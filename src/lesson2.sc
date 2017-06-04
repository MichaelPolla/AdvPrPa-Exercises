// Tail recursion (p.14-16) ---------------------------------------------------

// First example : perform recursive calls until the end of recursion,
// then return and calculate the result (=> result at the last recursive call).
def sum(x: Int) : Int = if (x == 1) x else x + sum(x - 1)

// Second example (tail recursion) : calculation, THEN recursive call.
// Pass the results to the next recursive step.
// Return value is always of the same type.
def tSum(x: Int, acc: Int) : Int = if (x == 0) acc else tSum(x-1, acc+x)

sum(3)    // Returns 6
tSum(3,0) // Returns 6

// Partial application (p.36) --------------------------------------------------

def adder(x: Int, y: Int) = x + y
val incr = adder(_: Int, 1)
val add2 = adder(2, _ : Int)

incr(5) // Returns 6
incr(5) // Returns 7


// Currying (p.41)--------------------------------------------------------------

def addNormal(a: Int, b: Int) = a + b
def addOneAtATime(a: Int) = (b: Int) => a + b

addNormal(3, 4)     // Returns 7
addOneAtATime(3)    // Returns (b: Int) => 3 + b
addOneAtATime(3)(4) // Returns 7

def addCurry(a: Int)(b: Int) = a + b
addCurry(3)(4)  // Returns 7

val inc = addCurry(1) _
inc(3) // Returns 4
