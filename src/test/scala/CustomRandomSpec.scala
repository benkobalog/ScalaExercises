import org.scalatest._

class CustomRandomSpec extends FlatSpec with Matchers {

  private def isBetween(x: Int, min: Int, max: Int) = x >= min && x <= max

  private def runTimes[T](times: Int, f: => T)(isGood: T => Boolean) = {
    (0 to times).foreach{ _ =>
      val result = f
      assert(isGood(result), s"$result" )
    }
  }

  it should "return numbers between limits when min and max are given" in {
    val (min, max) = (-123, -121)
    val setting = Setting(Some(min), Some(max), false)
    runTimes(100, CustomRandom.run(setting).get)(isBetween(_, min, max))
  }

  it should "work with only max given" in {
    val (min, max) = (CustomRandom.defaultMin, 2)
    val setting = Setting(None, Some(max), false)
    runTimes(100, CustomRandom.run(setting).get)(isBetween(_, min, max))
  }


  it should "work with only min given" in {
    val (min, max) = (8, CustomRandom.defaultMax)
    val setting = Setting(Some(min), None, false)
    runTimes(100, CustomRandom.run(setting).get)(isBetween(_, min, max))
  }

  it should "work without max or min given" in {
    val (min, max) = (CustomRandom.defaultMin, CustomRandom.defaultMax)
    val setting = Setting(None, None, false)
    runTimes(100, CustomRandom.run(setting).get)(isBetween(_, min, max))
  }

  "CustomRandom" should "return None when isHelp is true" in {
    CustomRandom.run(Setting(Some(4), Some(4), true)) should be(None)
  }
}
