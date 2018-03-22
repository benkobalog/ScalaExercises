package scraper

import java.io.{BufferedOutputStream, FileOutputStream}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import Helpers._
import http.Client._

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

  // Feel free to change the return type inside Future
  def downloadImages(links: Set[String]): Future[Unit] = {
    val linkDownloads = links.map { link =>
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
    Future.sequence(linkDownloads).map(_ => ())
  }

  def downloadPage(url: String): Future[String] = {
    val response = getAsString(url)
    response.map { r =>
      r.body.fold(identity, identity)
    }
  }
}
