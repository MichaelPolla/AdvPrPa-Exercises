/** Serie 5b - Sequence comprehensions **/

// Question 1 - Tuples --------------------------------------------------------




// Question 2 - Sending postcards ---------------------------------------------

case class Postcard(content: String)

def sendPostcards: List[Postcard] = {
  val cities = List("Paris", "London", "Berlin", "Lausanne")
  val relatives = List("Grandma", "Grandpa", "Aunt Lottie", "Dad")
  val travellers = List("Pierre-Andre", "Rachel")

  for {
    traveller <- travellers
    relative <- relatives
    city <- cities
    sender = traveller

    if (relative.startsWith("G")) // (b) Select only certain members of the family
  } yield {
    new Postcard("Dear " + relative + ", " +
      "Wish you were here in " +
     city + "! " +
    "Love, " + sender)
  }
}

sendPostcards.foreach((x: Postcard) => println(x.content))


