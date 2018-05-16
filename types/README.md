# Generic Programming Features in Scala

More advanced type features help us to write code which is more generic
but still type safe (checked by the compiler). 
The primary use of these features are in libraries, not in user/application code.
However it's still useful to know about these at least when you are looking at library code.

## Types of polymorphism

* Subtyping 
    * Method overriding
* Parametric
    * "Generics"
    * We don't know anything about the type, so we can't use any of its properties
* Bounded quantification
    * Subtyping + Parametric, we might want to give constraints to our type parameters
    * __Type bounds__
* Ad-hoc
    * Each type can have its own implementation
    * __Type classes__


## Topics
* Type Bounds
* Higher Kinded Types
* Variance
* Context Bounds
* Bonus: Type Constraints

## Parametric polymorphism in Scala
```scala
class Stack[T] {
    var elems: List[T] = Nil
    def push(x: T) { elems = x :: elems }  
    def top: T = elems.head  
    def pop() {  elems  =  elems.tail  }  
}
```

## Type Bounds
```scala
def f[T <: Comparable](t: T) = ???
```

## Variance
* Subtyping relationship of a "container" class varying with the subtyping relationship of a "contained" class.
* Covariance is marked with _+_ (_out_ in other languages)
* Contravariance is marked with _-_ (_in_ in other languages)
* "Contravariance" "Consumes" 

#### Variance vs Bounds
Bounds: Useful when you want to be generic but require a certain set of methods
Variance: Useful when you want to make a collections that behave the same way as the contained classes

## Higher kinded types

### Examples
* Monad
* sttp library

### What

## Context Bounds


#### Good sources
Type related features in Scala:
http://ktoso.github.io/scala-types-of-types/

Type Constraints:
http://blog.bruchez.name/2015/11/generalized-type-constraints-in-scala.html

Value Classes:
https://docs.scala-lang.org/overviews/core/value-classes.html

trait Animal
class Mammal extends Animal
class Zebra extends Mammal