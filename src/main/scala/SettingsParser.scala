import scala.util.Try

object SettingsParser {

  object intFromString {
    def unapply(arg: String): Option[Int] = Try(arg.toInt).toOption
  }

  def apply(args: List[String]): Setting = {
    args match {
      case ("-h" | "--help") :: tail => apply(tail).copy(isHelp = true)
      case ("-i" | "--min") :: intFromString(number) :: tail => apply(tail).copy(min = Some(number))
      case ("-a" | "--max") :: intFromString(number) :: tail => apply(tail).copy(max = Some(number))
      case Nil       => Setting.Empty
      case x :: _ => throw new IllegalArgumentException(x)
    }
  }
}
