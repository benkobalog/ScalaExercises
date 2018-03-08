### Implicit Parameters and Conversions

Our building blocks:
* Implicit parameters
* Implicit conversions (class and function)

What are they used for?
* Implicit context (implicit parameters)
* Extension methods (implicit conversion)
* Type classes

Be careful about
* Implicit type conversions, especially if they are too general like:
```scala
implicit def stringToInt(s: String): Int = s.toInt
``` 

```scala
def f(implicit ctx: Context) = {
  
}
```

#### Examples:

Type Class
* scala.math.Ordering
* scala.math.Numeric

Extension Method
* scala.Option.option2Iterable
* Predef.intWrapper 

Implicit Context
* scala.concurrent.Future needs ExecutionContext
* ActorSystem
* The Scala compiler also has a big Context object passed around everywhere, containing several mutable things


#### IntelliJ and Implicits

__The good__

implicits are marked with an underline.

* __Show implicit conversions:__ _Ctrl + Shift + q_ (don't press it on the method, but the class)
* __Show implicit parameters:__ _Ctrl + Shift + p_  
(I'm not sure what these are on Mac, but you can find them in the keymap if you search for implicit)

__The bad__

If there's no relevant implicit value 
in the scope the IDE doesn't mark it, unless you use _show implicit parameters_ 

#### Type Classes

Type Classes enable ad-hoc polymorphism. OOP languages usually use



Short example:
https://gist.github.com/davegurnell/a614c67e8d52c113d36d


#### Ok... but I still don't quite understand type classes/implicits

Read and understand TypeClassDemo.scala

This is a really great article about some more use cases of implicits
http://www.lihaoyi.com/post/ImplicitDesignPatternsinScala.html

I'd also recommend reading the relevant chapter of your favourite Scala book.
I found _Essential Scala_'s _Type Classes_ chapter really good:
https://underscore.io/training/courses/essential-scala/