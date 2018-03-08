import shapeless._

import scala.util.{Failure, Success, Try}

object AutoClose {

  def apply[A <: AutoCloseable, B](resource: A*)(block: List[A] => B): B = {
    Try(block(resource)) match {
      case Success(result) =>
        resource.foreach(_.close)
        result
      case Failure(e) =>
        resource.foreach(_.close)
        throw e
    }
  }

  def apply2[A <: AutoCloseable, B](resource: A*)(block: List[A] => B): B = {
    Try(block(resource)) match {
      case Success(result) =>
        resource.foreach(_.close)
        result
      case Failure(e) =>
        resource.foreach(_.close)
        throw e
    }
  }
}


object Main extends App {
  import AutoClose._

  override def main(args: Array[String]): Unit = {

    AutoClose()

  }
}