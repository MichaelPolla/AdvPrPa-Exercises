
/** Exercise 1, p.22 **/

def patFoo(x: Any) = {
  x match {
    case x : Int if (x % 4 == 0) => true
    case x : Char => x.isUpper
    case x : Boolean => true
    case _ => false
  }
}

patFoo(8)

patFoo(9)
patFoo('A')
patFoo('a')
patFoo(false)

/** End of Exercise 1 **/

/** Exercice 2, p.38 **/


def isort(xs: List[Int]): List[Int] =
  if (xs.isEmpty) Nil
  else insert(xs.head, isort(xs.tail))

def insert(elem: Int, l: List[Int]): List[Int] = {
  if(l.isEmpty)
    List(elem)
  else if (elem < l.head)
    elem :: l
  else
    l.head :: insert(elem, l.tail)
}

// pattern-matching version (p.40)
def insert2(elem: Int, l: List[Int]): List[Int] = {
  l match {
    case Nil => List(elem)
    case head :: rest => {
      if(elem < head)
        elem :: l
      else
        head :: insert(elem, rest)
    }
  }
}

insert(3, Nil)
insert(3, 4::Nil)
insert(3, 2::Nil)
isort(List(7, 3, 9, 2))