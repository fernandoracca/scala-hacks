package hacks.functional

class Factorial   {

	def fact(n: Int) = n match { case 0 => 1
		case _ => 1 to n reduceLeft (_*_)
	}
}