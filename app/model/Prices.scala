package model

import java.sql.Timestamp
import java.time.Instant
import java.util.NoSuchElementException

import logic.{Material, TradingPost}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Prices extends PostgresProfile {
  import api._
  private lazy val db = Database.forConfig("db.default")

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

  private def materialsPrices(tradingPost: TradingPost, isBuy: Boolean): DBIO[Seq[Price]] =
    prices
      .filter(_.tradingPost === tradingPost)
      .filter(_.isBuy === isBuy)
      .result

  private def pricesMap(tradingPost: TradingPost, isBuy: Boolean): Future[Map[Material, Double]] =
    db.run(materialsPrices(tradingPost, isBuy))
      .map(_.map(price => price.material -> price.price).toMap)

  private def price(tradingPost: TradingPost, material: Material, isBuy: Boolean): Future[Double] =
    db.run(
      prices
        .filter(_.tradingPost === tradingPost)
        .filter(_.isBuy === isBuy)
        .filter(_.material === material)
        .map(_.price)
        .result
        .head
    )

  private def update(newPrice: Price): DBIO[Int] =
    prices
      .filter(_.tradingPost === newPrice.tradingPost)
      .filter(_.isBuy === newPrice.isBuy)
      .filter(_.material === newPrice.material)
      .map(p => (p.price, p.updatedAt))
      .update((newPrice.price, Timestamp.from(Instant.now)))

  def buyPrices(tradingPost: TradingPost): Future[Map[Material, Double]] =
    pricesMap(tradingPost, isBuy = true)

  def sellPrices(tradingPost: TradingPost): Future[Map[Material, Double]] =
    pricesMap(tradingPost, isBuy = false)

  def buyPrice(tradingPost: TradingPost, material: Material): Future[Double] =
    price(tradingPost, material, isBuy = true)

  def sellPrice(tradingPost: TradingPost, material: Material): Future[Double] =
    price(tradingPost, material, isBuy = false)

  def allFor(tradingPost: TradingPost): Future[Seq[Price]] =
    db.run(
      prices
        .filter(_.tradingPost === tradingPost)
        .result
    )

  def updatePrices(newPrices: Seq[Price]): Future[Seq[Int]] = {
    val value = DBIO.sequence(newPrices.map(update))
    db.run(value)
  }

  def insertAll(newPrices: Seq[Price]): Future[Option[Int]] =
    db.run(prices ++= newPrices)
}
