package testmodels

import json.JsWriterTypes._
import json.{JsObject, JsValue, JsWriter}

object VisitorTypeClassInstances {
  implicit val anonWriter: JsWriter[Anonymous] = new JsWriter[Anonymous] {
    override def write(in: Anonymous): JsValue =
      JsObject(
        Map(
          "id" -> in.id.toJson,
          "createdAt" -> in.createdAt.toString.toJson
        ))
  }

  implicit val userWriter: JsWriter[User] = new JsWriter[User] {
    override def write(in: User): JsValue =
      JsObject(
        Map(
          "id" -> in.id.toJson,
          "email" -> in.email.toJson,
          "createdAt" -> in.createdAt.toString.toJson
        ))
  }
}
