import org.scalatest._
import OptionParser._

class CustomRandomSpec extends FlatSpec with Matchers {

  private val defaultCR = CustomRandom.default()

  "fromSet" should "construct a CustomRandom class with the proper parameters in the Set" in {
    val settings = List[Settings](Max(-3), Min(-100))
    CustomRandom.fromSet(settings) should be(CustomRandom(-100, -3, defaultCR.count))

    CustomRandom.fromSet(Count(5) :: settings) should be(CustomRandom(-100, -3, 5))
  }

  it should "construct the default CustomRandom class from an empty Set" in {
    CustomRandom.fromSet(Nil) should be(defaultCR)
  }

}
