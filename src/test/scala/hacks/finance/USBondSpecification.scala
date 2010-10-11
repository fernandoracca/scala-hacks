package hacks.finance

import org.specs.Specification

class USBondSpecification extends Specification {

	"A US Bond priced in decimals " should {
		"be converted to 32nds fractions" in {
			var bondPrice = UsBond(105.25)
			bondPrice must_== "105-08"

			bondPrice = UsBond(9.03125)
			bondPrice must_==  "9-01"
		}

		"be converted to 0 if price is zero" in {
			val bondPrice = UsBond(0)
			bondPrice must_== "0"
		}

		"be converted to 0 if price is negative" in {
			val bondPrice = UsBond(-1)
			bondPrice must_== "0"
		}
	}

	"A US Bond priced in 32nd fractions " should {
		"be converted to decimal" in {
			var bondPrice = UsBond.unapply("9-01")
			bondPrice must_==  9.03125

			bondPrice = UsBond.unapply("105-08")
			bondPrice must_==  105.25
		}

		"be converted to 0 if bond price is zero" in {
			var bondPrice = UsBond.unapply("0-0")
			bondPrice must_== 0d

			bondPrice = UsBond.unapply("0")
			bondPrice must_== 0d
		}

		"be converted to 0 if price is wrongly formatted" in {
			val bondPrice = UsBond.unapply("asdf")
			bondPrice must_== 0d
		}

		"be reflexively converted into decimals and back to fractions" in {
			val stringBondPrice: String = UsBond(105.25)
			stringBondPrice must_== "105-08"
			val doubleBondPrice = UsBond.unapply(stringBondPrice)
			doubleBondPrice must_== 105.25
		}
	}
}