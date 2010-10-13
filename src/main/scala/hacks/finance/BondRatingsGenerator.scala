package hacks.finance

import util.Random
import DataGeneratorUtil._	

/**
 * Dev: Eleanna Skouta
 */
object BondRatingsGenerator {

  /**
   * Moodys:
   *              Aaa, Aa1, Aa2, Aa3, A1, A2, A3,
   * Baa1, Baa2, Baa3, Ba1, Ba2, Ba3, B1, B2, B3,
   * Caa1, Caa2, Caa3, Ca, C, /
   * pattern: Aaa |  [A-B]a[1-3] | [B-C]aa[1-3] | C[a] | /
   *
   * */
  def moodysRating() = {
    val prob = Random.nextDouble
    val p1 = 0.10       // probability of 10% for that rating
    val p2 = 0.45 + p1  // probability of 45% for that rating
    val p3 = 0.40 + p2

    prob match {
      case probability : Double if prob < p1 => "Aaa"
      case probability : Double if prob < p2 => randomUpperCaseChar('a', 'b').concat("a").concat(boundedNumber(1, 3).toString)
      case probability : Double if prob < p3 => randomUpperCaseChar('b', 'c').concat("aa").concat(boundedNumber(1, 3).toString)
      case _ => "C".concat(repeat("a", 0, 1))
    }
  }

  /**
   * S&P:
   *             AAA, AA+, AA, AA-, A+, A, A-
   * BBB+, BBB, BBB-, BB+, BB, BB-, B+, B, B-
   * CCC+, CCC, CCC-, CC, C,
   * D
   * pattern: AAA | [BBB|CCC|A|AA|B|BB][-|+]{0,1} | C{1,2} | D
   *
   * */
  def spRating() = {
    val n = Random.nextDouble
    val p1 = 0.10
    val p2 = 0.40 + p1
    val p3 = 0.30 + p2
    val p4 = 0.10 + p3

    n match {
      case probability : Double if n < p1 => "AAA"
      case probability : Double if n < p2 => repeat(randomUpperCaseChar('B', 'C'), 3).concat(sign(required=false))
      case probability : Double if n < p3 => repeat(randomUpperCaseChar('A', 'B'), 2, 3).concat(sign(required=false))
      case probability : Double if n < p4 => repeat("C", 1, 2)
      case _ => "D"
    }
  }

  /**
   * Fitch:
   *             AAA, AA+, AA, AA-, A+, A, A-
   * BBB+, BBB, BBB-, BB+, BB, BB-, B+, B, B-
   * CCC,
   * DDD, DD, D
   * pattern: AAA | B{3}[-|+]{0,1} | [A|AA|B|BB]{1,2}[-|+]{0,1} | CCC | D{1,3}
   * */
  def fitchRating() = {
    val n = Random.nextDouble
    val p1 = 0.10
    val p2 = 0.40 + p1
    val p3 = 0.30 + p2
    val p4 = 0.10 + p3

    n match {
      case probability : Double if n < p1 => "AAA"
      case probability : Double if n < p2 => repeat("B", 3).concat(sign(required=false))
      case probability : Double if n < p3 => repeat(randomUpperCaseChar('A', 'B'), 1, 2).concat(sign(required=false))
      case probability : Double if n < p4 => repeat(randomUpperCaseChar('C', 'D'), 3)
      case _ => repeat("D", 1, 2)
    }
  }

}