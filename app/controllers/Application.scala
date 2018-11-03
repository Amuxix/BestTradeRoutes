package controllers

import javax.inject.Inject
import logic.stanton.StantonSystem
import logic.{Material, TradingPost}
import model.PriceData
import model.PriceData.materialFormat
import play.Logger
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

class Application @Inject()(
  messagesAction: MessagesActionBuilder,
  components: MessagesControllerComponents,
  priceUpdating: views.html.priceUpdating,
  tradingPostPicker: views.html.tradingPostPicker,
) extends MessagesAbstractController(components) {
  val priceForm: Form[Seq[PriceData]] = Form(
    mapping(
      "prices" -> seq(mapping(
        "material" -> of[Material],
        "price" -> optional(bigDecimal)
      )(PriceData.apply)(PriceData.unapply))
    )(Seq.apply)(Seq.unapplySeq)
  )

  def index = Action {
    Ok(tradingPostPicker(StantonSystem.tradingPosts))
  }

  def prices(tradingPostName: String) = Action { implicit request: MessagesRequest[AnyContent] =>
    TradingPost.findByName(tradingPostName).fold {
      Logger.error(s"Could not find $tradingPostName")
      BadRequest(tradingPostPicker(StantonSystem.tradingPosts))
    } {
      tradingPost =>
        val buy = tradingPost.buy.map {
          case (material, price) => PriceData(material, Some(BigDecimal(price)))
        }.toSeq
        val sell = tradingPost.sell.map {
          case (material, price) => PriceData(material, Some(BigDecimal(price)))
        }.toSeq
        Ok(priceUpdating(tradingPost, priceForm.fill(buy), priceForm.fill(sell)))
    }
  }

  def updatePrices(tradingPost: String) = Action { implicit request =>
    priceForm.bindFromRequest.fold({ errors =>
        Logger.info(errors.toString)
        BadRequest(tradingPostPicker(StantonSystem.tradingPosts))
      }, { success =>
        Ok(tradingPostPicker(StantonSystem.tradingPosts))
      }
    )
  }
}
