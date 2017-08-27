/**
  * Lesson 9 : Domain Specific Languages (DSL)
  */

// Sample 1 : Code blocks (p.10) ------------------------------------

// Simple block passing, note the by name call
def time(f: => Unit) = {
  val start = System.currentTimeMillis
  f // Execute the block
  val duration = System.currentTimeMillis - start // duration in ms
  duration
}

time(
  (1 to 100000).reverse.sorted
)


// Implicit conversion functions (p.13) -----------------------------
class Rational(x: Int, y: Int) {
  def this(n: Int) = this(n, 1)
  def numer: Int = x
  def denom: Int = y
}
implicit def intToRat(x: Int) = new Rational(x)

val f1: Rational = 2

// Implicit classes (p.17) ------------------------------------------

implicit class PimpedString(s: String) {
  def increment = s.map(c=>(c+1).toChar)
}

val c : PimpedString = "HAL"
println("HAL".increment)