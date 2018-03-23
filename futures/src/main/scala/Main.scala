import http.Client

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scraper.Helpers._
import scraper.Links._
import scraper.DownloadSolution._

object Main {
  println("number of cpus: " + Runtime.getRuntime.availableProcessors())

  def main(args: Array[String]): Unit = {

    val urls = getAllJpgUrls(generateUrl, traverseDepth = 1, Set())
    val downloadFuture = urls.flatMap(downloadImages)

//   // Alternatively:
//    val downloadFuture = for {
//      urls <- getAllJpgUrls(generateUrl, traverseDepth = 1, Set())
//      _ <- downloadImages(urls)
//    } yield urls

    val result = Await.result(downloadFuture, 10.seconds)
    debug("Last Future result: " + result)

    Client.backend.close()
    debug("Closed http backend")
  }

}
