import scala.util.{Failure, Success, Try}
import OptionOps._

object CompareMachineLvl2 {
  private val greaterThanWords = List("bigger", "greater")
  private val lessThanWords = List("less", "lower", "smaller")

  /** Return Correct for true, Incorrect for false
    * Return the error on Failure*/
  def showResult(result: Try[Boolean]): String =
    ???

  def isCorrect(numberFromAudio: Option[Int],
                predicateFromAudio: Option[String]): Try[Boolean] =
    ???

  private def getRelation(predicate: String): Try[Relation] = ???

  private def extractNum(predicate: String): Try[Int] = ???

}
