package model

import logic.{Material, TradingPost}

case class PriceCheckData(
  ignoredMaterials: Seq[Material],
  basesIgnored: Seq[TradingPost],
  basesKnown: Seq[TradingPost],
  materialsInHull: Seq[Material],
)
