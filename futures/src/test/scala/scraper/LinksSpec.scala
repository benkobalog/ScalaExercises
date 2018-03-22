package scraper

import org.scalatest._
import scala.io.Source

class LinksSpec extends AsyncFlatSpec with Matchers {

  "extractJpgUrls" should "find urls in page" in {
    val htmlPagePath = getClass.getResource("/page.html").getPath
    val file = Source.fromFile(htmlPagePath).mkString

    val jpgUrlRegex = """https?:\/\/.*\.jpg""".r

    scraper.Links.extractJpgUrls(file).map { res =>
      res.foreach(url =>
        assert(jpgUrlRegex.findAllIn(url).toList.length == 1, url))
      res.size should be(21)
    }
  }

}
