package model

import logic.{Material, TradingPost}

case class PriceCheckData(
  ignoredMaterials: Seq[Material],
  basesIgnored: Seq[TradingPost],
  buyPricesKnown: Seq[Material],
  sellPricesKnown: Seq[Material],
  materialsInHull: Seq[Material],
)
