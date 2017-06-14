/** Lesson 5a : ADVANCED LISTS **/

// Some useful list functions (p.6) -------------------------------------------

val intList = List(4, 1, 4, 12, 7, 8)
val stringList = List("cat", "cat", "bird", "dog")
val listFromRange = List.range(1,9,2) // List(1,3,5,7)

stringList.contains("cat")      // returns true
stringList.contains("giraffe")  // returns false
stringList.last                 // returns dog
stringList.init                 // returns List(cat, cat, bird)
stringList.distinct             // returns List(cat, bird, dog)
stringList.take(2)              // returns List(cat, cat)
stringList.drop(2)              // returns List(bird, dog)
stringList.apply(2)             // returns bird (remember that index starts at 0)
intList.sum                     // returns 36


// reduceLeft demo (p.15) -----------------------------------------------------
val list = List(4,1,12,7)
list reduceLeft ((x: Int, y:Int) => x + y) // returns 24

list reduceLeft((x, y) => x min y)  // returns 1. Formally: min(min(min(4,1), 12), 7))

list reduceLeft (_ max _)           // returns 12. Shorter, but can be hard to read



def verboseMax(x: Int, y: Int) = {
  val winner = x max y
  println(s"compared $x to $y, $winner was larger") // with string interpolation
  winner
}

list reduceLeft verboseMax


def concat(a: Any, b: Int) = a + "-" + b
// def concat(a: Any, b: Int) = a + "-" + b  ne marchera pas car Any
// n'est pas une sous-classe de Int(on devrait faire un reduceRight)
list reduceLeft concat


// foldRight example (p.17) ---------------------------------------------------

val list2 = List (1,2,3,4,5)
list2.foldRight(0)((x, y) => x + y)

// Exercise 2 (p.23) ----------------------------------------------------------
def length[A] (x : List[A]) : Int = {
  (x foldRight (0))((x:A, y:Int) => y + 1)
}
length(list2)

def map[A, B] (x: List[A], f: A => B) : List[B] = {
  (x foldRight List.empty[B])((x:A, y: List[B]) => f(x)::y)
}

val l4 = List(List(1), List(3,4))
l4.flatten    // List(1, 3, 4)

val l5 = List(1,2,3)
val l6 = List(4,5,6)
l5.zip(l6)    // List((1,4), (2,5), (3,6))

val l7 = List(3,4,5,2,2,5,6)
l7.sortWith(_ < _) // Order the list

//p.26
def dup[A](l: List[A]):List[A] = {
  def rep[A](l: A) = List[A](l, l) // réplique un élément
  l.flatMap(rep)
}

// v2
def rep[A](l: A) = List[A](l, l) // réplique un élément
def dup2[A] (l: List[A]) : List[A] = l.flatMap(rep)
def dup3[A] (l: List[A]) : List[A] = l.flatten(rep) // pourquoi pas besoin du map ?!?
dup(List(1,2,3))
dup2(List(1,2,3))
dup3(List(1,2,3))

// SUITE du 29.03
def merge(x : List[Int], y : List[Int]) : List[Int] = {
  (x, y) match {
    case (x, Nil) => x
    case (Nil, y) => y
    case (e1 :: rest1, e2 :: rest2) => {
      if (e1 <= e2)
        e1::merge(rest1, y)
      else
        e2::merge(x, rest2)
    }
  }
  val n1 = List(2, 3, 4, 6)
  val n2 = List(1, 7)
  merge (n1, n2) // Je veux List(1, 2, 3, 4, 5, 6, 7) comme résultat
  }

// EXERCICE 3
val b = (1 to 10).toList

/** First step in the right direction
  *
  * @param f
  * @param a
  * @return
  */
def filter( f : Int => Boolean, a:List[Int]) : List[Int] = {
  a match {
    case Nil => Nil
    case e :: rest =>
      if (f(e))
        e :: filter(f, rest)
      else
        filter(f, rest)
  }
  filter(_ % 2 == 0, b)
}

///**
//  * A first solution, note the nice usage of andThen for function composition.
//  * This solution requires two passes...not so nice
//  * Note : pas vu donc on pouvait pas trouver ça
//  * @param f
//  * @param a
//  * @return
//  */
//def partition( f : Int => Boolean, a:List[Int]) : (List[Int], List[Int]) = {
//  val tv = filter(f, a)
//  val tv2 = filter(f andThen ((x: Boolean) => !x), a)
//  //partition(tv, tv2)
//
//}
//partition(_ % 2 == 0, b)

// Very nice solution of a student
def partition3(f: Int => Boolean, a: List[Int]): (List[Int], List[Int]) = {
  a.foldRight((List.empty[Int], List.empty[Int])) {
    case (e, (l, r)) if f(e) => (e :: l, r) //e : element, l:left, r:right
    case (e, (l, r))        => (l, e :: r)
  }
}