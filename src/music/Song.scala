package music

import statistics.Statistics

class Song(val title: String, val artist: String, val youtubeId: String, val ratings: List[SongRating]) {

  def averageRating(): Double = {
    // This is an example of calling your average function to get the average rating of a song
    Statistics.average(this.ratings, (rating: SongRating) => rating.rating)
  }

  def averageEnergyRating(): Double = {
    // This is an example of calling your average function to get the average energy rating of a song
    Statistics.average(this.ratings, (rating: SongRating) => rating.energyLevel)
  }

  // Compute the bayesian average of song ratings
  def bayesianRating(extraRatings: Int, valueOfExtraRatings: Int): Double = {
    Statistics.bayesianAverage(this.ratings, (rating: SongRating) => rating.rating, extraRatings, valueOfExtraRatings)
  }

  def addRating(newRatings: SongRating): Song = {
    val list: List[SongRating] = this.ratings
    val newList: List[SongRating] = list :+ newRatings
    val songRef: Song = new Song(title, artist, youtubeId, newList)
    songRef
  }

  def addMultipleRatings(songRatingList: List[SongRating]): Song = {
    val list: List[SongRating] = this.ratings
    val newList: List[SongRating] = list ::: songRatingList
    val songRef: Song = new Song(title, artist, youtubeId, newList)
    songRef
    }
  }

