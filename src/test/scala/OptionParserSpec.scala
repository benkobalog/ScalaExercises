import OptionParser._
import org.scalatest._

class OptionParserSpec extends FlatSpec with Matchers {

  // #1
  "ArgumentParser" should "parse '-h' type arguments" in {
    OptionParser.run(List("-h")) should be(List(Help))
  }

  // #2
  it should "parse '--word' type arguments" in {
    OptionParser.run(List("--help")) should be(List(Help))
  }

  // #3
  it should "throw exception on invalid arguments" in {
    OptionParser.run(List("-goat")) should be (List(Error("-goat")))
  }

  // #4
  it should "parse '--word parameter' type arguments" in {
    OptionParser.run(List("--min", "4")) should be(List(Min(4)))
    OptionParser.run(List("-i", "4")) should be(List(Min(4)))
    OptionParser.run(List("-a", "4")) should be(List(Max(4)))
    OptionParser.run(List("--count", "4")) should be(List(Count(4)))
  }

  // This should pass after #4
  it should "handle multiple parameters" in {
    OptionParser.run(List("--min", "4", "--max", "6", "--count", "3", "--help")) should be(List(Min(4), Max(6), Count(3), Help))
  }

}
