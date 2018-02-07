import scala.util.Try

object OptionParser {

  sealed trait Settings
  case class Min(underlying: Int) extends Settings
  case class Max(underlying: Int) extends Settings
  case class Error(msg: String) extends Settings
  case object Help extends Settings {
    val helpText =
      """|Options:
        |
        |-h, --help
        |  Print help text
        |
        |-i, --min
        |  Lower bound for random generation
        |
        |-a, --max
        |  Upper bound for random generation
        |
        |-c, --count
        |  Quantity of numbers generated
        |
      """.stripMargin
  }
  class Count(val underlying: Int) extends Settings {
    override def toString: String = s"Count($underlying)"

    override def hashCode(): Int = {
      val state = Seq(underlying)
      state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
    }

    def canEqual(other: Any): Boolean = other.isInstanceOf[Count]

    override def equals(other: Any): Boolean = other match {
      case that: Count =>
        (that canEqual this) &&
          underlying == that.underlying
      case _ => false
    }
  }

  object Count {
    def apply(underlying: Int): Count = new Count(underlying)

    def unapply(arg: Count): Option[Int] = Some(arg.underlying)
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
