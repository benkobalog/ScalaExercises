## Pattern Matching
You can meet pattern matching at two places:

Match expression:
```scala
sealed trait Fruit
case class Apple(color: String, weight: Double) extends Fruit
case class Orange(weight: Double) extends Fruit
case class Melon(weight: Double) extends Fruit

fnReturningAFruit match {
  case Apple(color, weight) => println(s"we have $weight kgs of $color apples")
  case o: Orange => println(s"we have ${o.weight} kgs oranges")
  case m @ Melon(weight) => println(s"we have ${m.weight} kgs of melon")
}
```
Partial Function:
```scala
List(1, 2, 3, 4, 5).collect { case x if x % 2 == 0 => x }
```


### Pattern matching vs if, switch
__if__ doesn't have any "magic", while __pattern matching__ can make use of extractors and type inspection and casting.

```scala
// Type inspection and casting
case a: A => a.stuff

if(a.isInstanceOf[A]) a.asInstanceOf[A].stuff

// Extraction
case Apple(_, weight) => aggregateWeight(weight)
// There's no counterpart, if and switch can't do this

case _ => // is the "default" or the last else branch
```
* In Scala both __if__ and __match__ are expressions, returning a value
* In Java __if__ and __switch__ are not expressions
* __match__ cases are not fallthrough

```scala
// | can be useful
case 'a' | 'b' | 'c' => doSomething

if (Set('a', 'b', 'c').contains(x)) doSomething

// It works on a list of strings too, end in generally on nested structures
listOfThings match {
  case (fruit @ ("apple" | "orange" | "melon" )) :: tail => Fruit(fruit)
  case x => OtherThings(x) 
}

```


### Extractors
Useful link: https://docs.scala-lang.org/tour/extractor-objects.html

"An extractor object is an object with an __unapply__ method. Whereas the __apply__ method is like a constructor which takes arguments and creates an object, the unapply takes an object and tries to give back the arguments. This is most often used in pattern matching and partial functions."

__apply__ takes some arguments and constructs and object, it works as a normal function.

```scala
object Adder {
    def apply(i: Int) = x + 1
}

Adder(3) == 4 // is the same as
Adder.apply(3) == 4 

class A(i: Int) 
object A { def apply(i: Int) = new A(i)} // The main purpose of this is that we don't have to use the new keyword
A(123) // can be used now instead of "new A(123)"
```

__unapply__ is a bit more unusual than apply. It takes an object and returns it's arguments, therefore it is used at value assignments.
```scala
// The most frequent use case is just extracting case class parameters
case class Person(name: String, age: Int)

val p = Person("Joe", 128)
val Person(name, _) = p
name == p.name // returns true

// Scala collections have unapply implemented on them so, you can use

val Array(firstName, secondName) = someFunctionReturingAnArrayOfAName()
// So unapply works as a weird value assignment method

```
__unapply__ can only have 3 types of return types as opposed to __apply__ where you can freely choose the return type.
* Option[T]
* Option[(T1, ..., Tn)] 
* Boolean

The return type seems to be restricted, but actually we can return any type T.
Option means if unapply returns Some[T] unapply will return the t: T value
and if unapply returns None, the pattern match goes to the next case. (Throws a MatchError outside a match expression or if it gets out of the match expression)
```scala
object intFromString {
  def unapply(arg: String): Option[Int] = Try(arg.toInt).toOption 
}

def fn(s: String) = s match {
  case intFromString(int) => int
  case _ =>
    println("This is not a number...") 
    0 
}

fn("1234") // 1234
fn("something") // 0
```

### Case Class
A case class in Scala is a normal class with automatically generated methods and companion object.

The generated methods are:
* equals
* hashcode
* toString
* copy
* accessor methods for constuctor arguments // You can make the compiler do this in normal classes too, by adding the _val_ keyword to an argument"class A(val i: Int)"
* Also: productIterator, productArity, productPrefix, productElement


The companion object also has some generated methods:
* apply   // We don't have to use the new keyword for case classes
* unapply // We can use case classes in pattern matching 


