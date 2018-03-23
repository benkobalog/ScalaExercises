package scraper

import java.io.{BufferedOutputStream, FileOutputStream}

import http.Client._
import scraper.Helpers._
import scraper.Links.extractJpgUrls

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object DownloadSolution {

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
      val imgBytes = getAsByteArray(link)
      imgBytes
        .flatMap { response =>
          debug(s"Downloading: $link ...")
          response.body match {
            case Right(bytes) => Future.successful(bytes)
            case Left(error)  => Future.failed(new Exception(error))
          }
        }
        .flatMap(writeArrayOfBytesToFile(link))
    }
  }

  def getAllJpgUrls(genUrl: () => String,
                    traverseDepth: Int,
                    urls: Set[String]): Future[Set[String]] = {
    val url = genUrl()
    debug(s"Page: $url")
    for {
      htmlContent <- downloadPage(url)
      jpgUrls <- extractJpgUrls(htmlContent)
      imageLinks <- {
        if (jpgUrls.nonEmpty && traverseDepth > 0)
          getAllJpgUrls(genUrl, traverseDepth - 1, urls ++ jpgUrls)
        else
          Future.successful(urls)
      }
    } yield imageLinks
  }

  def downloadPage(url: String): Future[String] = {
    val response = getAsString(url)
    response.map { r =>
      r.body.fold(identity, identity)
    }
  }
}
