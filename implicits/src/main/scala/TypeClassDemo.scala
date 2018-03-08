// This file is from: https://gist.github.com/davegurnell/a614c67e8d52c113d36d

object TypeClassDemo {

  // The parts of the type class pattern are:
  //
  //   1. the "type class" itself -- a trait with a single type parameter;
  //
  //   2. type class "instances" for each type we care about,
  //      each marked with the `implicit` keyword;
  //
  //   3. an "interface" to the type class -- one or more methods
  //      with `implicit` parameter lists.
  //
  // Let's explore this further by looking at a worked example,
  // split into library and application code:

  // Library ====================================

  // The type class
  //
  // The type class itself is a trait with a single type parameter.

  trait HtmlWriter[A] {
    def write(value: A): String
  }

  // Type class interface
  //
  // The interface consists of one or more methods
  // that accept type class instances as an `implicit` parameter list.

  def toHtml[A](value: A)(implicit writer: HtmlWriter[A]): String =
    writer.write(value)

  // Here we define a convenience constructor for type class instances.
  //
  // This isn't a core part of the type class pattern,
  // but it helps keep the instance definitions short.

  object HtmlWriter {
    def apply[A](func: A => String) = new HtmlWriter[A] {
      def write(value: A): String = func(value)
    }
  }

  // Type class instances
  //
  // Libraries typically ship with
  // a set of default instances for common data types.
  //
  // Each is a `val`, `var`, `def`, or `object` tagged
  // with the keyword `implicit`.

  implicit val intWriter: HtmlWriter[Int] =
    HtmlWriter((value: Int) => value.toString)

  implicit val stringWriter: HtmlWriter[String] =
    HtmlWriter(
      (value: String) => value.replaceAll("<", "&lt;").replaceAll(">", "&gt;"))

  // Application ================================

  // Data types for our application

  case class Email(address: String)
  case class Person(name: String, email: Email, age: Int)

  // Additional type class instances
  //
  // We create additional instances of the type class for
  // data types in our application.

  implicit val emailWriter: HtmlWriter[Email] =
    HtmlWriter((email: Email) => email.address.replaceAll("@", " at "))

  implicit val personWriter: HtmlWriter[Person] =
    HtmlWriter { (person: Person) =>
      toHtml(person.name) + " " +
        toHtml(person.email) + " " +
        toHtml(person.age)
    }

  // Using the type class
  //
  // We typically use the type class via one of its interface methods.
  //
  // Here is the canonical use case -- we specify the data we want to write
  // and allow the compiler to fill in the implicit parameters automatically:

  toHtml(Person("Dave", Email("dave@example.com"), 36))

  // We are always free to specify the implicit parameters explicitly,
  // bypassing the implicit lookup system:

  toHtml(123)(intWriter)

  // Finally, if we really want to, we can use the instances directly:

  stringWriter.write("Dave <dave@example.com>")
}
