
/* CBV vs CBN example (p. 82-83 of the course) */

def first(x: Int, y: Int) = x
def firstCBN(x: Int, y: => Int) = x

def loop: Int = loop

first(4, 5)
//first(4, loop)  // loop indefinitely
firstCBN(4, loop) // return 4 ; loop is not evaluated, as it is not used.
