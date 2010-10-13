package hacks.finance
import BondRatingsGenerator._
import org.specs.Specification

/**
 * Dev: Eleanna Skouta
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Oct 12, 2010 12:48:29 AM
 */
class BondRatingsGeneratorSpecification extends Specification {

	"Moodys rating " in {
		moodysRating must be matching("Aaa|[A-B]a[1-3]|[B-C]aa[1-3]|C[a]|/")
	}

	"Standard & Poor's Rating" in {
		spRating must be matching("AAA|(BBB|CCC|AA|BB|A|B)[-|+]{0,1}|C{1,2}|D")
	}

	"Fitch Rating " in {
		fitchRating must be matching("AAA|B{3}[-|+]{0,1}|[A-B]{1,2}[-|+]{0,1}|CCC|D{1,3}")
	}
}