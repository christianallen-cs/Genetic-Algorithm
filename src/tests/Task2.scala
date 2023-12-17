package tests

import org.scalatest._
import music._

class Task2 extends FunSuite {

  val songRatings: String = "data/song_ratings_2021.csv"
  val yourRatings: String = "data/yourRatings.csv"
  val EPSILON: Double = 0.001

  def compareDoubles(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) < EPSILON
  }

  test("parse CSV") {
    val filename: String = "data/yourRatings.csv"

    val expectedOutput: Map[String, Int] = Map(
      "tvTRZJ-4EyI" -> 5,
      "k2qgadSvNyU" -> 4,
      "y6120QOlsfU" -> 5,
      "5qap5aO4i9A" -> 2
    )

    val computedOutput: Map[String, Int] = Music.readUserRatingsFromFile(filename)

    assert(computedOutput == expectedOutput)
  }
}
