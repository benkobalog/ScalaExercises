import org.scalatest._
import SettingsParser._

class CustomRandomSpec extends FlatSpec with Matchers {

  private val defaultCR = CustomRandom.default()

  "fromSet" should "construct a CustomRandom class with the proper parameters in the Set" in {
    val settings = List[Setting](Max(-3), Min(-100))
    CustomRandom.fromSet(settings) should be(CustomRandom(-100, -3, defaultCR.count))
  }

  it should "construct the default CustomRandom class from an empty Set" in {
    CustomRandom.fromSet(Nil) should be(defaultCR)
  }

}
