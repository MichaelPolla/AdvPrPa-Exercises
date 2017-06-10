/** Serie 4 - Pattern matching & Lists **/

// Question 1 - Working with expressions --------------------------------------
sealed abstract class Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Product(e1: Expr, e2: Expr) extends Expr

def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)
  case Product(e1, e2) => eval(e1) * eval(e2)
}

def show(e: Expr): String = e match {
  case Number(n) => n.toString
  case Sum(e1, e2) => show(e1) + " + " + show(e2)
  case Product(Sum(e1, e2), e3) => "(" + show(Sum(e1,e2)) + ") * " + show(e3)
  case Product(e1, Sum(e2, e3)) => s"${show(e1)} * (${show(Sum(e2,e3))})" // With string interpolation
  case Product(e1, e2) => show(e1) + " * " + show(e2)
}


val expr0 = Sum(Product(Number(2), Number(3)), Number(4))
println("Expr0: " + show(expr0))
assert(eval(expr0) == 10)

val expr1 = Product(Number(4), Number(12))
println("Expr1: " + show(expr1))
assert(eval(expr1) == 48)

val expr2 = Product(Sum(Number(2), Number(3)), Number(4))
println("Expr2: " + show(expr2))
assert(eval(expr2) == 20)

val expr3 = Product(Number(2), Sum(Number(3), Number(4)))
println("Expr3: " + show(expr3))
assert(eval(expr3) == 14)


// Question 2 - Defining trees ------------------------------------------------
sealed abstract class BinaryTree
case class Leaf(value: Int) extends BinaryTree
case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree

def leafSum(tree: BinaryTree) : Int = tree match {
  case Leaf(n) => n
  case Node(left, right) => leafSum(left) + leafSum(right)
}

val myTree = Node(Node(Leaf(3), Node(Leaf(8),Leaf(1))), Node(Leaf(5), Leaf(2)))
println(leafSum(myTree))

def smallest(tree: BinaryTree) : Int = {
  def min(x: Int, y: Int) = if (x < y) x else y

  def smallest(tree: BinaryTree, m: Int) : Int = {
    tree match {
      case Leaf(n) => if (n < m) n else m
      case Node(left, right) => min(smallest(left, m), smallest(right, m))
    }
  }
  smallest(tree, Integer.MAX_VALUE)
}

smallest(myTree)


// Question 3 - Lists functions -----------------------------------------------

// Returns the last element of a list
def last[A](l: List[A]): A = l match {
  case Nil => sys.error("last of empty list")
  case last :: Nil => last
  case head :: tail => last(tail)
}

// Returns a list of every element but the last
def init[A](l: List[A]): List[A] = l match {
  case Nil => sys.error("init of empty list")
  case last :: Nil => Nil
  case head :: tail => head :: init(tail)
}

// Returns the list with its elements in the reversed order
def reverse[A](l: List[A]): List[A] = l match {
  case head :: tail => reverse(tail) ++ List(head)
  case head => head
}

// Concatenates two lists together
def concate[A](list1: List[A], list2: List[A]): List[A] = list1 match {
  case Nil => list2
  case head :: tail => head :: concate(tail, list2)
}

// Returns the first n elements of the list
def take[A](list: List[A], n: Int): List[A] = n match {
  case 0 => Nil
  case _: Int =>  if (list.isEmpty) Nil else list.head :: take(list.tail, n - 1)

}

// Returns the list without its first n elements.
// If n > length(list), Nil should be returned.
def drop[A](list: List[A], n: Int): List[A] = n match {
  case 0 => list
  case _: Int => if(list.isEmpty) Nil else drop(list.tail, n - 1)
}

// Returns the nth element of a list.
def apply[A](list: List[A], n: Int): A = {
  drop(list, n).head
}
val intList = List(2,3,4,6,1)
val intList2 = List(8, 9)
val stringList = List("Bird", "Dog", "Cat", "Giraffe")

// Testing
last(intList)
last(stringList)
init(stringList)
reverse(stringList)
concate(intList, intList2)
take(stringList, 2)
drop(stringList, 2)
drop(stringList, 10)
apply(stringList, 2)


// Question 4 - Predicates on lists -------------------------------------------
def any[T](p: T => Boolean)(l: List[T]): Boolean = l match {
  case Nil => false
  case head :: tail => p(head) || any(p)(tail)
}


def every[T](p: T => Boolean)(l: List[T]): Boolean = l match {
  case Nil => false
  case head :: Nil => p(head)
  case head :: tail => p(head) && every(p)(tail)
}

val list = List(1,2,3,4)
every[Int]((x:Int) => if (x < 5) true else false)(list)