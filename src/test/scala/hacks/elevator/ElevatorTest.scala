package hacks

import elevator.{Passenger, Stop, Elevator}
import org.specs.Specification
import java.util.concurrent.TimeUnit

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Sep 19, 2010 11:31:17 PM
 */
class ElevatorSpecification extends Specification {
	"An elevator" should {
		var elevator = new Elevator()
		"start in ground floor" in {
			val elevator = new Elevator()
			elevator.getCurrentFloor() must_== 0
			elevator ! Stop
		 }

		"pick up a passenger in a higher floor and take him to a lower floor" in {
			val elevator = new Elevator()
			elevator.getCurrentFloor() must_== 0			                

			val passenger1 = new Passenger(5)
			passenger1.callElevator(elevator)
			TimeUnit.MILLISECONDS.sleep(2000)

			elevator.getCurrentFloor() must_== 5
						
			elevator ! Stop
		}
//
//			passenger1.move(elevator, 3)
//			elevator.getCurrentFloor() must_== 3
//			elevator ! Stop
//		}
	}
}