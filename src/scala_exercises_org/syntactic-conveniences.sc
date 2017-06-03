/** SYNTACTIC CONVENIENCES
https://www.scala-exercises.org/scala_tutorial/syntactic_conveniences */

// STRING INTERPOLATION
def greet(name: String): String =
  s"Hello, $name!"
greet("Scala")

def greetComplexExpression(name: String): String =
  s"Hello, ${name.toUpperCase}!"
greetComplexExpression("Scala")

// TUPLES
def pair(i: Int, s:String): (Int, String) = (i, s)
pair(42, "foo")
pair (0, "bar")

// Manipulating Tuples

//  Retrieve elements
val is: (Int, String) = (42, "foo")
val (i, s) = is
is._1
is._2

// FOR EXPRESSIONS
val xs = List(1,2,3,4);
val ys = List(1,2,3,4);

// Map
xs.map(x => x + 1)
// can be written :
for (x <- xs) yield x + 1 // Can be read as "for every value, that I name 'x', in 'xs', return 'x + 1'"

// Filter
xs.filter(x => x % 2 == 0)
// can be written :
for (x <- xs if x % 2 == 0) yield x

// Combined, to see the benefit of this syntax :
for (x <- xs if x % 2 == 0) yield x + 1
// Equivalent to :
xs.filter(x => x % 2 == 0).map(x => x + 1)

//Flatmap
xs.flatMap(x => ys.map(y => (x, y)))

// Putting things together
for {
  x <- xs if x % 2 == 0
  y <- ys
} yield (x, y)
// Equivalent de-sugared code :
xs.filter { x =>
  x % 2 == 0
}.flatMap { x =>
  ys.map { y =>
    (x, y)
  }
}

// METHODS PARAMETERS
case class Range(start: Int, end: Int, step: Int)

// Named parameters
Range(start = 1, end = 10, step = 2)

// default values
case class RangeWithDef(start: Int, end: Int, step: Int = 1)

val rangeWithDef = RangeWithDef(start = 1, end = 10)
rangeWithDef.step

// Repeated parameters
def average(x: Int, xs: Int*): Double =
  (x :: xs.to[List]).sum.toDouble / (xs.size + 1)

average(1)
average(1, 2)
average(1, 2, 3)