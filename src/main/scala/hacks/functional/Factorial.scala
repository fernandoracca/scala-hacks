package hacks.functional

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Sep 26, 2010 10:20:09 PM
 */
class Factorial   {

	def fact(n: Int) = n match { 
		case 0 => 1
		case _ => 1 to n reduceLeft (_*_)
	}
}