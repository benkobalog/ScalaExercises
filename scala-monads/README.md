![image0](https://image.slidesharecdn.com/monadicjava-forpdf-131109061340-phpapp02/95/monadic-java-4-638.jpg?cb=1455559018)

## Algebraic Data Type

An ADT consists of a combination of _Sum Types_ and _Product Types_.

They are modelled in the following way in Scala:
```scala
sealed trait MySumType
case class ProductType1(a: Int, b: String) extends MySumType
case class ProductType2(a: Int, b: String, c: Int, d: Long) extends MySumType
```

```scala
sealed trait Option[A]
case class Some[A](value: A) extends Option[A]
case object None extends Option[Nothing]


sealed trait Either[A, B] 
case class Right[A, B](value: A) extends Either[A, B]
case class Left[A, B](value: B) extends Either[A, B]
```

## Monads in Scala Standard Library
  * Option
  * Either
  * Try
  * Future
  * List (and other collections members)
 
What's common?

They don't seem to be doing similar things.

All of them have a flatMap method. 

```scala
final def flatMap[B](f: A => Option[B]): Option[B] = ???
final def map[B](f: A => B): Option[B] = ???
// A is the type of the Original Option[A] which has these methods
```

```map``` is a special case of ```flatMap```, 
```flatMap``` can do a bit more things, but many times map is enough.

![image1](https://porizi.files.wordpress.com/2014/02/map.png)
![image2](https://porizi.files.wordpress.com/2014/02/flatmap.png)


#### For Comprehension

For Comprehension is just syntactic sugar. It is transformed to map or flatMap (or filter or foreach) calls.
When you would only call one map or flatMap use them, but after 2-3 nested map calls
```for``` can be a better solution.

```scala
for {
 x <- List(0, 1)
 y <- List(0, 1)
} yield (x, y)
// List[(Int, Int)] = List((0,0), (0,1), (1,0), (1,1))
```

### Fail Fast
When used for error handling monads have a so called __fail fast__ behaviour.

![imageff1](https://image.slidesharecdn.com/railway-oriented-programming-slideshare-140312155941-phpapp01/95/railway-oriented-programming-74-638.jpg?cb=1427456657)
![imageff1](https://image.slidesharecdn.com/railway-oriented-programming-slideshare-140312155941-phpapp01/95/railway-oriented-programming-78-638.jpg?cb=1427456657)

Option, Either(v2.12), Try and Future works this way. (The 
exception is List and Either(v2.11))

```scala
val validatedObject: Try[Object1] = 
    for {
      object1 <- validate
      _ <- updateDB(object1)
      _ <- sendMail(object1)
    } yield object1
// Or
val validatedObject: Try[Object1] = 
    object1.map{ obj =>
      updateDb(obj).map{ _ => 
        sendMail(obj).map{ _ =>
          obj
        }
      }
    }
```


#### Effects
| Name | Effect| 
| ----- | ---- | 
| Option | Absence of a result/value, usually null handling |
| Either | Union type or Error Handling (expected errors) |
| Try | Error handling (unexpected + expected errors) |
| Future | Async + Error handling (unexpected + expected errors)  |
| List | Multiple values |
  

  
  

 References: 
  * https://porizi.wordpress.com/2014/02/21/flatmap-explained/
  * https://www.youtube.com/watch?v=E8I19uA-wGY&t=1s (watch this, it's funny)
