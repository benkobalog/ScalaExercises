object CompareMachine {
  def showResult(result: Either[String, String]): String =
    result match {
      case Right(res)         => res
      case Left(errorMessage) => errorMessage
    }

  private val greaterThanWords = List("bigger", "greater")
  private val lowerThanWords = List("lower", "smaller")

  def compare(numberFromAudio: Option[Int],
              predicateFromAudio: Option[String]): Either[String, String] =
    for {
      firstNum <- numberFromAudio.toRight("Repeat the first number please!")
      predicate <- predicateFromAudio
        .map(_.toLowerCase())
        .toRight("Repeat the predicate please!")
      secondNum <- extractNumberFromPredicate(predicate)
      hasBigger = greaterThanWords.exists(predicate contains _)
      hasLower = lowerThanWords.exists(predicate contains _)
      askBigger <- checkPredicate(hasBigger, hasLower)
    } yield
      if (askBigger)
        if (firstNum > secondNum)
          "Correct"
        else
          "Incorrect"
      else if (firstNum < secondNum)
        "Correct"
      else
        "Incorrect"

  private def checkPredicate(hasBigger: Boolean,
                             hasLower: Boolean): Either[String, Boolean] =
    (hasBigger, hasLower) match {
      case (true, true)   => Left("Lower or bigger?")
      case (false, false) => Left("You should say lower or bigger")
      case _              => Right(hasBigger)
    }

  private def extractNumberFromPredicate(predicate: String) = {
    predicate
      .find(_.isDigit)
      .map(_.asDigit)
      .toRight("Cannot find a number in the predicate!")
  }
}
