name := "Implicits"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"

val LogbackVersion = "1.2.3"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % LogbackVersion
)

val circeVersion = "0.9.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)