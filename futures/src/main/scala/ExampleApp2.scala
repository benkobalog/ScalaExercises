import scala.concurrent.{Await, Future, blocking}
import scala.concurrent.ExecutionContext.Implicits.global
import scraper.Helpers._

import concurrent.duration._

object ExampleApp2 {
  def main(args: Array[String]): Unit = {

    def retry[T](future: => Future[T], times: Int): Future[T] = {
      future.recoverWith {
        case t if times > 0 =>
          retry(future, times - 1)
      }
    }

    val eventualInt = () =>
      Future {
        debug("something")
        throw new Exception("Oo")
        1
    }
    Await.result(retry(eventualInt(), 5), 1.minute)

  }
}
