name := "BestTradeRoutes"

version := "0.1"

lazy val `BestTradeRoutes` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  evolutions,
  "com.github.tminglei" %% "slick-pg" % "0.16.2",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
  "org.postgresql" % "postgresql" % "42.2.5",
  "org.typelevel" %% "squants" % "1.3.0",
  "org.webjars" %% "webjars-play" % "2.6.3",
  "org.webjars" % "bootstrap" % "4.0.0-2",
  "org.typelevel" %% "cats-core" % "1.5.0",
)