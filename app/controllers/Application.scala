package controllers

import javax.inject.Inject
import logic.stanton.StantonSystem
import logic.{Material, TradingPost}
import model.PriceData.materialFormat
import model.{Price, PriceDAO, PriceData}
import play.Logger
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc._
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class Application @Inject()(
  messagesAction: MessagesActionBuilder,
  components: MessagesControllerComponents,
  priceUpdating: views.html.priceUpdating,
  tradingPostPicker: views.html.tradingPostPicker,
  prices: PriceDAO,
  val dbConfigProvider: DatabaseConfigProvider,
)(implicit exec: ExecutionContext) extends MessagesAbstractController(components) with HasDatabaseConfigProvider[JdbcProfile]{
  val priceForm: Form[Seq[PriceData]] = Form(
    mapping(
      "prices" -> seq(mapping(
        "material" -> of[Material],
        "price" -> optional(of[Double]),
        "is_buy" -> boolean
      )(PriceData.apply)(PriceData.unapply))
    )(Seq.apply)(Seq.unapplySeq)
  )

  def index = Action {
    Ok(tradingPostPicker(StantonSystem.tradingPosts))
  }

  def prices(tradingPostName: String): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    TradingPost.findByName(tradingPostName).fold {
      Logger.error(s"Could not find $tradingPostName")
      Future.successful(BadRequest(tradingPostPicker(StantonSystem.tradingPosts)))
    } {
      tradingPost =>
        prices.allFor(tradingPost).map(_.map(_.toPriceData)).map { allPrices =>
          Ok(priceUpdating(tradingPost, allPrices))
        }
    }
  }

  def updatePrices(tradingPostName: String) = Action.async { implicit request =>
    priceForm.bindFromRequest.fold(
      { errors =>
        Logger.info(errors.errors.toString)
        Future.successful(BadRequest(tradingPostPicker(StantonSystem.tradingPosts)))
      }, { success =>
        TradingPost.findByName(tradingPostName).fold {
          Logger.error(s"Could not find $tradingPostName")
          Future.successful(BadRequest(tradingPostPicker(StantonSystem.tradingPosts)))
        } { tradingPost =>
          val newPrices = success.flatMap(_.toPrice(tradingPost))
          newPrices.foreach { price =>
            Logger.info(s"${price.material.displayName} ${if (price.isBuy) "buy" else "sell"} price is now ${price.price}")
          }
          prices.updatePrices(newPrices).map(_ =>
            Redirect(routes.Application.prices(tradingPostName))
          )
        }
      }
    )
  }

  def populate() = Action.async {
    Future.sequence(
      StantonSystem.tradingPosts.map { tradingPost =>
        val buy = tradingPost.buy.map { case (material, price) =>
          Price(tradingPost, material, price, isBuy = true)
        }.toSeq
        val sell = tradingPost.sell.map { case (material, price) =>
          Price(tradingPost, material, price, isBuy = false)
        }.toSeq
        prices.insertAll(buy ++ sell)
      }
    ).map { _=>
      Ok(tradingPostPicker(StantonSystem.tradingPosts))
    }
  }
}
