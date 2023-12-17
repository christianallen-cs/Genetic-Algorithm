package genetics

object GeneticAlgorithm {

  def geneticAlgorithm[T](incubator: List[Double] => T, costFunction: T => Double, numberOfGenes: Int): T = {
    incubator(List())
    // TODO: Application Objective
  }

}

