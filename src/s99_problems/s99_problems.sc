// My tries, mixed with original answers of s99 - Ninety-nine Scala problems
// http://aperiodic.net/phil/scala/s-99

// P01 Find the last element of a list. ---------------------------------------
def lastBuiltin[A](list: List[A]): A = {
  list.last;
}

def lastRecursive[A](list:List[A]): A = list match {
  case lastElem :: Nil => lastElem
  case _ :: tail => lastRecursive(tail)
  case _ => throw new NoSuchElementException
}
lastBuiltin(List(1, 1, 2, 3, 5, 8))
lastRecursive(List(1, 1, 2, 3, 5, 8))