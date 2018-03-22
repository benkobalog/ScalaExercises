package scraper

import scala.concurrent.Future

object Links {
  def extractJpgUrls(html: String): Future[Set[URL]] = {
    val jpgUrlR = """https?:\/\/.*\.jpg""".r
    Future.successful(jpgUrlR.findAllIn(html).toSet)
  }

}
