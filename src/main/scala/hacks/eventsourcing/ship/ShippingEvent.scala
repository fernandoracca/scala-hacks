package hacks.eventsourcing.ship

import java.util.Date

/**
 * @author: jonas boner
 * http://jonasboner.com/2009/02/12/event-sourcing-using-actors.html
 */
sealed abstract case class ShippingEvent

abstract case class StateTransitionShippingEvent(val occurred: Date)
  extends ShippingEvent {
  val recorded = new Date
  def process: Unit
}

case class DepartureShippingEvent(val time: Date, val port: Port, val ship: Ship)
  extends StateTransitionShippingEvent(time) {
  override def process = ship ! this
}

case class ArrivalShippingEvent(val time: Date, val port: Port, val ship: Ship)
  extends StateTransitionShippingEvent(time) {
  override def process = ship ! this
}

case object Reset extends ShippingEvent

case object CurrentPort extends ShippingEvent

case object Replay extends ShippingEvent

case class ReplayUpTo(date: Date) extends ShippingEvent
