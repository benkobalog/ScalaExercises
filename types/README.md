# Advanced Types in Scala

## Polymorphism
Polymorphism is the provision of a single interface to entities of different types.

* Subtyping 
    * Method overriding
* Parametric
    * "Generics"
    * We don't know anything about the type, so we can't use any of its properties
* Ad-hoc
    * Each type can have its own implementation
    * __Type classes__
* Bounded parametric
    * We might require some properties for our types but otherwise they can be parametric
    * __Type bounds__ = Subtyping + Parametric ```[A <: Comparable[A]]```
    * __Context bounds__ = Ad-hoc + Parametric ```[A : Numeric]```

## Topics
* Variance
* Type Bounds
* Higher Kinded Types
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

## Variance
* Subtyping relationship of a "container" class varying with the subtyping relationship of a "contained" class.
* Covariance is marked with _+_ (_out_ in other languages)
* Contravariance is marked with _-_ (_in_ in other languages)
* "Contravariance" "Consumes"
* _Variance is only applicable to classes and traits not to functions!_


## Type Bounds
```scala
def f[T <: Comparable[T]](t: T) = ???
def f[T >: Comparable[T]](t: T) = ???
```
In Java ```<:``` corresponds to ```extends```.

```java
public <T extends Comparable<T>> void f(T t) {}
```

| Name | Syntax | Semantics |
| --- | --- | --- |
| upper bound |```A <: B```  | ```A``` is a subtype of ```B```
| lower bound |```A >: B```  | ```A``` is a supertype of ```B```

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
* [Type related features in Scala](http://ktoso.github.io/scala-types-of-types/)
* [Long and easy to understand article about variance in Java and Scala](https://medium.com/@sinisalouc/variance-in-java-and-scala-63af925d21dc)
* [Type Constraints](http://blog.bruchez.name/2015/11/generalized-type-constraints-in-scala.html)
* [Value Classes](https://docs.scala-lang.org/overviews/core/value-classes.html)
