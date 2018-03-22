name := "Futures"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test"
libraryDependencies += "com.softwaremill.sttp" %% "async-http-client-backend-future" % "1.1.10"
libraryDependencies += "com.softwaremill.sttp" %% "core" % "1.1.10"

//libraryDependencies ++= Seq(
//  "ch.qos.logback" % "logback-classic" % LogbackVersion
//)

