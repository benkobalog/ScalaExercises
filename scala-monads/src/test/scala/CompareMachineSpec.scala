import org.scalatest._

class CompareMachineSpec extends FlatSpec with Matchers {

  "CompareMachine" should "return true" in {
    val corrects = List(
      (2, "bigger than 1"),
      (5, "smaller than 9"),
      (4, "is greater than 0"),
      (3, "is lower than 7")
    ).map { case (int, str) => (Some(int), Some(str)) }

    corrects.foreach {
      case (int, str) =>
        CompareMachine.isCorrect(int, str) should be(Right(true))
    }
  }

  it should "return false" in {
    val corrects = List(
      (2, "bigger than 5"),
      (5, "smaller than 2"),
      (4, "is greater than 8"),
      (3, "is lower than 1")
    ).map { case (int, str) => (Some(int), Some(str)) }

    corrects.foreach {
      case (int, str) =>
        CompareMachine.isCorrect(int, str) should be(Right(false))
    }
  }
}
