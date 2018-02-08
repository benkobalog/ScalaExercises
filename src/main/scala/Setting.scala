sealed trait Setting
case class Min(underlying: Int) extends Setting
case class Max(underlying: Int) extends Setting
case class Error(msg: String) extends Setting
case object Help extends Setting {
  val helpText =
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