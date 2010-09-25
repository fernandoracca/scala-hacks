package hacks.collection.sort

import scala.collection.mutable.ListBuffer
import scala.util.Random
import hacks.io.Resource

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Jun 6, 2010 5:02:10 PM
 */
object DataSetGenerator {
	def generate(quantity: Int): List[Int] = {generate(quantity, quantity)}

	def generate(quantity: Int, bound: Int): List[Int] = {
		val dataSet = new ListBuffer[Int]()
		for (i <- 0 until quantity)
			dataSet += Random.nextInt(bound)
		dataSet.toList
	}

	def generatex(quantity: Int, bound: Int): List[Int] = {
		val items: List[Int] = Nil
		(0 until quantity) foreach {Random.nextInt(bound) :: items}
		items
	}

	def generateAndStore(quantity: Int, bound: Int, filePath: String): Unit = {
		printf("Started generating %d integers...%n", quantity)
		Resource.writeToFile(filePath) {
			writer =>
				for (item <- generate(quantity, bound))
					writer println item
		}
	}

}

