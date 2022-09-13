package com.indieproject.client.utils

import com.indieproject.client.data.iot.Environment
import com.indieproject.client.data.iot.EnvironmentData
import com.indieproject.client.data.iot.Status
import kotlin.random.Random

/**
 * This class is tasked with feeding data to
 * and generating EnvironmentData objects.
 *
 * @author David
 */
class EnvironmentUtils {
  
  /**
   * This method generates a random integer
   * to be assigned as an environment identifier.
   *
   * @return a random integer between 900 and 3000.
   */
  private fun setIdentifier(): Int {
    return Random.nextInt(900, 3000)
  }
  
  /**
   * This method generates a random Status object to describe
   * the condition of the given environment.
   *
   * @return a random Status object.
   */
  private fun setStatus(): Status {
    return Status.values().random()
  }
  
  /**
   * This method generates a random EnvMetric enum to describe
   * the given environment.
   *
   * @return a random EnvMetric enum.
   */
  private fun setEnvironment(): Environment {
    return Environment.values().random()
  }
  
  /**
   * This method generates an EnvironmentData object and feeds it with dummy data.
   *
   * @return an EnvironmentData object.
   */
   fun generateEnvironmentObject(): EnvironmentData {
    return EnvironmentData(this.setEnvironment(),
      this.setIdentifier(),
      this.setStatus())
  }
}