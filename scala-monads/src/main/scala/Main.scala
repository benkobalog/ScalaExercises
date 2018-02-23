object Main extends App {

  def hearFirstNumber(): Option[Int] = Some(4)
  def hearPredicate(): Option[String] = Some("than 5")

  val result = CompareMachineSolution.isCorrect(hearFirstNumber(), hearPredicate())

  println(CompareMachineSolution.showResult(result))

}
