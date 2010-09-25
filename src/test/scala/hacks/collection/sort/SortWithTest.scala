package hacks.collection.sort

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Jun 1, 2010 5:16:27 PM
 */
import hacks.collection.sort.DataSetGenerator.generate
import hacks.collection.sort.SortWith.mergeSort
import hacks.collection.sort.SortWith.quickSort
import org.junit.Assert.{assertEquals,assertTrue}
import org.junit.Test
import scala.util.Random

class SortWithTest {

//	val sortAscending = mergeSort((x: Int, y: Int) => x < y) _

	def mergeSortDesc(items: List[Int]) = {
		mergeSort(items)(_ > _)
	}

	def mergeSortAsc(items: List[Int]) = {
		mergeSort(items)(_ < _)
	}

	@Test
	def sortAnEmptyListWithMergeSort(){
		assertTrue(mergeSortAsc(Nil).isEmpty)
	}

	@Test
	def aListWithOneElementIsAlreadySortedForMergeSort(){
		assertEquals(1, mergeSortAsc(List(1)).head)
	}

	@Test
	def sortAnAlreadySortedListWithMergeSort(){
		var sortedInts = for(i <- 0 to 9) yield i
		assertListIsSorted(mergeSortAsc(sortedInts.toList))
	}

	def assertListIsSorted(result: List[Int]) {
		for( i <- (0 to 9)){
			assertEquals(i, result(i))
		}
	}

	@Test
	def sortASmallListOfUnorderedIntegersWithMergeSort(){
		var mixedInts = List(4, 1, 9, 0, 5, 8, 3, 6, 2, 7)
		val result = mergeSortAsc(mixedInts)
		assertListIsSorted(result)
	}

	@Test
	def sortALargeListOfRandomIntegersWithMergeSort(){
		var randomInts = for(i <- 0 to 1000) yield Random.nextInt(10000)
		val result = mergeSortAsc(10000 :: randomInts.toList)
		assertEquals(10000, result.last)
	}

	@Test
	def sortAnArbitraryLargeListOfRandomIntegersWithMergeSort(){
		var randomInts = generate(100000)
		val result = mergeSortDesc(100000 :: randomInts)
		assertEquals(100000, result.head)
	}

	@Test
	def sortAnEmptyListWithQuickSort(){
		assertTrue(quickSort(Nil).isEmpty)
	}

	@Test
	def aListWithOneElementIsAlreadySortedForQuickSort(){
		assertEquals(1, quickSort(List(1)).head)
	}

	@Test
	def sortAnAlreadySortedListWithQuickSort(){
		var sortedInts = for(i <- 0 to 9) yield i
		assertListIsSorted(quickSort(sortedInts.toList))
	}

	@Test
	def sortAnArbitraryLargeListOfRandomIntegersWithQuickSort(){
		var randomInts = generate(100000)
		val result = quickSort(100000 :: randomInts)
		assertEquals(100000, result.last)
	}
}