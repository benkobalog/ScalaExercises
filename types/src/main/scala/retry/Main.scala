package retry

import Retry._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Try}

object Main {
  def main(args: Array[String]): Unit = {
    def futureFn = {
      Future {
        throw new Exception("We r fuk'd")
        123
      }
    }

    def normalFunction = {
//
      Try {
        throw new Exception("We r fuk'd")
        123123
      }
    }

    val asd = retry(4)(normalFunction)

    println(asd)
//    println(Await.result(asd, 1.minute))
  }
}
