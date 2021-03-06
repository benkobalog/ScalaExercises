## Compare Machine

You will build a machine which is able to verify the correctness of a simple statement like "2 is bigger than 1".
The machine has an unstable input, (eg: a program which tries to process human speech).
 
This unstable behaviour will be represented with the Option data type. 
When the input program can understand the speaker it will return ```Some[String]``` 
when it couldn't it will return ```None```.

The machine has 2 inputs. (The input are weird because the inventor of the machine
wants to show how Scala monads work)
* ```firstNumber: Option[Int]``` which can be a number from 0 to 9
* ```predicate: Option[String]``` which will contain the rest of the sentence. eg: "is bigger than 8". 
    * The second number has to be extracted from this String and is also a number from 0 to 9
    * There are two lists ```lessThanWords``` and ```greaterThanWords```, these contain the words which you have to find in the predicate
    * The order of the number and relation words doesn't matter

The output will be either an error message if something didn't make sense 
or the correctness of the statement.
Eg.:

* __input:__ (2, "is bigger than 4") __output:__ "Incorrect"
* __input:__ (2, "is lower than 4") __output:__ "Correct"
