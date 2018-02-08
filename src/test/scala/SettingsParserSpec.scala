import org.scalatest._

class SettingsParserSpec extends FlatSpec with Matchers {

  // #1
  "ArgumentParser" should "parse '-h' type arguments" in {
    SettingsParser.apply(List("-h")) should be(List(Help))
  }

  // #2
  it should "parse '--word' type arguments" in {
    SettingsParser.apply(List("--help")) should be(List(Help))
  }

  // #3
  it should "throw exception on invalid arguments" in {
    SettingsParser.apply(List("-goat")) should be (List(Error("-goat")))
    SettingsParser.apply(List("-i", "three")) should be (List(Error("-i"), Error("three")))
    SettingsParser.apply(List("-a", "six")) should be (List(Error("-a"), Error("six")))
  }

  // #4
  it should "parse '--word parameter' type arguments" in {
    SettingsParser.apply(List("--min", "4")) should be(List(Min(4)))
    SettingsParser.apply(List("-i", "4")) should be(List(Min(4)))
    SettingsParser.apply(List("-a", "4")) should be(List(Max(4)))
  }

  // This should pass after #4
  it should "handle multiple parameters" in {
    SettingsParser.apply(List("--min", "4", "--max", "6", "--count", "3", "--help")) should be(List(Min(4), Max(6), Error("--count"), Error("3"), Help))
  }

}
