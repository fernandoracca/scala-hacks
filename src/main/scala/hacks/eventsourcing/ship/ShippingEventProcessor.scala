package hacks.eventsourcing.ship

import actors.Actor

/**
 * @author: jonas boner
 * http://jonasboner.com/2009/02/12/event-sourcing-using-actors.html
 */
class ShippingEventProcessor extends Actor {
  def act = loop(Nil)

  private def loop(log: List[StateTransitionShippingEvent]) {
    react {
      case event: StateTransitionShippingEvent =>
        event.process
        loop(event :: log)

	  case Replay =>
        log.reverse.foreach(_.process)
        loop(log)

	  case ReplayUpTo(date) =>
        log.reverse.filter(_.occurred.getTime <= date.getTime).foreach(_.process)
        loop(log)
	  
      case unknown =>
        error("Unknown event: " + unknown)
    }
  }
}
