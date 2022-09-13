package com.indieproject.client.data.logs

/**
 * This class models all the medical environments as enum constants.
 *
 * @param metric A string representation of each enum.
 *
 * @author David
 */
enum class EnvMetric(private val metric: String) {
  PHARMACEUTICAL_STORAGE("PHARMACEUTICAL STORAGE"), PATIENT_WARD("PATIENT WARD"),
  INCUBATOR("INCUBATOR"), LAVATORY("LAVATORY");

  /**
   * This function is tasked with retrieving the string representation of the given enum.
   *
   * @return the string representation of a given enum class.
   */
  fun getMetric(): String {
    return this.metric
  }
}