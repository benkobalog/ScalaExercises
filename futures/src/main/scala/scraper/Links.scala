package scraper

import scala.concurrent.Future

object Links {
  def extractJpgUrls(html: String): Future[Set[String]] = {
    val jpgUrlR = """https?:\/\/.*\.jpg""".r
    val urls = jpgUrlR.findAllIn(html).toSet
    println(s"Extacted ${urls.size} urls")
    ???
  }
}
