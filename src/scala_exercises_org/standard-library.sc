// Manipulating lists
// https://www.scala-exercises.org/scala_tutorial/standard_library

val nums = Nil.::(4).::(3).::(2).::(1)

nums match {
  // Lists of `Int` that starts with `1` and then `2`
  case 1 :: 2 :: xs =>
  // Lists of length 1
  case x :: Nil =>
  // Same as `x :: Nil`
  case List(x) =>
  // The empty list, same as `Nil`
  case List() =>
  // A list that contains as only element another list that starts with `2`
  //case List(2 :: xs) => ???
}

// Either
def triple(x: Int): Int = 3 * x

def tripleEither(x: Either[String, Int]): Either[String, Int] =
  x.right.map(triple)

tripleEither(Right(1))
tripleEither(Left("not a number"))