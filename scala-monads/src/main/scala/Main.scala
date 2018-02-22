object Main extends App {

  def hearFirstNumber(): Option[Int] = Some(2)
  def hearPredicate(): Option[String] = Some("is bigger than 1")

  val result = CompareMachine.compare(hearFirstNumber(), hearPredicate())

  println(CompareMachine.showResult(result))

}
