/** Serie 5a - Higher-Order Functions on Lists **/

// Question 1 - Higher-order functions on lists -------------------------------
// Using only the zip and map higher-order functions. define the following functions that :

// (a) Returns a list of the length of each string of a list
def lengthStrings(l: List[String]): List[Int] = {
  l map (_.length())
}

lengthStrings(List("How", "long", "are", "we?"))

// (b) Produces a list with n identical elements of arbitrary type (without using the fill method)
def dup[A](l: A, n: Int): List[A] = {
  require(n >= 0)
  List.range(1, n + 1).map(x => l)
}

dup("foo", 5)
dup(List(1, 2, 3), 2)

// (c) Multiplies element-wise two lists of values and create a new list
def dot(list1: List[Int], list2: List[Int]): List[Int] = {
  list1 zip list2 map {case (a, b) => a * b}
}

dot(List(1, 2, 3), List(2, 4, 3)) // returns List(2, 8, 9)

// Question 2 - Folding functions on lists ------------------------------------
// Using folding (right or left), define a function that :

// (a) Determines if all logical values in a non-empty list are true

def areTrue(l: List[Boolean]): Boolean = {
  l.foldLeft(true)((result, elem) => result && elem)
}

areTrue(List(true, true, false, true))  // returns false
areTrue(List(true, true, true, true))   // returns true

// (b) Determines the total length of the strings in a list

def lString(l: List[String]): Int = {
  l.foldLeft(0)((acc, elem) => acc + elem.length)
}
lString(List("Folding", "is", "fun")) // returns 12

// (c) Returns the longest string of a list as well as its size

def longest(l: List[String]): (Int, String) = {
  l.foldLeft((0, ""))((acc, elem) => if (acc._1 < elem.length) (elem.length, elem) else acc)
}
longest(List("What", "is", "the", "longest?")) // returns (8, longest?)

// (d) Decides if a value is an element of a list of an arbitrary type

def isPresent[A](l:List[A], e: A): Boolean = {
  l.foldLeft(false)((acc, elem) => elem == e || acc)
}

isPresent(List(1, 2, 3, 4), 5) // returns false
isPresent(List(1, 2, 3, 4), 3) // returns true
isPresent(List("Hi", "how", "are", "you?"), "are") // returns true

// (e) Flatten a nested list structure of any type

def flattenList(l: List[Any]): List[Any] = {
  l.foldRight(List.empty[Any]) {
    case (t: List[_], r) => flattenList(t) ++ r
    case (t: Any, r) => t :: r
  }
}
flattenList(List(List(1, 1), 2, List(3, List(5, 8)))) // returns List (1, 1, 2, 3, 5, 8)

