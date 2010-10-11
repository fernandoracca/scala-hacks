package hacks.specs

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Jul 9, 2010 10:05:20 PM
 */

import org.specs._
import org.specs.runner.{ConsoleRunner, JUnit4}

class MySpecTest extends JUnit4(MySpec)

object MySpecRunner extends ConsoleRunner(MySpec)

object MySpec extends Specification {
  "This wonderful test" should {
    "save the world" in {
      val list = Nil
      list must beEmpty
    }
  }
}