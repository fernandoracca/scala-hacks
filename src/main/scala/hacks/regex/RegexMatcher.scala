package hacks.regex

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Dev: Eleanna Skouta
 * Creation Date: Jul 10, 2010 9:19:41 AM
 */
class RegexMatcher(val pattern: String) extends TypeSafeMatcher[String] {

  def matchesSafely(text: String) = {
    text.matches(pattern);
  }

  def describeTo(description: Description) {
    description.appendText("match " + pattern);
  }

}

object RegexMatcher {

	def apply(regex: String) = {
        new RegexMatcher(regex);
    }
}