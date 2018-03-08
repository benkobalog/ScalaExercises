import json.JsWriterTypes._
import testmodels._

import testmodels.VisitorTypeClassInstances._

object Main extends App {

  val a = Anonymous("1").toJson
  val b = User("2", "test@test.com").toJson

  "".toJson
  123.toJson

  println(a.stringify)
  println(b.stringify)

}
