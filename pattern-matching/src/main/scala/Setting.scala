case class Setting(min: Option[Int], max: Option[Int], isHelp: Boolean)

object Setting {
  val Empty = new Setting(None, None, false)

  val helpText: String =
    """|Options:
       |-h, --help
       |  Print help text
       |
       |-i, --min
       |  Lower bound for random generation
       |
       |-a, --max
       |  Upper bound for random generation
    """.stripMargin
}


