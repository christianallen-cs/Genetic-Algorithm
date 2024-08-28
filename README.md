# UNIVERSITY AT BUFFALO

## Genetic Algorithm - Immutable

## Introduction

Genetic algorithms provide probabilistic solutions to optimization problems. These algorithms can be thought of as an advanced “guess and check” technique that eventually arrives at an output that is close to the actual solution without having to know how to compute the solution directly.

### Resources:
- [Wikipedia: Genetic Algorithm](https://en.wikipedia.org/wiki/Genetic_algorithm)
- [TutorialsPoint: Genetic Algorithms](https://www.tutorialspoint.com/genetic_algorithms/index.htm)

## Project Objective

You will be developing a generic genetic algorithm that can find approximate solutions to optimization problems. This algorithm should be designed for reusability across various applications suitable for genetic algorithms. Specifically, you will:
- Provide a cost function to evaluate potential solutions.
- Implement an incubator function to create potential solutions from a list of doubles (referred to as genes).

## Immutable Design

Your genetic algorithm must be designed without using any mutable variables or state. Specifically:
- **Variables (var)** are banned.
- The value at any memory address on the stack or heap cannot change throughout the execution of your program.
- Use only immutable values (val) to store data.
- Avoid any direct simulation of mutability, such as importing classes with mutable state variables.

## Learning Objectives and Tasks

### Lecture Task 1: Average and Top K

Develop methods to compute statistical values generically for a variety of data structures.

#### Testing:

**In the `statistics.Statistics` object:**
- **`average` Method:**
  - Parameters: `List<T>`, `Function<T, Double>`.
  - Returns: The average value after applying the function to each element.

- **`topK` Method:**
  - Parameters: `List<T>`, `Function<T, Double>`, `Int k`.
  - Returns: The top `k` elements with the highest outputs from the function.

**Examples:**
- Average and top K methods should handle a list of `SongRatings` with functions accessing the “rating” or “energy” of the songs.

### Lecture Task 2: Standard Deviation

Implement a method to compute the population standard deviation of any value from a list of objects.

#### Testing:

**In the `statistics.Statistics` object:**
- **`standardDeviation` Method:**
  - Parameters: `List<T>`, `Function<T, Double>`.
  - Returns: The standard deviation of values after applying the function.

### Lecture Task 3: Bayesian Average

Use Bayesian averaging to improve the reliability of ratings by adding “fake” ratings.

#### Testing:

**In the `statistics.Statistics` object:**
- **`bayesianAverage` Method:**
  - Parameters: `List<T>`, `Function<T, Double>`, `Int numFakeRatings`, `Int fakeRatingValue`.
  - Returns: The Bayesian average of the elements after applying the function.

### Lecture Task 4: Reading Song Data

Implement methods to parse song ratings from a CSV file and create a list of `Song` objects with their ratings.

#### Functionality:

**In the `Song` class:**
- **`addRating` Method:**
  - Parameters: `SongRating`.
  - Returns: A new `Song` with the added rating.

- **`readSongsFromFile` Method:**
  - Parameters: `String filename`.
  - Returns: A list of `Song` objects containing all ratings from the file.

### Lecture Task 5: Song Cost Function

Develop a cost function to measure how much a user would like or dislike a song based on their ratings.

#### Testing:

**In the `Song` object:**
- **`costFunction` Method:**
  - Parameters: `Map<String, Int> userRatings`.
  - Returns: A function that computes the cost of a `Song` based on user ratings.

### Lecture Task 6: Playlist Cost Function

Create a cost function for entire playlists to minimize the score based on song costs and playlist quality.

#### Testing:

**In the `Playlist` object:**
- **`costFunction` Method:**
  - Parameters: `Map<String, Int> userRatings`.
  - Returns: A function that computes the cost of a `Playlist` based on user ratings, duplicates, and energy level variety.

## Notes

After completing all tasks, you should be able to use your genetic algorithm to generate playlists based on user preferences. Enjoy the music!
