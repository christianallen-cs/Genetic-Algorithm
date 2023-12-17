package tests

import org.scalatest._
import music._
import statistics.Statistics

class Task1 extends FunSuite {

  val songRatings: String = "data/song_ratings_2021.csv"
  val yourRatings: String = "data/yourRatings.csv"
  val EPSILON: Double = 0.001

  def compareDoubles(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) < EPSILON
  }

  test("test 1") {
    val Rating: List[SongRating] = List(new SongRating(2, 1),
                                        new SongRating(1, 5),
                                        new SongRating(1, 3),
                                        new SongRating(3, 2),
                                        new SongRating(4, 4))
    val getRating = (rating: SongRating) => rating.energyLevel.toDouble
    val computedResult = Statistics.average(Rating, getRating)
    val expectedResult: Double = 3.0
    assert(compareDoubles(computedResult, expectedResult))
  }

  test("test 2") {
    val data: List[Int] = List(1, 2, 3, 4)
    val f: Int => Double = (number: Int) => number.toDouble

    val computedResult: Double = Statistics.average(data, f)
    val expectedResult: Double = 2.5

    assert(compareDoubles(computedResult, expectedResult))
  }

  test("test 3") {
    val data: List[SongRating] = List(new SongRating(5, 5),
                                      new SongRating(2, 4))
    val f: SongRating => Double = (songRatings: SongRating) => songRatings.energyLevel.toDouble

    val computedResult: Double = Statistics.average(data, f)
    val expectedResult: Double = 4.5

    assert(compareDoubles(computedResult, expectedResult))

  }

  test("SongCostBadRating 1") {
    val UserRatings: Map[String, Int] = Map("12345678901" -> 5, "blahblah" -> 1)

    val CostFunction: Song => Double = Music.songCostFunction(UserRatings)
    val Song: Song = new Song("Example", "Me", "blahblah",
                List(new SongRating(5, 5)))

    val ComputedCost: Double = CostFunction(Song)
    val ExpectedCost: Double = 1000.0

    assert(compareDoubles(ComputedCost, ExpectedCost))
  }

  test("SongCostBadRating 2") {
    val UserRatings: Map[String, Int] = Map("random" -> 4, "youtubeIDs" -> 3, "xdxdxd" -> 2)

    val CostFunction: Song => Double = Music.songCostFunction(UserRatings)
    val Song: Song = new Song("Example", "Me", "xdxdxd",
      List(new SongRating(2, 5)))

    val ComputedCost: Double = CostFunction(Song)
    val ExpectedCost: Double = 1000.0

    assert(compareDoubles(ComputedCost, ExpectedCost))
  }

  test("SongCostBadRating 3") {
    val UserRatings: Map[String, Int] = Map("random" -> 2, "youtubeIDs" -> 3, "xdxdxd" -> 1)

    val CostFunction: Song => Double = Music.songCostFunction(UserRatings)
    val Song: Song = new Song("Example", "Me", "youtubeIDs",
      List(new SongRating(3, 5)))

    val ComputedCost: Double = CostFunction(Song)
    val ExpectedCost: Double = 0.11111111

    assert(compareDoubles(ComputedCost, ExpectedCost))
  }

  test("SongCostBadRating 4") {
    val UserRatings: Map[String, Int] = Map("randomLetter" -> 2, "youtubeID" -> 4, "hahaha" -> 5)

    val CostFunction: Song => Double = Music.songCostFunction(UserRatings)
    val Song: Song = new Song("Example", "Me", "youtubeID",
      List(new SongRating(4, 5)))

    val ComputedCost: Double = CostFunction(Song)
    val ExpectedCost: Double = 0.075

    assert(compareDoubles(ComputedCost, ExpectedCost))
  }

  test("SongCostBadRating 5") {
    val UserRatings: Map[String, Int] = Map("randomLetterz" -> 2, "youtubeIDzz" -> 4, "hahahaz" -> 5)

    val CostFunction: Song => Double = Music.songCostFunction(UserRatings)
    val Song: Song = new Song("Example", "Me", "hahahaz",
      List(new SongRating(5, 5), new SongRating(5,5)))

    val ComputedCost: Double = CostFunction(Song)
    val ExpectedCost: Double = 0.05

    assert(compareDoubles(ComputedCost, ExpectedCost))
  }

  test("SongCostBadRating No Rating") {
    val UserRatings: Map[String, Int] = Map("randomID" -> 2, "youtube" -> 4, "bozo" -> 5)

    val CostFunction: Song => Double = Music.songCostFunction(UserRatings)
    val Song: Song = new Song("Example", "Me", "notInMap",
                List(new SongRating(3,1)))

    val ComputedCost: Double = CostFunction(Song)
    val ExpectedCost: Double = 0.1111111

    assert(compareDoubles(ComputedCost, ExpectedCost))
  }

  test("topK plus 2") {
    val dataList: List[Int] = List(4, 0, 3, 4)

    val f: Int => Double = (number: Int) => number.toDouble
    val k: Int = 2

    val Computed: List[Int] = Statistics.topK(dataList, f, k)
    val Expected: List[Int] = List(4, 4)

    assert(Computed == Expected)
  }

  test("topK breaks when k larger than list") {
    val dataList: List[Int] = List(2, 1, 3, 4, 3)

    val f: Int => Double = (number: Int) => number.toDouble
    val k: Int = 6

    val Computed: List[Int] = Statistics.topK(dataList, f, k)
    val Expected: List[Int] = List(4, 3, 3, 2, 1)

    assert(Computed == Expected)
  }
}
