import scala.util.{Success, Try, Failure}

sealed trait Relation
case object Greater extends Relation
case object Less extends Relation

object CompareMachineTry {
  private val greaterThanWords = List("bigger", "greater")
  private val lessThanWords = List("less", "lower", "smaller")

  def showResult(result: Try[Boolean]): String =
    result match {
      case Success(v)     => if (v) "Correct" else "Incorrect"
      case Failure(error) => error.getMessage
    }

  def getRelation(predicate: String): Try[Relation] = {
    val hasGreater = greaterThanWords.exists(predicate.contains)
    val hasLess = lessThanWords.exists(predicate.contains)

    (hasGreater, hasLess) match {
      case (true, true)   => Failure(new Exception("Found both less and greater"))
      case (false, false) => Failure(new Exception("Found no less or greater"))
      case (true, false)  => Success(Greater)
      case (false, true)  => Success(Less)
    }
  }

  def extractNum(predicate: String): Try[Int] = {
    predicate
      .find(_.isDigit)
      .map(_.asDigit)
      .toTry(new Exception("No second number"))
  }

  def isCorrect(numberFromAudio: Option[Int],
                predicateFromAudio: Option[String]): Try[Boolean] =
    for {
      firstNum <- numberFromAudio.toTry(new Exception("No first number"))
      predicate <- predicateFromAudio.toTry(new Exception("No predicate"))
      secondNum <- extractNum(predicate)
      relation <- getRelation(predicate)
    } yield
      relation match {
        case Greater => firstNum > secondNum
        case Less    => secondNum > firstNum
      }

  implicit class OptionOps[A](option: Option[A]) {
    def toTry(failure: Throwable): Try[A] =
      option match {
        case Some(v) => Success(v)
        case None    => Failure(failure)
      }
  }
}
