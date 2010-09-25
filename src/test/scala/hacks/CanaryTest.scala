package hacks

import org.junit.Test
import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.is

class CanaryTest {
	@Test
	def testTrue() {
		assertThat(true, is(true))
	}

	@Test
	def testFalse() {
		assertThat(false, is(false))
	}

	@Test
	def testLambda() {
		def increment = (x: Int) => x + 1
		assertThat(increment(3), is(4))
	}
}