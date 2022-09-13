package com.indieproject.client.utils

import kotlin.random.Random

/**
 * This class generates a random out-of-10 rating for each Status object.
 *
 * @author David
 */
class StatusUtils {
  /**
   * This method generates a random out-of-10 rating.
   *
   * return a random number from 1 to 10 representing a rating.
   */
  fun setRate(): Int {
    return Random.nextInt(1, 10)
  }
}