trait CompareMachineService {

  def showResult(result: Either[String, Boolean]): String

  def isCorrect(numberFromAudio: Option[Int],
                predicateFromAudio: Option[String]): Either[String, Boolean]

}
