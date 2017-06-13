/** Lesson 5b : FOR-COMPREHENSIONS **/

// For-comprehension example (p.41) -------------------------------------------------------------
case class Person(name: String, age: Int)
val persons = List(
  Person("John", 23),
  Person("Mary", 30),
  Person("Alex", 22),
  Person("Cindy", 40))

for (p <- persons)
  println(p)

val names = for {
  p <- persons if p.age > 25
} yield p.name      // Yields List(Mary, Cindy)

val names2 = for (
  p <-persons
  if p.age > 25
  if p.name.startsWith("C")
) yield p.name    // Yields List(Cindy)

val myList = for { i <- 1 to 10 if (i < 5)} yield i // Vector(1, 2, 3, 4)
println(myList)

// Translating for-comprehensions to map, filter and flatMap (p.42-44) --------

// to map :
// for (x <- e) yield e'  -->  e.map(x => e')

// to filter (with "if f" a filter and "s" a sequence of generators and filters) :
// for (x <- e if f; s) yield e'  -->  for(x <- e.filter(x => f); s) yield e'

// to flatMap (where "s" is a sequence (possibly empty of generators and filters) :
// for (x <- e; y <- e', s) yield e''  -->  e.flatMap(x => for(y <- e'; s) yield e'')

for (x <- 1 to 2;
     y <- 'a' to 'b') yield (x, y)  // Yields Vector((1,a), (1,b), (2,a), (2,b))

(1 to 2).flatMap(x => ('a' to 'b').map(y => (x, y))) // Returns Vector((1,a), (1,b), (2,a), (2,b))


// Exhaustive combinations (p.49) ---------------------------------------------

def primeSum(max: Int) : List[(Int, Int)] =
for {
  i <- (1 to max).toList
  j <- (1 to max).toList
  if (isPrime(i + j))
} yield (i, j)

// A possible prime function, using matching
def isPrime(i : Int): Boolean =
  i match {
    case i if i <= 1 => false
    case 2 => true
    case _ => !(2 to (i -1)).exists(x => i % x == 0)
  }