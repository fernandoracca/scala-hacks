package hacks.elevator

import scala.actors.Actor
import scala.actors.Actor._

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Sep 14, 2010 11:25:17 PM
 */

trait ElevatorAction
case class Call(val floorTo: Int) extends ElevatorAction
case class Up(val floorTo: Int) extends ElevatorAction
case class Down(val floorTo: Int) extends ElevatorAction
case object OpenDoors extends ElevatorAction
case object Stop extends ElevatorAction

class Passenger(var currentFloor: Int = 0) {

	def callElevator(elevator: Elevator){
		elevator ! Call(currentFloor)
	}

	def move(elevator: Elevator, floorTo: Int) {
		floorTo match {
			case targetFloor if currentFloor > floorTo => elevator ! Down(targetFloor)
			case targetFloor if currentFloor < floorTo => elevator ! Up(targetFloor)
			case currentFloor => elevator ! OpenDoors
		}
	}
}

class Elevator extends Actor {
	private var currentFloor: Int = 0

	def getCurrentFloor() = currentFloor

	println("Elevator is in ground floor.")

	def going(direction: String, floorTo: Int) {
		printf("Going %s from floor %d to floor %d %n", direction, currentFloor, floorTo)
		move(floorTo)
	}

	def move(floorFrom: Int): Unit = {
		val direction: Int = if (floorFrom > currentFloor)  1 else -1

		def changeCurrentFloor(floorFrom: Int) {
			println("Elevator in floor " + currentFloor)

			if( floorFrom == currentFloor) {
				return
			}
			currentFloor += direction
			changeCurrentFloor(floorFrom)
		}
		changeCurrentFloor(floorFrom)
	}

	def openDoors(){
		println("Doors opening in floor " + currentFloor)
	}

	def act(){
		println("Starting Elevator")

		loop {
      		react {
				case Call(floorFrom: Int) => {
					printf("Picking up passenger in floor %d %n", floorFrom)					
					move(floorFrom)
					openDoors
				}
				case Up(floorTo: Int) => going("up", floorTo)
				case Down(floorTo: Int) =>  going("down", floorTo)
				case OpenDoors => openDoors				 
				case Stop => println("Elevator is stopping.")
				  			Elevator.unapply(this)
			  }
		}
	}
}
object Elevator {
	def apply() = {
		val elevator = new Elevator
		elevator.start
		elevator
	}

	def unapply(elevator: Elevator) = elevator.exit
}

object ElevatorController {
	def run = {
		val elevator = Elevator()

		println("passenger1...")
		val passenger1 = new Passenger(5)
		passenger1.callElevator(elevator)
		passenger1.move(elevator, 3)

		println("passenger2...")
		val passenger2 = new Passenger
		passenger2.callElevator(elevator)
		passenger2.move(elevator, 6)

		println("passenger3...")
		val passenger3 = new Passenger
		passenger3.callElevator(elevator)
		passenger3.move(elevator, 0)

		println("passenger4...")
		val passenger4 = new Passenger(2)
		passenger3.callElevator(elevator)
		passenger3.move(elevator, 5)

		elevator ! Stop
	}

	def main(args: Array[String]) = run
}