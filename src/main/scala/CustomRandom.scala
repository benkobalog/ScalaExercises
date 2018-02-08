import java.util.concurrent.ThreadLocalRandom.current

case class CustomRandom(min: Int, max: Int, count: Int) {
  def nextInts(nrInts: Int = count) =
    (1 to nrInts).map(_ => current().nextInt(min, max)).toList

  def nextInt() = nextInts(1).head
}

object CustomRandom {
  def default() = CustomRandom(0, 10, 1)

  def fromSet(settings: List[Setting]): CustomRandom = {
    def loop(customRandom: CustomRandom,
             settings: List[Setting]): CustomRandom =
      settings match {
        case Nil => customRandom
        case setting :: rest =>
          val newCR = setting match {
            case Max(v)     => customRandom.copy(max = v)
            case m @ Min(v) => customRandom.copy(min = m.underlying)
            case h @ Help =>
              println(h.helpText)
              customRandom
            case Error(x) =>
              // We just skip invalid parameters for now
              customRandom
          }
          loop(newCR, rest)
      }
    loop(CustomRandom.default(), settings)
  }

  def alternativeFromSet(settings: List[Setting]): CustomRandom =
    settings.foldLeft(CustomRandom.default()) { (cr, setting) =>
      setting match {
        case Max(v)   => cr.copy(max = v)
        case Min(v)   => cr.copy(min = v)
        case h @ Help =>
          println(h.helpText)
          cr
        case Error(x) =>
          // We just skip invalid parameters for now
          cr
      }
    }
}
