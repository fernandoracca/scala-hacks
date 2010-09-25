package hacks.eventsourcing.ship

import java.util.Date

/**
 * @author: jonas boner
 * http://jonasboner.com/2009/02/12/event-sourcing-using-actors.html
 */
object ActorBasedEventSourcingDemo {

	def main(args: Array[String] ) {
		(new ActorBasedEventSourcing)
	  .setup
	  .arrivalSetsShipsLocation
	  .departurePutsShipOutToSea
	  .smallTrip
	  .resetAndReplayEventLog
	  .resetAndReplayEventLogUpToDate
	  .tearDown	
	}
}

class ActorBasedEventSourcing {

  private var shipKR: Ship = _
  private var portSFO, portLA, portYYV: Port = _
  private var processor: ShippingEventProcessor = _

  def setup = {
    processor = new ShippingEventProcessor
    processor.start

    portSFO = new Port("San Francisco", Country.US)
    portLA = new Port("Los Angeles", Country.US)
    portYYV = new Port("Vancouver", Country.CANADA)

    shipKR = new Ship("King Roy", portYYV)
    shipKR.start

    this
  }

	def tearDown = {
		exit(0)
		this
	}

  def arrivalSetsShipsLocation = {
    println("\n===> arrivalSetsShipsLocation")

    processor ! DepartureShippingEvent(new Date(2009, 2, 1), portSFO, shipKR)
    Thread.sleep(500)

    processor ! ArrivalShippingEvent(new Date(2009, 2, 3), portSFO, shipKR)
    Thread.sleep(500)

    assert(portSFO == (shipKR !? CurrentPort))
    this
  }

  def departurePutsShipOutToSea = {
    println("\n===> departurePutsShipOutToSea")

    processor ! DepartureShippingEvent(new Date(2009, 2, 4), portLA, shipKR)
    Thread.sleep(500)

    assert(Port.AT_SEA == (shipKR !? CurrentPort))
    this
  }

  def smallTrip = {
    println("\n===> smallTrip")

    processor ! ArrivalShippingEvent(new Date(2009, 2, 5), portLA, shipKR)
    Thread.sleep(500)

    processor ! DepartureShippingEvent(new Date(2009, 2, 6), portYYV, shipKR)
    Thread.sleep(500)

    processor ! ArrivalShippingEvent(new Date(2009, 2, 8), portYYV, shipKR)
    Thread.sleep(500)

    processor ! DepartureShippingEvent(new Date(2009, 2, 9), portSFO, shipKR)
    Thread.sleep(500)

    processor ! ArrivalShippingEvent(new Date(2009, 2, 11), portSFO, shipKR)
    Thread.sleep(500)

    assert(portSFO == (shipKR !? CurrentPort))
    this
  }

	def resetAndReplayEventLog = {
	  println("\n===> resetAndReplayEventLog")

	  shipKR ! Reset

	  processor ! Replay
	  Thread.sleep(500)

	  assert(portSFO == (shipKR !? CurrentPort))
	  this
	}

	def resetAndReplayEventLogUpToDate = {
	  println("\n===> resetAndReplayEventLogUpToDate")

	  shipKR ! Reset
	
	  processor ! ReplayUpTo(new Date(2009, 2, 4))
	  Thread.sleep(500)

	  assert(Port.AT_SEA == (shipKR !? CurrentPort))
	  this
	}
}

