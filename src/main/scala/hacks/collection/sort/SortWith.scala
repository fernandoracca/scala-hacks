package hacks.collection.sort

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Jun 1, 2010 4:03:34 PM
 */
object SortWith {

	//if list has zero or one elements, it is already sorted, so we return
	//longer lists are split into two sub lists, each containing about half the elements
	//of the original list. each sublist is sorted by a recursive call to the sort function
	//and the resulting two sorted lists are then combined in a merge operation
	def mergeSort[T](elements: List[T])(comparator: (T,T) => Boolean ): List[T] = {
		def merge(left: List[T], right: List[T], accumulate: List[T]): List[T] = (left, right) match {
			case (Nil, _) => right.reverse ::: accumulate
			case (_, Nil) => left.reverse  ::: accumulate
			case (l :: left1, r :: right1) =>
						if(comparator(l, r)) merge(left1, right, l :: accumulate)
						else merge(left, right1, r :: accumulate)
		}

		val n = elements.length / 2
		if ( n == 0 ) elements
		else {
			val (left, right) = elements splitAt n
			merge(mergeSort(left)(comparator), mergeSort(right)(comparator), Nil).reverse
		}
	}

	def quickSort(elements: List[Int]): List[Int] = {
	    if (elements.isEmpty || elements.take(2).length == 1) elements
	    else {
	      val pivot = elements(elements.length / 2)
	      quickSort(elements.filter(_ < pivot)) :::
	           elements.filter(_ == pivot) :::
	           quickSort(elements.filter(_ > pivot))
	    }
	  }

	/*
	  Implementation suggested in Programming in Scala book. Throws StackOverflowError in large collections
	  def msort[T](less: (T, T) => Boolean) (xs: List[T]): List[T] = {
	    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
	        case (Nil, _) => ys
	        case (_, Nil) => xs
	        case (x :: xs1, y :: ys1) =>
	          if (less(x, y)) x :: merge(xs1, ys)
	          else y :: merge(xs, ys1)
	      }

	    val n = xs.length / 2
	    if (n == 0) xs
	    else {
	      val (ys, zs) = xs splitAt n
	      merge(msort2(less)(ys), msort2(less)(zs))
	    }
	} */
}