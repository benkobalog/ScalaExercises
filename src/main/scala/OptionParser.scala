import scala.util.Try

object OptionParser {

  sealed trait Settings
  case class Min(underlying: Int) extends Settings
  case class Max(underlying: Int) extends Settings
  case class Count(underlying: Int) extends Settings
  case class Error(msg: String) extends Settings
  case object Help extends Settings {
    val helpText =
      """
        |This should be helpful
      """.stripMargin
  }

  // TODO 
  def run(args: List[String]): List[Settings] = {
    def isInt(s: String) = Try(s.toInt).isSuccess
    args match {
      case ("-h" | "--help") :: tail =>
        Help :: run(tail)

      case ("-i" | "--min") :: number :: tail if isInt(number) =>
        Min(number.toInt) :: run(tail)

      case ("-c" | "--count") :: number :: tail if isInt(number) =>
        Count(number.toInt) :: run(tail)

      case ("-a" | "--max") :: number :: tail if isInt(number) =>
        Max(number.toInt) :: run(tail)

      case Nil       => Nil
      case x :: tail => Error(x) :: run(tail)
    }
  }

  def alternativeSolution(args: List[String]) =
    args match {
      case ("-h" | "--help") :: tail =>
        Help :: run(tail)

      case switch :: value :: tail
          if (switch.startsWith("-") || switch.startsWith("--")) && Try(
            value.toInt).isSuccess =>
        run(tail) + switch match {
          case "-c" | "--count" => Count(value.toInt)
          case "-i" | "--min"   => Min(value.toInt)
          case "-a" | "--max"   => Max(value.toInt)
        }

      case Nil       => Nil
      case x :: tail => Error(x) :: run(tail)
    }
}
