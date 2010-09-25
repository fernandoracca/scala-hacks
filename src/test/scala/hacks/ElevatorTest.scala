package hacks

import elevator.Elevator
import org.specs.Specification

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Sep 19, 2010 11:31:17 PM
 */
class ElevatorSpecification extends Specification {

	"An elevator" should {
  		"start in ground floor" in {
			val  elevator = Elevator()
			elevator.getCurrentFloor() must_== 0
			Elevator.unapply(elevator)
		 }
	}
	def doLast = { println("clean") }
}