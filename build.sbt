name := "BestTradeRoutes"

version := "0.1"

lazy val `BestTradeRoutes` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-slick" % "3.0.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.1",
  "org.postgresql" % "postgresql" % "42.2.5",
  "org.typelevel" %% "squants" % "1.3.0",
  "org.webjars" %% "webjars-play" % "2.6.3",
  "org.webjars" % "bootstrap" % "4.0.0-2",
)