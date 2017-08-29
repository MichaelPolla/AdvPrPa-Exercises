/**
  * Serie 9 - Domain Specific Languages (DSLs)
  * Question 1 solution
  */

// Question 1 - Implicit conversions --------------------------------

sealed abstract class Temperature(deg: Double)

object Temperature {
  implicit def doubleToCelsius(d: Double) = Celsius(d)
  implicit def doubleToKelvin(d: Double) = Kelvin(d)
  implicit def CelsiusToKelvin(temp: Celsius) = Kelvin(temp.deg + 273.15)
  implicit def KelvinToCelsius(temp: Kelvin) = Celsius(temp.deg - 273.15)
}

case class Celsius(deg: Double) extends Temperature(deg) {
  require(deg >= -273.15, "Celsius can't be lower than -273.15")

  override def toString() = s"$deg °C"
}

case class Kelvin(deg: Double) extends Temperature(deg) {
  require(deg >= 0, "Kelvin can't be lower than 0")

  override def toString() = s"$deg °F"
}


val a: Celsius = 30.0
val b: Kelvin = 30 // Here we have first automatic type promotion and then implicit
val c: Kelvin = Celsius(10)
val d: Celsius = c
val e: Temperature = d

println(a) // 30.0 °C
println(b) // 30.0 °F
println(e) // 10.0 °C