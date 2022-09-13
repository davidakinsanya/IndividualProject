package com.indieproject.client.data.logs

/**
 * This class models all the hospital monitor readings as enum constants.
 *
 * @param metric A string representation of each enum.
 *
 * @author David
 */

enum class MonitorMetric(private val metric: String) {
  PULSE("PULSE"), BP("BLOOD PRESSURE"),
  OS("OXYGEN SATURATION"), RESPIRATION("RESPIRATION"),
  TEMPERATURE("TEMPERATURE");

  /**
   * This function is tasked with retrieving the string representation of the given enum.
   *
   * @return the string representation of a given enum class.
   */
  fun getMetric(): String {
    return this.metric
  }
}