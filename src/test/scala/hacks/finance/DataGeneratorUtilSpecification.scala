package hacks.finance

import org.specs.Specification
import DataGeneratorUtil._

/**
 * Dev: Eleanna Skouta
 * Dev: Fernando Racca (fracca@gmail.com)
 */
class DataGeneratorUtilSpecification extends Specification {
	"generate random text" in {
		generateText(8, 'a', 'z') must be matching ("[A-Z]{8}")
	}

	"generate random lower case text" in {
		randomLowerCaseChar('a', 'z') must be matching ("[a-z]")
	}

	"generate random upper case text" in {
		randomUpperCaseChar('a', 'z') must be matching ("[A-Z]")
	}

	"generate random text" in {
		randomChar('a', 'z') must be matching ("[a-zA-Z]")
	}

	"generate numbers" in {
		val result = generateNumbers(6)
		result.toString must be matching ("[0-9]{6}")
	}

	"sign required" in {
		sign(required = true) must be matching ("[+|-]")
	}

	"sign not required" in {
		sign(required = false) must be matching ("[+|-]?")
	}

	"repeat text " in {
		repeat("A", 3) must be matching ("AAA")
	}

	"repeat text with different times probability" in {
		repeat("A", 2, 5) must be matching ("AA|AAAAA")
	}
}