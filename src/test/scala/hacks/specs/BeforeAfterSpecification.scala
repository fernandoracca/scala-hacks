package hacks.specs

import org.specs.Specification

class BeforeTestSpecification extends Specification {
  "this system" should {
    var x = 0
    "execute the first example" in {
      x = 1
      x must_== 1 // success
    }
    "execute the second example in isolation" in {
      x must_== 0 // success
    }
  }
}