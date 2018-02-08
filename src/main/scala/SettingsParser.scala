import scala.util.Try

object SettingsParser {

  object intFromString {
    def unapply(arg: String): Option[Int] = Try(arg.toInt).toOption
  }

  def apply(args: List[String]): List[Setting] = {
    args match {
      case ("-h" | "--help") :: tail => Help :: apply(tail)
      case ("-i" | "--min") :: intFromString(number) :: tail => Min(number) :: apply(tail)
      case ("-a" | "--max") :: intFromString(number) :: tail => Max(number) :: apply(tail)
      case Nil       => Nil
      case x :: tail => Error(x) :: apply(tail)
    }
  }
}
