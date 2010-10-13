package hacks.finance

/**
 * Dev: Eleanna Skouta
 */
import util.Random
import scala.math._

object DataGeneratorUtil {

   def repeat(text: String, times: Int) = {
    val letters = new StringBuilder

    for(i <- 1 to times){
      letters.append(text)
    }
    letters.toString
  }

  def repeat(text: String, eitherTimes: Int, orTimes: Int): String = {
    val times = if (Random.nextBoolean) eitherTimes else orTimes

    repeat(text, times)
  }

  /**
   * Generates upper case string
   * */
  def generateText (numberOfLetters: Int, low: Char, high: Char) = {
    val letters = new StringBuilder

    for(i <- 1 to numberOfLetters) {
      letters.append(randomUpperCaseChar(low, high))
    }

    letters.toString
  }

  def randomChar (low: Char, high: Char) = {
    val letter = randomLowerCaseChar (low, high)
    if(Random.nextBoolean) letter.capitalize else letter
  }

  def randomUpperCaseChar (low: Char, high: Char): String = randomLowerCaseChar(low, high).capitalize

  /**
   * random lower case character based on the ascii characters 97-122 is the lowercase alphabet
   * */
  def randomLowerCaseChar (low: Char, high: Char): String = {
    val number = boundedNumber(low.toInt, high.toInt)
    number.asInstanceOf[Char].toString
  }

  def boundedNumber (low: Int, high: Int) = {
    (Random.nextInt(high) % (high - low + 1 )) + low
  }

  /**
   * Given 6 digits => generated number would be between 100,000 and 999,999
   * */
  def generateNumbers (numberOfDigits: Int): Int = {
    val low = math.pow(10, numberOfDigits - 1).toInt //10^5 => 100,000
    val high = math.pow(10, numberOfDigits).toInt - 1 // 10^6 -1 => 999,999
    boundedNumber(low, high)
  }

  /**
   * Returns - or + sign or " " if no sign is required
   * */
  def sign(required: Boolean): String = {
    val boolean = Random.nextBoolean

    boolean match {
      case sign : Boolean if(!required && Random.nextBoolean) => ""
      case sign : Boolean if boolean => "+"
      case _ => "-"
    }
  }


  //-------------------------------
  /**
   * NOT IN USE AT THE MOMENT
   *
   * Generates text, while having the ability to choose
   * between upper, lower, or mixed case letters.
   * */
  def generate (numberOfLetters: Int, randomCharGenerator: () => Any) = {
    val letters = new StringBuilder

    for(i <- 1 to numberOfLetters) {
      letters.append(randomCharGenerator)
    }

    letters.toString
  }

}