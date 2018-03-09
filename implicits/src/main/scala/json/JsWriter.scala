package json

// The type class
trait JsWriter[A] {
  def write(in: A): JsValue
}

object JsWriterTypes {

  // Type class interface (extension method version)
  implicit class JsUtil[A](value: A) {
    def toJson(implicit writer: JsWriter[A]): JsValue = {
      writer.write(value)
    }
  }

  // Type class instances
  implicit val stringWriter: JsWriter[String] =
    new JsWriter[String] {
      override def write(in: String): JsValue = JsString(in)
    }

  implicit val intWriter: JsWriter[Int] =
    new JsWriter[Int] {
      override def write(in: Int): JsValue = in.toString.toJson
    }

}