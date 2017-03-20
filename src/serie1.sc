/* QUESTION 1 */

/* e) define a function that returns the square of a value */
def square (x: Double) = x * x
square (3)

/* f) Define another function that returns the 4th power of a value,
 *    using the square function you just defined. */
def fourthPow(x: Double) = square(x) * square(x)
fourthPow(3)


/* QUESTION 2 */
/* Calculate the square root of a number, using the Newton's method. */
def abs(x: Double) = if (x < 0) -x else x
def isGoodEnough(approx: Double, x: Double) = abs(square(approx) - x) < 0.001
def improve(approx: Double, x: Double) = approx - (square(approx) - x) / (2 * approx)

def sqrtIter(approx: Double, x: Double) : Double =
  if (isGoodEnough(approx, x)) approx
  else sqrtIter(improve(approx, x), x)

def sqrt(x: Double) = sqrtIter(10.0, x)

sqrt(612)


/* Implement the cubic root using the same approach */
// Example with nested functions

def cube(x: Double) = x * x * x

def cubicRoot(x: Double) = {
  def isGoodEnough(approx: Double) = abs(cube(approx) - x) < 0.0001
  def improve(approx: Double) = approx - (cube(approx) - x) / (3 * square(approx))

  def cubicRootIter(approx: Double) : Double =
    if(isGoodEnough(approx)) approx
    else cubicRootIter(improve(approx))

  cubicRootIter(10.0)
}

cubicRoot(27)


