import org.scalatest._
import OptionParser.Count

class CountSpec extends FlatSpec with Matchers {
  "Count" should "have a properly working apply" in {
    Count(1).underlying should be(1)
  }

  it should "have a properly working unapply" in {
    val count = new Count(123)

    count match { case Count(c) => c should be(123)}
  }
}
