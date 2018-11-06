package model

import java.sql.Timestamp
import java.time.Instant
import java.util.NoSuchElementException

import javax.inject.{Inject, Singleton}
import logic.{Material, TradingPost}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PriceDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit exec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  implicit val tradingPostColumnType = MappedColumnType.base[TradingPost, String](
    _.toString,
    tradingPostName => TradingPost.findByName(tradingPostName).fold(throw new NoSuchElementException(s"Could not find $tradingPostName"))(identity)
  )

  implicit val materialColumnType = MappedColumnType.base[Material, String](
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

  private def getMaterialPrices(tradingPost: TradingPost, isBuy: Boolean): Future[Seq[Price]] =
    db.run(
      prices
        .filter(_.tradingPost === tradingPost)
        .filter(_.isBuy === isBuy)
        .result
    )//.map(_.flatMap(price => price.price.map(price.material -> _)).toMap)

  def buyPrices(tradingPost: TradingPost): Future[Seq[Price]] =
    getMaterialPrices(tradingPost, isBuy = true)

  def sellPrices(tradingPost: TradingPost): Future[Seq[Price]] =
    getMaterialPrices(tradingPost, isBuy = false)

  def allFor(tradingPost: TradingPost): Future[Seq[Price]] =
    db.run(
      prices
        .filter(_.tradingPost === tradingPost)
        .result
    )

  def updatePrices(newPrices: Seq[Price]) =
    Future.sequence(
      newPrices.collect { case Price(tradingPost, material, price, isBuy) =>
        db.run(
          prices
            .filter(_.tradingPost === tradingPost)
            .filter(_.isBuy === isBuy)
            .filter(_.material === material)
            .map(p => (p.price, p.updatedAt))
            .update((price, Timestamp.from(Instant.now)))
        )
      }
    )

  def insertAll(newPrices: Seq[Price]) =
    db.run(
      prices ++= newPrices
    )
}
