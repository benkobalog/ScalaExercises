package scraper

import scala.concurrent.Future

object LinksSolution {
  def extractJpgUrls(html: String): Future[Set[String]] = {
    val jpgUrlR = """https?:\/\/.*\.jpg""".r
    val urls = jpgUrlR.findAllIn(html).toSet
    println(s"Extacted ${urls.size} urls")
    Future.successful(urls)
  }
}
