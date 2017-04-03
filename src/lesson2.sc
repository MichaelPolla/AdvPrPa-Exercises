/* Tail recursion (p.14 - 16) */
// First example : perform recursive calls until the end of recursion,
// The return and calculate the result (=> result at the last recursive call).
def sum(x: Int) : Int = if (x == 1) x else x + sum(x - 1)

// Second example (tail recursion) : calculation, THEN recursive call.
// Pass the results to the next recursive step.
// Return value is always of the same type.
def tSum(x: Int, s: Int) : Int = if (x == 0) s else tSum(x-1, s+x)

sum(3)
tSum(3,0)

//----------------------------------------------------------------------------

/* Example of Currying (p.41) */
def addNormal(a: Int, b: Int) = a + b
def addOneAtATime(a: Int) = (b: Int) => a + b

addNormal(3, 4)
addOneAtATime(3)
addOneAtATime(3)(4)

def addCurry(a: Int)(b: Int) = a + b
addCurry(3)(4)

val inc = addCurry(1) _
inc(3)
