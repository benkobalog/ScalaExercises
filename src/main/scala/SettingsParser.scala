import scala.util.Try

object SettingsParser {

  object intFromString {
    def unapply(arg: String): Option[Int] = Try(arg.toInt).toOption
  }

  /** Produce a correct Setting object form command line arguments
    * Check out Setting.helpText for the parameters to be handled
    *  */
  def apply(args: List[String]): Setting = {
    ???
  }
}
