object PipeOps {

  implicit class Pipe[A](a: A) {
    def |>[B](f: A => B): B = f(a)
  }
}
