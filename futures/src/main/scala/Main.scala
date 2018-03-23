import http.Client

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scraper.Helpers._
import scraper.Links._
import scraper.Download._

object Main {
  println("number of cpus: " + Runtime.getRuntime.availableProcessors())

  def main(args: Array[String]): Unit = {

    def generateUrl = {
      var id = 0
      def gen() = {
        id += 1
        "http://portfotolio.net/medvekoma?page=" + id
      }
      gen _
    }

    val allJpgUrls = getAllImgs(generateUrl, 1, Set())
    val downloadedImages = allJpgUrls.flatMap(downloadImages)

    val result = Await.result(downloadedImages, 10.seconds)
    debug("Last Future result: " + result)

    Client.backend.close()
    debug("Closed http backend")
  }

}
