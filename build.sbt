name := "BestTradeRoutes"

version := "0.1"

lazy val `BestTradeRoutes` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.typelevel" %% "squants"                 % "1.3.0",
  guice,
  "org.webjars" %% "webjars-play" % "2.6.3",
  "org.webjars" % "bootstrap" % "4.0.0-2",
)