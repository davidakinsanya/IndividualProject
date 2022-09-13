package com.indieproject.client.data.iot

import com.indieproject.client.utils.StatusUtils

/**
 * This class models the static categories of
 * standardizing conditions in hospital environments.
 *
 *
 * @param rate the out of 10 rating within each static category.
 * @author David
 */
enum class Status(private val rate: Int) {
  SUB_OPTIMAL(StatusUtils().setRate()),
  MODERATE(StatusUtils().setRate()),
  STANDARD(StatusUtils().setRate()),
  OPTIMAL(StatusUtils().setRate());
  
  /**
   * This method retrieves the out of 10 rating
   * within each static category.
   *
   *
   * @return the out of 10 rating
   * within each static category.
   */
  fun getRate(): Int {
    return rate
  }
}