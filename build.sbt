name := """haplo_things"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.play" %% "play-slick" % "0.8.0",
  "joda-time" % "joda-time" % "2.4",
  "org.joda" % "joda-convert" % "1.6",
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0"
)
