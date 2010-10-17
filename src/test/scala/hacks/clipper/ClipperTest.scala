package hacks.clipper

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite

/**
 * Dev: Fernando Racca (fracca@gmail.com)
 * Creation Date: Oct 16, 2010 12:44:30 AM
 */
class ClipperSuite extends FunSuite with ShouldMatchers {

   test( "Simple version parsing") {
	   val shortParam = "-v"
	   val longParam = "--version"
	   val parameters = List(ParamBuilder().short(shortParam).long(longParam))
	   val arguments = Array[String]()
	   val cli = Clipper.parse(parameters.map(_ build), arguments)
	   cli.size should equal (1)
	   val shortParameter = cli.findByShortParameter(shortParam).get.short
	   shortParameter.get should equal(shortParam)
	   cli.findByShortParameter(longParam) should equal(None)
   }
}