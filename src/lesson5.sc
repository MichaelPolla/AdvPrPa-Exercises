// list samples

val l = List(4, 1, 4, 12, 7, 8)

l.sum
l.distinct


// ReduceLeft demo
val l2 = List(4,1,12,7)
l2 reduceLeft ((x: Int, y:Int) => x + y)

l2 reduceLeft((x, y) => x min y)
//Retourne formellement : min(min(min(4,1), 12), 7))

l2 reduceLeft (_ max _) // Mais prof conseille pas car devient difficile à comprendre

def verboseMax(x: Int, y: Int) = {
  val winner = x max y
  println(s"compared $x to $y, $winner was larger")
  // on préfixe la chaine par 's' pour dire que l'on va
  // interpoler (remplacer les chaînes précédées de $ par la valeur correspondante).
  // Evite de devoir faire ".." + x + "..."
  winner
}

l reduceLeft verboseMax


def concat(a: Any, b: Int) = a + "-" + b
// def concat(a: Any, b: Int) = a + "-" + b  ne marchera pas car Any
// n'est pas une sous-classe de Int
// //(on devrait faire un reduceRight)
l2 reduceLeft concat


// p.17

val l3 = List (1,2,3,4,5)
l3.foldRight(0)((x, y) => x + y)


// Ex2 p.22
def length[A] (x : List[A]) : Int = {
  (x foldRight (0))((x:A, y:Int) => y + 1)
}
length(l3)

def map[A, B] (x: List[A], f: A => B) : List[B] = {
  (x foldRight List.empty[B])((x:A, y: List[B]) => f(x)::y)
}

// A préférer (perso je trouve que ça évite les risques de parenthèses en trop ou mal formées) :
// x.foldRight(List.empty...


val l4 = List(List(1), List(3,4))
l4.flatten

val l5 = List(1,2,3)
val l6 = List(4,5,6)
l5.zip(l6)

val l7 = List(3,4,5,2,2,5,6)
l7.sortWith(_ < _) ///  affiche la liste par ordre croissant

//p.26
def dup[A](l: List[A]):List[A] = {
  def rep[A](l: A) = List[A](l, l) // réplique un élément
  l.flatMap(rep)
}

// v2
def rep[A](l: A) = List[A](l, l) // réplique un élément
def dup2[A] (l: List[A]) : List[A] = l.flatMap(rep)
def dup3[A] (l: List[A]) : List[A] = l.flatten(rep) // pourquoi pas besoin du map ?!?
dup(List(1,2,3))
dup2(List(1,2,3))
dup3(List(1,2,3))
