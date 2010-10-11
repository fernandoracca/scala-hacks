package hacks.finance

object UsBond {

	val Fraction = 32
	
	def apply(bondPrice: Double) = {
		bondPrice match {
			case zeroOrNegative if(bondPrice <= 0) => "0"
			case _ =>  val integer = bondPrice.toInt
					   val fraction = ((bondPrice - integer) * Fraction ).toInt
					   "%d-%02d" format (integer, fraction)
		}
	}

	def unapply(bondPriceFractional: String ) = {
		val BondPrice = """^(\d+)-(\d{2})?""".r
		bondPriceFractional match {
			case BondPrice(integer, fractional) => integer.toInt  + (fractional.toDouble / Fraction)
			case _ => 0
		}
	}
}