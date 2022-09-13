package com.indieproject.client.data.logs
/**
 * This class models the individual rating of a particular hospital monitor reading.
 *
 * @param metric A MonitorMetric object, indicating a specific monitor reading attribute.
 * @param log A MsgLog object, indicating the arbitrary rating of the MonitorMetric object.
 *
 * @author David
 */
data class MonitorLog(private val metric: MonitorMetric, private val log: MsgLog) {

  /**
   * This function is tasked with retrieving the MonitorMetric object.
   *
   * @return a MonitorMetric Object.
   */
  fun getMetric(): MonitorMetric {
    return this.metric
  }

  /**
   * This function is tasked with retrieving the MsgLog object.
   *
   * @return a MsgLog Object.
   */
  fun getLog(): MsgLog {
    return this.log
  }
}
