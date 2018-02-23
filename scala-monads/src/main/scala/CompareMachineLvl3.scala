import scala.util.{Failure, Success, Try}
import OptionOps._

object CompareMachineLvl3 {
  private val greaterThanWords = List("bigger", "greater")
  private val lessThanWords = List("less", "lower", "smaller")

  /** Return Correct for true, Incorrect for false
    * Return the error on Failure*/
  def showResult(result: Try[Boolean]): String =
    ???

  def isCorrect(numberFromAudio: Option[Int],
                predicateFromAudio: Option[String]): Try[Boolean] =
    for {
      firstNum <- numberFromAudio.toTry(new Exception("No first number"))
      predicate <- predicateFromAudio.toTry(new Exception("No predicate"))
      secondNum <- extractNum(predicate)
      relation <- getRelation(predicate)
    } yield ???

  private def getRelation(predicate: String): Try[Relation] = ???

  private def extractNum(predicate: String): Try[Int] = ???
}
