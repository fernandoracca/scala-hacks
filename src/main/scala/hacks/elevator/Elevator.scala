package hacks.elevator

import scala.actors.Actor._
import actors.{TIMEOUT, Actor}

trait ElevatorAction
case class Call(val floorTo: Int) extends ElevatorAction
case class Up(val floorTo: Int) extends ElevatorAction
case class Down(val floorTo: Int) extends ElevatorAction
case object OpenDoors extends ElevatorAction
case object CurrentFloor extends ElevatorAction
case object Stop extends ElevatorAction

class Passenger(var currentFloor: Int = 0) {

	def callElevator(elevator: Elevator){
		println("A new passenger in floor: " + currentFloor)
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

	def getCurrentFloor() = currentFloor//self !? CurrentFloor // it blocks the unit test 

	private var running = true

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
		loopWhile(running) {
      		reactWithin(500) {
				case Call(floorFrom: Int) => {
					printf("Picking up passenger in floor %d %n", floorFrom)					
					move(floorFrom)
					openDoors
				}
				case Up(floorTo: Int) => going("up", floorTo)
				case Down(floorTo: Int) =>  going("down", floorTo)
				case OpenDoors => openDoors
				case CurrentFloor => reply(currentFloor)
				case Stop => println("Elevator is stopping.")
				  			running = false
				case TIMEOUT => {
					println("timeout")
					this.exit
				}
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
}

object ElevatorController {
	def run = {
		val elevator = Elevator()

		val passenger1 = new Passenger(5)
		passenger1.callElevator(elevator)
		passenger1.move(elevator, 3)

		val passenger2 = new Passenger
		passenger2.callElevator(elevator)
		passenger2.move(elevator, 6)

		val passenger3 = new Passenger
		passenger3.callElevator(elevator)
		passenger3.move(elevator, 0)

		val passenger4 = new Passenger(2)
		passenger4.callElevator(elevator)
		passenger4.move(elevator, 5)

		elevator ! Stop
	}

	def main(args: Array[String]) = run
}