package retry

import Retry._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Try}

object Main {
  def main(args: Array[String]): Unit = {
    def futureFn =
      Future {
        throw new Exception("Something bad in Future")
        123
      }

    def tryFunction =
      Try {
        throw new Exception("Something bad in Try")
        123123
      }

    val tryRes = retry(4)(tryFunction)
    println(tryRes)

    val futureRes = retry(4)(futureFn)

    // Note: Await.result throws the exception which was in the Future's Failure
    // It transforms the Try semantics inside Future to a result this way.
    println(Try(Await.result(futureRes, 1.minute)))
  }
}
