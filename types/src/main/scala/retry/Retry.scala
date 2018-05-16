package retry
import scala.concurrent.Future
import scala.language.higherKinds
import scala.util.{Failure, Success, Try}

// The type class
trait Retry[A[_]] {
  def retry[T](times: Int)(a: => A[T]): A[T]
}

object Retry {

  // Type class interface
  def retry[A[_], T](times: Int)(a: => A[T])(implicit r: Retry[A]): A[T] = {
    r.retry(times)(a)
  }

  // Type class instance for Try
  implicit val idRetry: Retry[Try] = new Retry[Try] {
    override def retry[T](times: Int)(a: => Try[T]): Try[T] = {
      a.recoverWith {
        case _ if times > 0 =>
          println("retry " + times)
          retry(times - 1)(a)
      }
    }
  }

  // Type class instance for Future
  implicit val futureRetry: Retry[Future] = new Retry[Future] {
    import scala.concurrent.ExecutionContext.Implicits.global
    override def retry[T](times: Int)(a: => Future[T]): Future[T] = {
      a.recoverWith {
        case _ if times > 0 =>
          println("retry " + times)
          retry(times - 1)(a)
      }
    }
  }

}
