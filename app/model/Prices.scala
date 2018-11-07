package model

import java.sql.Timestamp
import java.time.Instant
import java.util.NoSuchElementException

import logic.{Material, TradingPost}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Prices extends PostgresProfile {
  import api._
  private val db = Database.forConfig("db.default")

  implicit val tradingPostColumnType: BaseColumnType[TradingPost] = MappedColumnType.base[TradingPost, String](
    _.toString,
    tradingPostName => TradingPost.findByName(tradingPostName).fold(throw new NoSuchElementException(s"Could not find $tradingPostName"))(identity)
  )

  implicit val materialColumnType: BaseColumnType[Material] = MappedColumnType.base[Material, String](
    _.toString,
    materialName => Material.findByName(materialName).fold(throw new NoSuchElementException(s"Could not find $materialName"))(identity)
  )

  class PriceTable(tag: Tag) extends Table[Price](tag, "price") {
    def tradingPost = column[TradingPost]("trading_post")
    def material = column[Material]("material")
    def price = column[Double]("price")
    def isBuy = column[Boolean]("is_buy")
    def updatedAt = column[Timestamp]("updated_at")

    override def * = (
      tradingPost,
      material,
      price,
      isBuy,
    ) <> (Price.tupled, Price.unapply)
  }

  private val prices = TableQuery[PriceTable]

  private def getMaterialPrices(tradingPost: TradingPost, isBuy: Boolean): Future[Map[Material, Double]] =
    db.run(
      prices
        .filter(_.tradingPost === tradingPost)
        .filter(_.isBuy === isBuy)
        .result
    ).map(_.map(price => price.material -> price.price).toMap)

  def buyPrices(tradingPost: TradingPost): Future[Map[Material, Double]] =
    getMaterialPrices(tradingPost, isBuy = true)

  def sellPrices(tradingPost: TradingPost): Future[Map[Material, Double]] =
    getMaterialPrices(tradingPost, isBuy = false)

  def allFor(tradingPost: TradingPost): Future[Seq[Price]] =
    db.run(
      prices
        .filter(_.tradingPost === tradingPost)
        .result
    )

  private def update(newPrice: Price): DBIO[Int] =
    prices
      .filter(_.tradingPost === newPrice.tradingPost)
      .filter(_.isBuy === newPrice.isBuy)
      .filter(_.material === newPrice.material)
      .map(p => (p.price, p.updatedAt))
      .update((newPrice.price, Timestamp.from(Instant.now)))

  def updatePrices(newPrices: Seq[Price]): Future[Seq[Int]] =
    db.run(DBIO.sequence(newPrices.map(update)))

  def insertAll(newPrices: Seq[Price]): Future[Option[Int]] =
    db.run(prices ++= newPrices)
}
