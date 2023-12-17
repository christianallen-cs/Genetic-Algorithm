package tests

import org.scalatest._
import music._
import music.Song

class Task3 extends FunSuite {

  val EPSILON: Double = 0.001

  def compareDoubles(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) < EPSILON
  }

  def compareSongs(expected: Song, actual: Song): Unit = {

    assert(expected.youtubeId == actual.youtubeId)
    assert(expected.title == actual.title)
    assert(expected.artist == actual.artist)
    assert(expected.ratings.length == actual.ratings.length)

    //TODO: Check if both songs have the exact same rating and energyLevel values regardless of order
  }

  def compareSongMaps(actual: Map[String, Song], expected: Map[String, Song]): Unit = {
    assert(actual.size == expected.size)
    assert(actual.keys == expected.keys)

    for ((k,v_actual) <- actual){
      val v_expected = expected(k)
      compareSongs(v_expected, v_actual)
    }

    //TODO: Check if each map contains the same keys
    //TODO: Check each key maps to equivalent Songs in each map
  }

  test("add rating to song") {
    val initialSong = new Song("Sandstorm", "Darude", "y6120QOlsfU",
      List(new SongRating(5, 3)))

    val actual = initialSong.addRating(new SongRating(4, 4))

    val expected = new Song("Sandstorm", "Darude", "y6120QOlsfU",
      List(new SongRating(5, 3), new SongRating(4, 4)))

    compareSongs(expected, actual)
  }

  test("add multiple ratings to song") {
    val initialSong = new Song("HUMBLE.", "Kendrick Lamar", "tvTRZJ-4EyI",
      List(new SongRating(5,5)))

    val actual = initialSong.addMultipleRatings(List(new SongRating(3,3), new SongRating(4,4)))

    val expected = new Song("HUMBLE.", "Kendrick Lamar", "tvTRZJ-4EyI",
      List(new SongRating(5, 5), new SongRating(3, 3), new SongRating(4,4)))

    compareSongs(actual, expected)
  }

  test("convert song list to map with a duplicate") {
    val inputSong: List[Song] = List(new Song("Sandstorm", "Darude", "y6120QOlsfU",
      List(new SongRating(5, 5))),
      new Song("Sandstorm", "Darude", "y6120QOlsfU",
        List(new SongRating(5, 5))),
      new Song("Sandstorm", "Darude", "y6120QOlsfU",
        List(new SongRating(1,1))),
      new Song("Sandstorm", "Darude", "y6120QOlsfU",
        List(new SongRating(1, 1))))

    val expected: Map[String, Song] = Map("y6120QOlsfU" -> new Song("Sandstorm", "Darude", "y6120QOlsfU",
      List(new SongRating(5, 5),
        new SongRating(5, 5),
        new SongRating(1,1),
        new SongRating(1,1))))

    val actual: Map[String, Song] = Music.songListToMap(inputSong)

    compareSongMaps(actual, expected)
  }

  test("convert song to map with duplicate again") {
    val inputSong: List[Song] = List(new Song("lofi hip hop", "Lofi Girl", "5qap5aO4i9A",
      List(new SongRating(3, 3))),
      new Song("lofi hip hop", "Lofi Girl", "5qap5aO4i9A",
        List(new SongRating(1,1))))

    val expected: Map[String, Song] = Map("5qap5aO4i9A" -> new Song("lofi hip hop", "Lofi Girl", "5qap5aO4i9A",
      List(new SongRating(3,3),
        new SongRating(1,1))))

    val actual: Map[String, Song] = Music.songListToMap(inputSong)

    compareSongMaps(actual, expected)
  }
}
