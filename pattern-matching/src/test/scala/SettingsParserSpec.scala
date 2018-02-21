import org.scalatest._

class SettingsParserSpec extends FlatSpec with Matchers {

  // #1
  "ArgumentParser" should "parse '-h' type arguments" in {
    val settings = SettingsParser(List("-h"))

    settings should be(Setting(None, None, true))
  }

  // #2
  it should "parse '--word' type arguments" in {
    SettingsParser(List("--help")) should be(Setting(None, None, true))
  }

  // #3
  it should "throw exception on invalid arguments" in {
    def catchMessage(x: => Setting) =
      intercept[IllegalArgumentException](x).getMessage

    catchMessage(SettingsParser(List("-goat"))) should be("-goat")
    catchMessage(SettingsParser(List("-i", "three"))) should be("-i")
    catchMessage(SettingsParser(List("-a", "six"))) should be("-a")
  }

  // #4
  it should "parse '--word parameter' type arguments" in {
    SettingsParser(List("--min", "4")) should be(Setting(Some(4), None, false))
    SettingsParser(List("-i", "4")) should be(Setting(Some(4), None, false))
    SettingsParser(List("-a", "4")) should be(Setting(None, Some(4), false))
  }

  // This should pass after #4
  it should "handle multiple parameters" in {
    SettingsParser(List("--min", "4", "--max", "6", "--help")) should be(
      Setting(Some(4), Some(6), true))
  }

}
