package hacks.io

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Jun 6, 2010 5:18:18 PM
 */
import scala.io.Source
import java.io.File

object LinePrinter {

	def printLines(filePath: String) {
		for(line <- Source.fromFile(new File(filePath)).getLines())
			printf("%1$3d | %2$s %n", line.length, line)
	}

	def main(args: Array[String] ) {
		if(args.length < 1) {
			Console.err.printf("%s. You need to supply a file name to print.", getClass().getSimpleName)
			exit(-1)
		}
		printLines(args(0))
	}
}