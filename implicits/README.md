# Implicit Parameters and Conversions

Our building blocks:
* Implicit parameters

```scala
 def map[S](f: T => S)(implicit executor: ExecutionContext): Future[S]
```

* Implicit conversions (class and function)
```scala
implicit def option2Iterable[A](xo: Option[A]): Iterable[A] = xo.toList

implicit class OptionConverter[A](option: Option[A]) {
  def toTry(failure: Throwable): Try[A] =
    option match {
      case Some(v) => Success(v)
      case None    => Failure(failure)
    }
}
``` 

What are they used for?
* Implicit context (implicit parameters)
* Extension methods (implicit conversion)
* Type classes (mostly parameters, optionally conversion too)
* and more...

Be careful about
* Implicit type conversions, especially if they are too general like:
```scala
implicit def stringToInt(s: String): Int = s.toInt
``` 


#### Examples from the Scala ecosystem:

Type Class
* scala.math.Ordering
* scala.math.Numeric

Extension Method
* Predef.intWrapper 

Implicit Context
* scala.concurrent.Future needs ExecutionContext
* ActorSystem
* The Scala compiler has a big Context object passed around everywhere, containing several mutable things


#### IntelliJ and Implicits

__The good__

Implicits are marked with a grey underline (both conversion and parameters).

| | Implicit Parameters | Implicit Conversions| 
| -----| ----- | ---- | 
| Shortcuts | _Ctrl + Shift + p_ | _Ctrl + Shift + q_ |
| Mac shortcuts | _Command + Shift + p_ | _Ctrl + q_ |  

__The bad__

If there's no relevant implicit value 
in the scope the IDE doesn't mark it, unless you use _show implicit parameters_ 

#### Extension Methods
Can add a new method to an already existing class.

```scala
implicit class OptionConverter[A](option: Option[A]) {
  def toTry(failure: Throwable): Try[A] =
    option match {
      case Some(v) => Success(v)
      case None    => Failure(failure)
    }
}
``` 
The class you want to extend has the same type as the single parameter of the implicit class.
(in the above case _Option[A]_).
You can use type parameters so you could extend
_Option[A], Option[Int]_ or even just _A_.


#### Type Classes

##### Polymorphism
* Parametric _(we don't specify anything about the types, commonly referred as_ generics _)_
```scala
 def map[B](f: A => B): Option[B] =
     if (isEmpty) None else Some(f(this.get))
```
* Subtyping _(types are in a subtyping relationship)_
```scala
 trait Adder { def add(x: Int): Int }
 object Add2 extends Adder {
   override def add(x: Int): Int = x + 2
 }
 object Add3 extends Adder {
   override def add(x: Int): Int = x + 3
 }
```
* Ad hoc _(Heterogeneous set of types, but specific types not like with parametric polymorphism)_

Type classes are useful when you want to implement common 
functionality for an arbitrary set of types.

Eg.: you want to implement a function which works on String, List and Future as well, but not on other types.

Some examples:
* Json converters
* The pure FP side of Scala prefers it over OOP inheritance (Cats library is built from type classes)


#### Ok... but I still don't quite understand type classes/implicits

Read and understand TypeClassDemo.scala

This is a really great article about some more use cases of implicits
http://www.lihaoyi.com/post/ImplicitDesignPatternsinScala.html

I'd also recommend reading the relevant chapter of your favourite Scala book.
I found _Essential Scala_'s _Type Classes_ chapter really good:
https://underscore.io/training/courses/essential-scala/