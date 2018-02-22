object CompareMachine extends CompareMachineService {
  def showResult(result: Either[String, Boolean]): String =
    result match {
      case Right(res)         => if (res) "Correct" else "Incorrect"
      case Left(errorMessage) => errorMessage
    }

  private val greaterThanWords = List("bigger", "greater")
  private val lowerThanWords = List("lower", "smaller")

  def isCorrect(numberFromAudio: Option[Int],
                predicateFromAudio: Option[String]): Either[String, Boolean] =
    for {
      firstNum <- numberFromAudio.toRight("Repeat the first number please!")
      predicate <- predicateFromAudio.toRight("Repeat the predicate please!")
      secondNum <- extractNumberFromPredicate(predicate)
      hasBigger = greaterThanWords.exists(predicate contains _)
      hasLower = lowerThanWords.exists(predicate contains _)
      firstBigger <- isBigger(hasBigger, hasLower)
    } yield
      if (firstBigger)
        firstNum > secondNum
      else
        firstNum < secondNum

  private def isBigger(hasBigger: Boolean,
                       hasLower: Boolean): Either[String, Boolean] =
    (hasBigger, hasLower) match {
      case (true, true)   => Left("Lower or bigger?")
      case (false, false) => Left("You need to specify a relation!")
      case _              => Right(hasBigger)
    }

  private def extractNumberFromPredicate(predicate: String) = {
    predicate
      .find(_.isDigit)
      .map(_.asDigit)
      .toRight("Cannot find a number in the predicate!")
  }
}
