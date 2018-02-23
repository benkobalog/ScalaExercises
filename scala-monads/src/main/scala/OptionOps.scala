import scala.util.{Failure, Success, Try}
object OptionOps {

  implicit class OptionConverter[A](option: Option[A]) {
    def toTry(failure: Throwable): Try[A] =
      option match {
        case Some(v) => Success(v)
        case None    => Failure(failure)
      }
  }

}
