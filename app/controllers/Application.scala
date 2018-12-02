package controllers

import javax.inject.Inject
import logic.{Material, TradingPost, Universe}
import model.Prices
import play.api.mvc._
import logic.util.NameOrdering

import scala.concurrent.{ExecutionContext, Future}

class Application @Inject()(
  components: MessagesControllerComponents,
  tradingPostPicker: views.html.tradingPostPicker,
  pricesTable: views.html.pricesTable
)(implicit exec: ExecutionContext) extends AbstractController (components) {

  def index = Action.async {
    priceTable(Universe.tradingPosts, Material.materials)
  }

  def priceTable(tradingPosts: Set[TradingPost], materials: Set[Material]) =
    Future.sequence(tradingPosts.map { tradingPost =>
      for {
        buyPrices <- Prices.buyPrices(tradingPost)
        filteredBuyPrices = buyPrices.filterKeys(materials.contains)
        sellPrices <- Prices.sellPrices(tradingPost)
        filteredSellPrices = sellPrices.filterKeys(materials.contains)
      } yield tradingPost -> (filteredBuyPrices, filteredSellPrices)
    }).map { prices =>
      Ok(pricesTable(tradingPosts.toSeq.sorted(NameOrdering), materials.toSeq.sorted(NameOrdering), prices.toMap))
    }

  def tradePostPicker = Action {
    Ok(tradingPostPicker(Universe.tradingPosts))
  }
}
