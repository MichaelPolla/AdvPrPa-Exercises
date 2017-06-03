// STRUCTURING INFORMATION
// https://www.scala-exercises.org/scala_tutorial/structuring_information

// CASE CLASSES, SEALED TRAITS
sealed trait Symbol
case class Note(name: String, duration: String, octave: Int) extends Symbol
case class Rest(duration: String) extends Symbol

val c3 = Note("C", "Quarter", 3)
c3.name
c3.duration
c3.octave

val symbol1: Symbol = Note("C", "Quarter", 3)
val symbol2: Symbol = Rest("Whole");

// PATTERN MATCHING

def symbolDuration(symbol: Symbol): String =
  symbol match {
    case Note(name, duration, octave) => duration
    case Rest(duration) => duration
  }

// ENUMERATIONS
sealed trait NoteName
case object A extends NoteName
case object B extends NoteName
case object C extends NoteName
case object D extends NoteName
case object E extends NoteName
case object F extends NoteName
case object G extends NoteName