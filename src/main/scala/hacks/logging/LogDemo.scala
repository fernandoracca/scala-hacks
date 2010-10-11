package hacks.logging

import com.weiglewilczek.slf4s.Logging

class LogDemo extends Logging {
  logger warn "SLF4S just works!"
  logger info  "Created Logger with number %d.".format(System.currentTimeMillis)
}

object LogDemo {
	def main(args: Array[String]) { new LogDemo }
}
