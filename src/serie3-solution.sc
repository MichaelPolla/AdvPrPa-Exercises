/** Serie 3 - Functional Data Structures **/

abstract class IntSet() {
  def add(x: Int): IntSet
  def +(x: Int): IntSet = this add x
  def contains(x: Int): Boolean
  def foreach(f: Int => Unit): Unit
  def union(other: IntSet): IntSet
  def intersect(other: IntSet): IntSet
  def excl(x: Int): IntSet
  def -(x: Int): IntSet = this excl x
}

object Empty extends IntSet() {
  def contains(x : Int): Boolean = false
  def add(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def foreach(f: (Int) => Unit): Unit = {}
  def union(other: IntSet): IntSet = other
  def intersect(other: IntSet): IntSet = Empty
  def excl(x: Int): IntSet = Empty
  override def toString: String = "-"

}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def add(x: Int): IntSet = {
    if (x < elem) new NonEmpty(elem, left add x, right)
    else if (x > elem) new NonEmpty(elem, left, right add x)
    else this
  }

  def contains(x: Int): Boolean = {
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  }

  def foreach(f: Int => Unit): Unit = {
    f(elem)
    left.foreach(f)
    right.foreach(f)
  }

  def union(other: IntSet): IntSet = {
    val newSet = (left union right) union other
    newSet add elem
  }

  def intersect(other: IntSet): IntSet = {
    val r = right intersect other
    val l = left intersect other
    val newSet = r union l
    if (other contains elem)
      newSet add elem
    else
      newSet
  }
  def excl(x: Int): IntSet = {
    if (x < elem) new NonEmpty(elem, left excl x, right)
    else if (x > elem) new NonEmpty(elem, left, right excl x)
    else left union right
  }

  override def toString: String = "(" + left + "|" + elem + "|" + right + ")"
}

println("Some tests at the beginning")
println(Empty)
println(Empty.add(3))
println(Empty.add(3).add(2))

val set1 = Empty.add(3).add(2).add(6).add(1)
val set2 = Empty + 5 + 2 + 8 + 4 + 0

println("Using foreach : " + set1.foreach(x => print(x + 1 + ", ")))
println("Union of set1 and set2: " + set1.union(set2))
println("Intersection of set1 and set2: " + set1.intersect(set2))
println("Exclusion of '3' in set1: " + set1.excl(3))
println("Testing new operators :" + (set1 + 42 - 3))
