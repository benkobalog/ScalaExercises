import java.util.Date

import io.circe.{Encoder, Json, JsonObject}
import json.JsWriterTypes._
import testmodels._

object Main extends App {

//// Task 1

//  import PipeOps._
//  val res = "Mqtpmgmxw$evi$gssp%" |> (_.map(x => (x - 4).toChar))
//  println(res)

//// Task 2

//  // Here are the solutions for Anonymous and User
//  //  import testmodels.VisitorTypeClassInstances._
//
//  println("".toJson.stringify)
//  println(123.toJson.stringify)
//
//  val a = Anonymous("1").toJson
//  println(a.stringify)
//  val b = User("2", "test@test.com").toJson
//  println(b.stringify)

//// Task 3

//  import io.circe.generic.auto._, io.circe.syntax._
//
//  case class Person(name: String)
//  case class Greeting(salutation: String, person: Person, exclamationMarks: Int)
//
//  val res = Greeting("Hey", Person("Chris"), 3).asJson
//  println(res.toString())

//// Task 4

//  import testmodels._
//  val res2 = Anonymous("1").asJson
//  println(res2.toString())
//
//  val res3 = User("2", "test@test.com").asJson
//  println(res3.toString)
}
