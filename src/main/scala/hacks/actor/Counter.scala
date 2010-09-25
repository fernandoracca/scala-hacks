package hacks.actor

import scala.actors.Actor;

  /*
case class Incr()
case class Value(counter: Counter)
case class Lock(counter: Counter)
case class UnLock(newValue: Int)

class CounterDemo extends Application {
	def main(args: Array[String]) {
		val counter = new Counter
		counter.start()
		counter ! Incr()
		counter ! Value(counter)
		receive { case x: Int => Console.println(x) }
	}
}

class Counter extends Actor {
	override def run(): Unit = loop(0)

	def loop(value: Int): Unit = {
		Console.println("Value: " + value)
		receive {
			case Incr() => loop(value + 1)
			case Value(a) => a ! value; loop(value)
			case Lock(a) => a ! value
				receive {
					case UnLock(v) => loop(v)
				}
			case _ => loop(value)
		}
	}
}

*/
