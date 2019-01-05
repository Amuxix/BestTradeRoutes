package controllers

import javax.inject.Inject
import logic.util.NameOrdering
import logic.{Material, Ship, TradingPost, Universe}
import model._
import play.Logger
import play.api.data.Form
import play.api.data.Forms.{mapping, of, seq}
import play.api.mvc.{MessagesAbstractController, MessagesActionBuilder, MessagesControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

class PriceCheckRoute @Inject()(
  messagesAction: MessagesActionBuilder,
  components: MessagesControllerComponents,
  priceCheckRoute: views.html.priceCheckRoute,
)(implicit exec: ExecutionContext) extends MessagesAbstractController(components) {

  val priceCheckForm: Form[PriceCheckData] = Form(
    mapping(
      "ignored_materials" -> seq(of[Material]),
      "bases_ignored" -> seq(of[TradingPost]),
      "bases_known" -> seq(of[TradingPost]),
      "materials_in_hull" -> seq(of[Material])
    )(PriceCheckData.apply)(PriceCheckData.unapply)
  )

  def getPriceCheckRoute = Action {
    Ok(priceCheckRoute(Ship.values.toSeq.sorted(NameOrdering), Material.values.toSeq.sorted(NameOrdering), Universe.tradingPosts.toSeq.sorted(NameOrdering)))
  }

  def calculatePriceCheckRoute = Action.async { implicit request =>
    priceCheckForm.bindFromRequest.fold(
      { errors =>
        Logger.info(errors.errors.toString)
        Future.successful(Redirect(routes.PriceCheckRoute.getPriceCheck))
      },
      { success =>

        ???
      }
    )
  }

}
