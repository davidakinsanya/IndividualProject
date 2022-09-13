package com.indieproject.client.msg

import com.indieproject.client.data.iot.Environment
import com.indieproject.client.data.iot.EnvironmentData
import com.indieproject.client.data.iot.Status
import com.indieproject.client.data.logs.EnvMetric
import com.indieproject.client.data.logs.EnvironmentLog
import com.indieproject.client.data.logs.MsgLog

/**
 * This class interprets EnvironmentData objects to produce log messages.
 *
 * @param env An EnvironmentData object.
 *
 * @author david
 */

class EnvMsg constructor(private val env: EnvironmentData?) {
  private var dangerStatus = 0
  /**
   * This function is tasked with translating Environment enums to EnvMetric enums.
   */
  private fun evalType(): EnvMetric {
    return when(this.env!!.getEnvironment()) {
      Environment.PHARMACEUTICAL_STORAGE -> EnvMetric.PHARMACEUTICAL_STORAGE
      Environment.INCUBATOR -> EnvMetric.INCUBATOR
      Environment.PATIENT_WARD -> EnvMetric.PATIENT_WARD
      Environment.LAVATORY -> EnvMetric.LAVATORY
    }
  }

  /**
   * This function is tasked with evaluating the EnvironmentData object to produce a MsgLog object.
   *
   * @return a MsgLog object, indicating the rating of the individual metric.
   */
  private fun evalLog(): MsgLog {
    val log: MsgLog = if (this.env!!.getStatus() == Status.SUB_OPTIMAL) {
      MsgLog.SUB_OPTIMAL
    } else if (this.env.getStatus() == Status.MODERATE && this.env.getIdentifier() <= 6) {
      MsgLog.MODERATE
    } else if (this.env.getStatus() == Status.MODERATE && this.env.getIdentifier() > 6) {
      MsgLog.STANDARD
    } else if (this.env.getStatus() == Status.STANDARD && this.env.getIdentifier() <= 5) {
      MsgLog.STANDARD
    } else {
      MsgLog.SUB_OPTIMAL
    }

    return log
  }

  /**
   * This function is tasked with generating an EnvironmentLog object from the prior methods.
   *
   * @return an EnvironmentLog object, combining the metric and its rating.
   */
  fun evalEnvironmentLog(): EnvironmentLog {
    if (evalLog() == MsgLog.SUB_OPTIMAL) dangerStatus++
    return EnvironmentLog(evalType(), evalLog())
  }

  /**
   * This function generates a log message from an EnvironmentLog object.
   *
   * @return a string representation of the log message.
   */
  fun generateLogMessage(log: EnvironmentLog): String {
    return "${log.getEnvMetric().getMetric()} #${this.env!!.getIdentifier()}: ${log.getLog().getStatus()}"
  }

  fun getDangerStatus():Int {
    return dangerStatus
  }
}