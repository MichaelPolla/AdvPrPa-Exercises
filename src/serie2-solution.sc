import scala.annotation.tailrec

// Question 1 : Tail recursion ------------------------------------------------

// a) Define a tail-recursive version of fact.
@tailrec
def factTail(x: Int, acc: Int = 1) : Int = {
  if(x == 0)
    acc
  else
      factTail(x - 1, x * acc)
}

factTail(10)

// b) Write two implementation of the Fibonacci function :
// one with the definition provided in assignment, the other using tail recursion.

def fibo(x: Int) : Int = {
  if(x == 0) 0
  else if(x == 1) 1
  else fibo(x - 1) + fibo(x - 2)
}

fibo(10)

// tail recursion version
def fiboTail(x: Int) : Int = {
  @tailrec
  def fib(n: Int, a: Int, b: Int) : Int = {
    if (n == 0) a
    else fib(n-1, b, a+b)
  }

  fib(x, 0, 1)
}

fiboTail(10)

// another solution, with default arguments values
@tailrec
def fiboTail2(x: Int, a: Int = 0, b: Int = 1) : Int = {
  if (x == 0) a
  else fiboTail2(x-1, b, a + b)
}

// Question 2 : Higher-order functions ----------------------------------------

// a) Transform the sum function (see details in the assignment) to a tail recursion.
def sum(f: Int => Int, a: Int, b: Int) = {
  def iter(a: Int, acc: Int) : Int = {
    if (a > b) acc
    else iter(a+1, f(a) + acc)
  }
  iter(a, 0)
}
// Usage example
sum(x => x + 2, 1, 4)

// Question 3 : Currying ------------------------------------------------------

// a) Using the sum function as a source of inspiration, write a function product
// that computes the product of the values of a function for the integers
// in a given interval. Make sure that this function is in its curried form.
def product(f: Int => Int)(a: Int, b: Int) : Int = {
  if (a > b) 1
  else f(a) * product(f)(a+1,b)
}
// Usage example
product(x => x + 2)(1, 3)

// b) Write 'factorial' in terms of the function product that you defined in part a.
def factorial(n: Int) = product(x => x)(1, n)

// c) Write a more general function that generalizes both sum and product.
// This done,provide a new implementation of sum, resp. product, using that new function.
def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zeroValue: Int)(a: Int, b:Int) : Int = {
  if (a > b) zeroValue
  else combine(f(a), mapReduce(f, combine, zeroValue)(a+1, b))
}

def prodAsMapReduce(f: Int => Int)(a: Int, b: Int) : Int =
  mapReduce(f, (x, y) => x * y, 1)(a, b)

def sumAsMapReduce(f: Int => Int)(a: Int, b: Int) : Int =
  mapReduce(f, (x, y) => x + y, 0)(a, b)

// Usage examples
prodAsMapReduce(x => x)(1, 6)
sumAsMapReduce(x => x * 2)(1, 6)

