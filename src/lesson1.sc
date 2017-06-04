/*------------------------------------------------*/
/* CBV vs CBN example (p. 82-83 of the course)    */

def first(x: Int, y: Int) = x
def firstCBN(x: Int, y: => Int) = x

def loop: Int = loop

first(4, 5)
//first(4, loop)  // loop indefinitely
firstCBN(4, loop) // return 4 ; loop is not evaluated, as it is not used.


/*------------------------------------------------*/
/* Defining && and || with an if statement (p.87) */

def opAnd(a: Boolean, b: Boolean) =
  if (a) b else false

def opOr(a: Boolean, b: Boolean) =
  if (a) true else b

opAnd(a = true, b = true)   // Returns true
opAnd(a = true, b = false)  // Returns false
opAnd(a = false, b = true)  // Returns false
opAnd(a = false, b = false) // Returns false

opOr(a = true, b = true)  // Returns true
opOr(a = true, b = false) // Returns true
opOr(a = false, b = true) // Returns true
opOr(a = false, b = false)  // Returns false