class Rational(n: Int, d: Int) {
  require(d != 0, "denominator must be positive")

  // Auxiliary constructor
  def this(n: Int) = this(n, 1)

  // Note : recursive method must specify a return type
  private def gcd(x: Int, y: Int) : Int = if (y == 0) x else gcd(y, x%y)
  private val g = gcd(n, d)

  def num: Int = n / g
  def denom: Int = d / g

  // Add
  def +(that: Rational): Rational = {
    new Rational(num * that.denom + that.num * denom,
      denom * that.denom)
  }

  // Minus
  def -(that: Rational): Rational = {
    new Rational(num * that.denom - that.num * denom,
      denom * that.denom)
  }

  // Times
  def *(that: Rational): Rational = {
    new Rational(num * that.num, denom * that.denom)
  }

  // Divided
  def /(that: Rational): Rational = {
    new Rational(num * that.denom, denom * that.num)
  }

  // Equal
  def ==(that: Rational): Boolean = {
    num * that.denom == denom * that.num
  }

  // Neg
  def unary_-(): Rational = {
    new Rational(-num, denom)
  }

  def less(that: Rational): Boolean = {
    this.num * that.denom < this.denom * that.num
  }
  def max(that: Rational): Rational = {
    if(this.less(that)) that else this
  }

  override def toString: String = num + "/" + denom
}

object Rational { // Object companion
  implicit def intToRat(x: Int): Rational = new Rational(x)
}

val r1 = new Rational(2, 3)
r1.num
r1.denom
r1.unary_-()

val r2 = new Rational(4, 3)
r2.num
r2.denom

val r3 = new Rational(5)
val r4 = 3 * r1
// val r5 = new Rational(6, 0) // Would trow an error because of require()


