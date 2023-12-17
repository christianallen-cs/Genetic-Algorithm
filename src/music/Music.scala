package music

import scala.io.{BufferedSource, Source}

object Music {



  /**
   * You may use this helper method to read a file and return a List of Strings containing the lines of the file
   *
   * @param filename The file to be read
   * @return The lines of the file as a List of Strings with 1 String per line
   */
  def filenameToListOfLines(filename: String): List[String] = {
    val file = Source.fromFile(filename)
    val lines: List[String] = file.getLines().toList
    file.close()
    lines
  }

  // Uses your methods to read all the data in a file with song ratings
    def readSongsFromFile(filename: String): List[Song] = {
      val songsWithDuplicates: List[Song] = readSongsFromFileWithoutDuplicates(filename)
      val songMap: Map[String, Song] = songListToMap(songsWithDuplicates)
      songMap.values.toList
    }

  // Can be used to test your application objective
  def songIncubator(songs: List[Song]): List[Double] => Song = {
    genes: List[Double] => {
      val geneSong: Int = (genes.head.abs * songs.length).toInt % songs.length
      songs(geneSong)
    }
  }

  // Can be used to test your application objective
  def playlistIncubator(songs: List[Song]): List[Double] => Playlist = {
    genes: List[Double] => {
      val incubatorForSongs: List[Double] => Song = songIncubator(songs)
      new Playlist(genes.map((gene: Double) => incubatorForSongs(List(gene))))
    }
  }

  def songCostFunction(UserRating: Map[String, Int]): Song => Double = {
    val comparator: Song => Double = (a: Song) => {
      val rating: Int = UserRating.getOrElse(a.youtubeId, 3)
      if (rating <= 2) {
        1000.0
      } else {
        1 / (a.bayesianRating(2,3) * rating)
      }
    }
    comparator
  }

  def readUserRatingsFromFile(SingleUserRatingsFile: String): Map[String, Int] = {
    val file: List[String] = filenameToListOfLines(SingleUserRatingsFile)
    readRatingsHelper(Map(), file)
  }

  def readRatingsHelper(ratings: Map[String, Int], file: List[String]): Map[String, Int] = {
    if (file.isEmpty) {
      ratings
    } else {
      val stored: String = file.head
      val split: Array[String] = stored.split(",")
      val map: Map[String, Int] = Map(split(0) -> split(3).toInt)
      readRatingsHelper(ratings ++ map, file.drop(1))
      }
  }

  def readSongsFromFileWithoutDuplicates(filename: String): List[Song] = {
    val file: List[String] = filenameToListOfLines(filename)
    readSongsFromFileWithoutDuplicatesHelper(List(), file)
  }

  def readSongsFromFileWithoutDuplicatesHelper(songs: List[Song], lines: List[String]): List[Song] = {
    if (lines.length == 1) {
      songs
    } else {
      val stored: String = lines.head
      val split: Array[String] = stored.split(",")
      val list: List[Song] = List(new Song(split(2), split(1), split(0),
        List(new SongRating(split(3).toInt, split(4).toInt))))
      list ::: readSongsFromFileWithoutDuplicatesHelper(songs, lines.slice(1, lines.length))
    }
  }

  def songListToMap(songsList: List[Song]): Map[String, Song] = {
    if (songsList.isEmpty) {
       Map()
    } else if (songsList.length == 1) {
      Map(songsList.head.youtubeId -> songsList.head)
    } else {
      val repeatList: List[Song] = songsList.filter(_.youtubeId != songsList.head.youtubeId)
      val stored: List[Song] = songsList.filter(_.youtubeId == songsList.head.youtubeId)
      val listOfRating: List[SongRating] = stored.map(_.ratings.head)
      val songsStored: Song = new Song(songsList.head.title, songsList.head.artist, songsList.head.youtubeId, listOfRating)
      songListToMap(repeatList) + (songsStored.youtubeId -> songsStored)
      }
    }
}





