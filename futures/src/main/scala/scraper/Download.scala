package scraper

import java.io.{BufferedOutputStream, FileOutputStream}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import Helpers._
import http.Client._
import scraper.Links.extractJpgUrls

object Download {

  private def stringToFilename(url: String) =
    url.replaceAll("[\"*/:<>?|]", "").replace("\\", "")

  private def writeArrayOfBytesToFile(url: String)(
      byteArray: Array[Byte]): Future[Unit] = Future {
    val folder = "images"
    val filename = stringToFilename(url)
    debug(s"Writing $url to $filename")
    val bos =
      new BufferedOutputStream(new FileOutputStream(folder + "/" + filename))
    bos.write(byteArray)
    bos.close()
  }

  def downloadImages(links: Set[String]): Future[Set[Unit]] = {
    Future.traverse(links) { link =>
      val imgBytes = getAsByteArray(link).map(_.body)
      ???
    }
  }

  def getAllJpgUrls(genUrl: () => String,
                    traverseDepth: Int,
                    urls: Set[String]): Future[Set[String]] = {
    val url = genUrl()
    debug(s"Page: $url")
    ???
  }

  def downloadPage(url: String): Future[String] = {
    val response = getAsString(url)
    ???
  }
}
