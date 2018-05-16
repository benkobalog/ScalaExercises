package scraper

object Helpers {
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) / 1000.0 / 1000.0 + "ms")
    result
  }

  def debug(message: String): Unit = {
    val now = java.time.format.DateTimeFormatter.ISO_INSTANT
      .format(java.time.Instant.now)
//      .substring(11, 23)
    val thread = Thread.currentThread.getName()
    println(s"$now [$thread] $message")
  }

  def generateUrl: () => String = {
    var id = 0
    def gen() = {
      id += 1
      "http://portfotolio.net/medvekoma?page=" + id
    }
    gen
  }
}
