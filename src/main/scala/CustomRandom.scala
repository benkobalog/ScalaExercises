import scala.util.Random

object CustomRandom {

  /** None when min is greater than max
    * else return a random number in the interval
    */
  def nextInt(min: Int, max: Int) = {
    if (max < min) None
    else Some(min + Random.nextInt(max - min))
  }

  val defaultMax = 10
  val defaultMin = 0

  /** Generates an Integer number based on the settings parameter
    * @param settings
    * @return Some(int) when settings.isHelp is false
    *         None when settings.isHelp is true
    *         When either settings.max or settings.min is None use the corresponding defaultMax or defaultMin value
    */
  def run(settings: Setting): Option[Int] = settings match {
    case Setting(_, _, true)              => println(Setting.helpText); None
    case Setting(None, Some(max), _)      => nextInt(defaultMin, max)
    case Setting(Some(min), None, _)      => nextInt(min, defaultMax)
    case Setting(Some(min), Some(max), _) => nextInt(min, max)
    case Setting(None, None, _)           => nextInt(defaultMin, defaultMax)
  }

}
