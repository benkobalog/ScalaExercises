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


    def getAllImgs(genUrl:() => String, urls: Set[String]): Future[Set[String]] = {
      val url = genUrl()
      debug(s"Page: $url")
      for {
        htmlContent <- downloadPage(url)
        jpgUrls <- extractJpgUrls(htmlContent)
        res <- if (jpgUrls.nonEmpty) getAllImgs(genUrl, urls ++ jpgUrls) else Future.successful(urls)
      } yield res
    }

    val allJpgUrls = getAllImgs(generateUrl, Set())
//    val downloadedImages = allJpgUrls.flatMap(downloadImages)

    val result = Await.result(allJpgUrls, 10.seconds)
    println(result)

    Client.backend.close()
  }

}
