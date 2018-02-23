import org.scalatest._
import org.scalatest.prop.TableDrivenPropertyChecks._

class CompareMachineSpec extends FlatSpec with Matchers {

  "CompareMachine" should "return true" in {
    val corrects = Table(
      ("firstNum", "predicate"),
      (Some(2), Some("bigger than 1")),
      (Some(5), Some("smaller than 9")),
      (Some(4), Some("is greater than 0")),
      (Some(3), Some("is lower than 7"))
    )

    forAll(corrects) { (int: Option[Int], str: Option[String]) =>
      CompareMachine.isCorrect(int, str) should be(Right(true))
    }
  }

  it should "return false" in {
    val incorrects = Table(
      ("firstNum", "predicate"),
      (Some(2), Some("bigger than 5")),
      (Some(5), Some("smaller than 2")),
      (Some(4), Some("is greater than 8")),
      (Some(3), Some("is lower than 1"))
    )

    forAll(incorrects) { (int: Option[Int], str: Option[String]) =>
      CompareMachine.isCorrect(int, str) should be(Right(false))
    }
  }

  it should "return Errors" in {
    val errorsT = Table(
      ("firstNum", "predicate"),
      (Some(4), Some("than 5")),
      (Some(5), Some("smaller bigger than 2")),
      (None, Some("is greater than 8")),
      (Some(4), Some("is greater than")),
      (Some(3), None)
    )

    forAll(errorsT) { (int: Option[Int], str: Option[String]) =>
      CompareMachine.isCorrect(int, str).isLeft should be(true)
    }
  }
}
