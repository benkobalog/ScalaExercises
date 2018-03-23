import java.util.concurrent.ExecutorService

import scala.concurrent.{Await, Future, blocking}
import scala.concurrent.ExecutionContext.Implicits.global
import scraper.Helpers._

import concurrent.duration._

object ExampleApp {
  def main(args: Array[String]): Unit = {

    def fn(x: Int) = {
      debug(s"Starting $x")
      Thread.sleep(1000)
      debug(s"Completed $x")
      x
    }

    time {
      val futures = (0 until 10).map(x => Future(fn(x)))

      val f = Future.sequence(futures)

      Await.result(f, 1.minute)
    }
  }
}
