package com.indieproject.client.data.logs

/**
  * This class models the individual rating of a particular medical environment.
  *
  * @param env An EnvMetric object, indicating an environment metric.
  * @param log A MsgLog object, indicating the arbitrary rating of the EnvMetric object.
  *
  * @author David
 */
data class EnvironmentLog(private val env: EnvMetric, private val log: MsgLog) {
  /**
   * This function is tasked with retrieving the EnvMetric object.
   *
   * @return a EnvMetric Object.
   */
  fun getEnvMetric(): EnvMetric {
    return this.env
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
