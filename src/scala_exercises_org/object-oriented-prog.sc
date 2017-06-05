class Rational(x: Int, y: Int) {
  def numer: Int = x
  def denom: Int = y

  def addRational(r: Rational): Rational = {
    new Rational(numer * r.denom + denom * r.numer, denom * r.denom)
  }

  override def toString: String = numer + "/" + denom
}

val x = new Rational(1, 2)
x.numer
x.denom

def addRational(r: Rational, s: Rational): Rational =
  new Rational(
    r.numer * s.denom + r.denom * s.numer,
    r.denom * s.denom
  )

def makeString(r: Rational) = {
  r.numer + "/" + r.denom
}

makeString(addRational(new Rational(1, 2), new Rational(2, 3)))