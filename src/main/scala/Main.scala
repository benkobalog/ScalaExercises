object Main extends App {

  override def main(args: Array[String]): Unit = {
    println(args.toList)
    val settings = SettingsParser(args.toList)
    val result = CustomRandom.run(settings)
    println(result)
  }
}