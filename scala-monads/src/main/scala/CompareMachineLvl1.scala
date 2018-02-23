import scala.util.{Success, Try, Failure}
import OptionOps._

object CompareMachineLvl1 {
  private val greaterThanWords = List("bigger", "greater")
  private val lessThanWords = List("less", "lower", "smaller")

  /** Return Correct for true, Incorrect for false
    * Return the error on Failure*/
  def showResult(result: Try[Boolean]): String =
    ???

  def isCorrect(numberFromAudio: Option[Int],
                predicateFromAudio: Option[String]): Try[Boolean] =
    ???

}
