name := "Implicits"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"

val http4sVersion = "0.18.0"
val LogbackVersion = "1.2.3"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % LogbackVersion
)