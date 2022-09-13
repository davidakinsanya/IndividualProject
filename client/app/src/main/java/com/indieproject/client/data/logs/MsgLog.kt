package com.indieproject.client.data.logs

/**
 * This class models an arbitrary rating of both monitor readings and medical environments
 * as enum constants.
 *
 * @param status a string representation of the given enum constant.
 *
 * @author David
 */
enum class MsgLog(private val status: String) {
  SUB_OPTIMAL("SUB OPTIMAL"),
  MODERATE("MODERATE"),
  STANDARD("STANDARD"),
  OPTIMAL("OPTIMAL");

  /**
   * This function retrives the string representation of each enum constant.
   *
   * @return a string representation of each enum constant.
   */
  fun getStatus(): String { return this.status }
}