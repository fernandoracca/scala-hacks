package hacks.actor

import scala.actors.Actor
import scala.actors.Actor._
import java.util.concurrent.TimeUnit.NANOSECONDS

abstract class PingMessage
case object Start extends PingMessage
case object SendPing extends PingMessage
case object Pong extends PingMessage

abstract class PongMessage
case object Ping extends PongMessage
case class Stop(val start: Long) extends PongMessage

/**
 * Original source code from Scala website:
 * @see {@link http://www.scala-lang.org/node/54}
 *
 * I made some modifications to pass a message that records the 
 * time it took to complete.
 */
object PingPong extends Application {
  val pong = new Pong
  val ping = new Ping(100000, pong)
  ping.start
  pong.start
  ping ! Start
}

class Ping(count: Int, pong: Actor) extends Actor {
  val startTime = System.nanoTime
  def act() {
    println("Ping: Initializing with count "+count+": "+pong)
    var pingsLeft = count
    loop {
      react {
        case Start =>
          println("Ping: starting.")
          pong ! Ping
          pingsLeft = pingsLeft - 1
        case SendPing =>
          pong ! Ping
          pingsLeft = pingsLeft - 1
        case Pong =>
          if (pingsLeft % 1000 == 0)
            println("Ping: pong from: "+sender)
          if (pingsLeft > 0)
            self ! SendPing
          else {
            println("Ping: Stop.")
            pong ! new Stop(startTime)
            exit('stop)
          }
      }
    }
  }
}

class Pong extends Actor {
  def act() {
    var pongCount = 0
    loop {
      react {
        case Ping =>
          if (pongCount % 1000 == 0)
            println("Pong: ping "+pongCount+" from "+sender)
          sender ! Pong
          pongCount = pongCount + 1
        case Stop(startTime: Long) =>
          println("Pong: Stop.")
	      val now = System.nanoTime
		  printf("Time elapsed: %d ms", NANOSECONDS.toMillis(now - startTime))
          exit('stop)
      }
    }
  }
}