package hacks.io

import java.io.{File,IOException,PrintWriter}
import java.util.concurrent.TimeUnit

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Jun 6, 2010 5:04:35 PM
 */
object Resource {

	def writeToFile(filePath: String)(codeBlock: PrintWriter => Unit) {
		val writer = new PrintWriter(new File(filePath))
		val start = System.nanoTime
		try {
			codeBlock(writer)
	    } catch {
	        case ioex: IOException => error("exception writing: " + ioex.getMessage)
	    }  finally {
	        writer.close
	    }
	    printf("Completed writing to file: %s in "+ TimeUnit.NANOSECONDS.toMillis(System.nanoTime - start) +"ms", filePath)
	}

	def readFromFile(filePath: String)  {

	}
}

