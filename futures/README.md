## Future

Asynchronous + Try effects

Future starts executing in the moment it's declared.

```scala
doSomething1()
val f1 = Future(doSomething2())
doSomething3()
```
and starts running on an execution context. 
The only way to get results back to our "main thread"
is Await.
```scala
import concurrent.Await
import concurrent.duration._
val result = Await.result(f1, 1.second)
```
Otherwise we can compose computations with callbacks or in a monadic way
with map, flatMap.





### Future's different constructors
```scala
import concurrent.Future
val expr = () => 1
Future(expr)                     // Starts executing the expr
Future.successful(expr)          // Creates a completed successful Future
Future.failed(new Exception("")) // Creates a completed failed Future

// Try these in repl:
Future.failed(new Exception(""))       // Creates: Future[Nothing]: Failure(java.lang.Exception: )
Future.failed(throw new Exception("")) // This constructor doesn't catch the exception so it throws an exception
Future(throw new Exception(""))        // Starts executing and returns Future[Nothing]: Failure(java.lang.Exception: )

// These return Future[Try[T]] not Future[T]
Future(Success(expr))
Future(Failure(new Exception()))
```

### ExecutionContext

```scala
import concurrent.ExecutionContext.Implicits.global
```
For CPU intensive tasks.

It's usually recommended to use a different thread pool for IO
eg: Slick has an own thread pool


### Things to avoid

1. Calling Future.apply inside a for comprehension
```scala
for {
  a <- Future(1)
  b <- Future(2)
  c <- Future(3)
} yield a + b + c

// Desugars to (the second one only starts after the first returned):
Future(1)
  .flatMap( a =>
    Future(2)
     .flatMap(b =>
       Future(3).map(c => a + b + c)))
            
// Do this instead:
val aF = Future(1) // These 3 will start executing immediately
val bF = Future(2)
val cF = Future(3)
for {
  a <- aF
  b <- bF
  c <- cF
} yield a + b + c

// This desugars to:
aF.flatMap(a =>
  bF.flatMap(b =>
    cF.map(c => a + b + c)))
```
