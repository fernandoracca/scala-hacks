package hacks.eventsourcing.ship

import actors.Actor

/**
 * @author: jonas boner
 * http://jonasboner.com/2009/02/12/event-sourcing-using-actors.html
 */
class Ship(val name: String, val home: Port) extends Actor {

  def act = loop(home)

  private def loop(current: Port) {
    react {
      case ArrivalShippingEvent(time, port, _) =>
        println(toString + " ARRIVED  at port   " + port + " @ " + time)
        loop(port)

      case DepartureShippingEvent(time, port, _) =>
        println(toString + " DEPARTED from port " + port  + " @ " + time)
        loop(Port.AT_SEA)

      case Reset =>
        println(toString + " has been reset")
        loop(home)

      case CurrentPort =>
        reply(current)
        loop(current)

      case unknown =>
        error("Unknown event: " + unknown)
    }
  }

  override def toString = "Ship(" + name + ")"
}

class Port(val city: String, val country: Country) {
  override def toString = "Port(" + city + ")"
}
object Port {
  val AT_SEA = new Port("AT SEA", Country.AT_SEA)
}

case class Country(val name: String)
object Country {
  val US = new Country("US")
  val CANADA = new Country("CANADA")
  val AT_SEA = new Country("AT_SEA")
}