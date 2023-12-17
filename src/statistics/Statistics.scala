package statistics

object Statistics {

  def average[T](data: List[T], f: T => Double): Double = {
    val numbers: List[Double] = data.map(f)
    val sum: Double = numbers.sum
    val length: Double = numbers.length
    sum / length
  }

  def topK[T](data: List[T], f: T => Double, k: Int): List[T] = {
    val numberSorted = data.sortBy(f)
    val sortedList: List[T] = numberSorted.sortWith((a: T, b: T) => f(a) > f(b))
    val slicedList: List[T] = sortedList.slice(0, k)
    slicedList
  }

  def bayesianAverage[T](data: List[T], f: T => Double, fakeRating: Int, extraRating: Int): Double = {
    val number: List[Double] = data.map(f)
    val multiply: Double = fakeRating * extraRating
    val sum: Double = number.sum + multiply
    sum / (number.size + fakeRating)
  }

}
