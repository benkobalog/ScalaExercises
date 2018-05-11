package retry
import scala.concurrent.Future
import scala.language.higherKinds
import scala.util.{Failure, Success, Try}

trait Retry[A[_]] {
  def retry[T](times: Int)(a: => A[T]): A[T]
}

object Retry {

  type Id[A] = A

  def retry[A[_], T](times: Int)(a: => A[T])(implicit r: Retry[A]): A[T] = {
    r.retry(times)(a)
  }

  implicit val idRetry: Retry[Id] = new Retry[Id] {
    override def retry[T](times: Int)(a: => Id[T]): Id[T] = {
      Try(a) match {
        case Success(res) =>
          println("Success \\o/")
          res
        case Failure(res) =>
          println("retry " + times)
          if(times < 1) throw res
          else retry(times - 1)(a)
      }
    }
  }

  implicit val futureRetry: Retry[Future] = new Retry[Future] {
    import scala.concurrent.ExecutionContext.Implicits.global
    override def retry[T](times: Int)(a: => Future[T]): Future[T] = {
      a.recoverWith{ case _ if times > 0 =>
        println("retry " + times)
        retry(times - 1)(a)
      }
    }
  }

}

