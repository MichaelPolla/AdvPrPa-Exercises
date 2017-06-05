/** PATTERN MATCHING AND LISTS **/

/** PATTERN MATCHING **/
// Matching on Types examples (p.21) ------------------------------------------

def typeMatch(x: Any) = {
  x match {
    case a: Int => "Got an int, " + a
    case b: String => "The string " + b
    case _ => "Don't know what it was"
  }
}

def guardMatch(x: Any) = {
  x match {
    case a: Int if (a % 2 != 0) => "Odd int, " + a
    case b: String if (b.length > 4) => b + " is long"
    case c: String if (c.length <= 4) => c + "is short"
    case _ => "Don't know what it was"
  }
}

guardMatch(1) // Odd int, 1
guardMatch(2) // Don't know what it was
guardMatch("Hello Scala") // Hello Scala is long


// Exercise 1 (p.22) ----------------------------------------------------------
// Implement a function that returns true if the parameter is :
// - an integer which can be divided by 4 without rest
// - an uppercase letter
// - a boolean value
// Otherwise it should return false

def patFoo(x: Any) = {
  x match {
    case x: Int if x % 4 == 0 => true
    case x: Char => x.isUpper
    case x: Boolean => true
    case _ => false
  }
}

patFoo(8)     // true
patFoo(9)     // false
patFoo('A')   // true
patFoo('a')   // false
patFoo(false) // true

/** LISTS **/
// Useful functions listed at the end

// Lists examples (p.33-34) ---------------------------------------------------
val names: List[String] = List("Roger", "Ana", "Paul")
val nums: List[Int] = List(1, 2, 3, 4)
val diag3: List[List[Int]] = List(List(1, 0, 0),
                                  List(0, 1, 0),
                                  List(0, 0, 1))

// Every list is built from empty list Nil and operator :: (cons)
val fruits = "apples"::("kiwi"::Nil) // <-> fruits: List[String] = List(apples, kiwi)
val nums2 = 1::(2::(3::(4::Nil)))

// Sorting a list (p.37) + Exercise 2 (p.38) ----------------------------------
// Implement the insert function, using recursion.

def insertionSort(xs: List[Int]): List[Int] =
  if (xs.isEmpty) Nil
  else insert(xs.head, insertionSort(xs.tail))

def insert(elem: Int, l: List[Int]): List[Int] = {
  if(l.isEmpty)
    List(elem)
  else if (elem < l.head)
    elem :: l
  else
    l.head :: insert(elem, l.tail)
}

// Pattern-matching version (p.40) --------------------------------------------
def insert2(elem: Int, l: List[Int]): List[Int] = {
  l match {
    case Nil => List(elem)
    case head :: tail => {
      if(elem < head)
        elem :: l
      else
        head :: insert2(elem, tail)
    }
  }
}

insert(3, Nil)                  // List(3)
insert(3, 4::Nil)               // List(3, 4)
insert(3, 2::1::5::4::Nil)      // List(2, 1, 3, 5, 4)
insert2(3, 2::1::5::4::Nil)     // List(2, 1, 3, 5, 4)
insertionSort(List(7, 3, 9, 2)) // List(2, 3, 7, 9)


// Genericity example (p.43) --------------------------------------------------
def length[T](l: List[T]): Int = {
  l match {
    case Nil => 0
    case x :: xs => length(xs) + 1
  }
}


// Append two lists (p.44) ----------------------------------------------------
List(1, 2, 3) ++ List(4, 5, 6) // in fact, ++ falls back on ::: (legacy)
// Discussion about ::: vs ++ : https://stackoverflow.com/q/6559996/1975002

// Useful functions on lists (p.45) -------------------------------------------
// length   : returns number of elements in the list
// last     : return the last element
// init     : returns all the elements but the last
// reverse
// take(n)  : returns the first n elements
// drop(n)  : returns everything but the first n elements
// apply(n) : returns the nth element