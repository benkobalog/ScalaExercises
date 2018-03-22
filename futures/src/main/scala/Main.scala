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

    val url = "http://portfotolio.net/medvekoma?page=1"

    def getAllImgs(url: String) = {}

    val downloadAllJpgs = for {
      htmlContent <- downloadPage(url)
      jpgUrls <- extractJpgUrls(htmlContent)
      _ <- downloadImages(jpgUrls)
    } yield jpgUrls

    Await.result(downloadAllJpgs, 10.seconds)

    Client.backend.close()
  }

}
