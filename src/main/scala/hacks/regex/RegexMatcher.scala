package hacks.regex

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

class RegexMatcher(val pattern: String) extends TypeSafeMatcher[String] {

  def matchesSafely(text: String) = text.matches(pattern)

  def describeTo(description: Description) = description.appendText("match " + pattern)
}

object RegexMatcher {
	def apply(regex: String) =  new RegexMatcher(regex)
}