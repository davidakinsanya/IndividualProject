package com.indieproject.client.msg

import android.util.Log
import com.indieproject.client.data.iot.MonitorData
import com.indieproject.client.data.logs.MonitorLog
import com.indieproject.client.data.logs.MonitorMetric
import com.indieproject.client.data.logs.MsgLog

/**
 * This class interprets MonitorData objects to produce log messages
 * and detect 'danger' amongst given logs.
 *
 * @param mon a MonitorData object.
 *
 * @author david
 */
class MonitorMsg constructor(private val mon: MonitorData?) {
  private var dangerStatus: Int = 0

  /**
   * This function is tasked with evaluating the pulse reading
   * of the MonitorData object to produce a MonitorLog object.
   *
   * @return a MonitorLog object, combining the monitor reading as well as its rating.
   */
  private fun evalPulse(): MonitorLog {
    val log: MonitorLog;
    when {
      mon!!.getPulse() <= 95 -> {
        log = MonitorLog(MonitorMetric.PULSE, MsgLog.SUB_OPTIMAL)
      }
      mon.getPulse() in 75..85 -> {
        log = MonitorLog(MonitorMetric.PULSE, MsgLog.STANDARD)
      }
      else -> {
        log = MonitorLog(MonitorMetric.PULSE, MsgLog.OPTIMAL)
      }
    }
    return log
  }

  /**
   * This function is tasked with evaluating the blood pressure
   * of the MonitorData object to produce a MonitorLog object.
   *
   * @return a MonitorLog object, combining the monitor reading as well as its rating.
   */
  private fun evalBloodPressure(): MonitorLog {
    val log: MonitorLog;

    when {
      mon!!.getBloodPressure()[0] <= 115 && mon.getBloodPressure()[1] <= 85 -> {
        log = MonitorLog(MonitorMetric.BP, MsgLog.SUB_OPTIMAL)
      }
      mon.getBloodPressure()[0] in 110..114 &&  mon.getBloodPressure()[1] in 80..84 -> {
        log = MonitorLog(MonitorMetric.BP, MsgLog.STANDARD)
      }
      else -> {
        log = MonitorLog(MonitorMetric.BP, MsgLog.OPTIMAL)
      }
    }

    return log
  }

  /**
   * This function is tasked with evaluating the oxygen saturation
   * of the MonitorData object to produce a MonitorLog object.
   *
   * @return a MonitorLog object, combining the monitor reading as well as its rating.
   */
  private fun evalOxygenSaturation(): MonitorLog {
    val log: MonitorLog;

    when {
      mon!!.getOxygenSaturation() <= 95 -> {
        log = MonitorLog(MonitorMetric.OS, MsgLog.SUB_OPTIMAL)
      }
      mon.getOxygenSaturation() in 87..94 -> {
        log = MonitorLog(MonitorMetric.OS, MsgLog.STANDARD)
      }
      else -> {
        log = MonitorLog(MonitorMetric.OS, MsgLog.OPTIMAL)
      }
    }

    return log
  }

  /**
   * This function is tasked with evaluating the respiration rate reading
   * of the MonitorData object to produce a MonitorLog object.
   *
   * @return a MonitorLog object, combining the monitor reading as well as its rating.
   */
  private fun evalRespiration(): MonitorLog {
    val log: MonitorLog;
    when {
      mon!!.getRespiration() <= 13 -> {
        log = MonitorLog(MonitorMetric.RESPIRATION, MsgLog.SUB_OPTIMAL)
      }
      mon.getRespiration() in 14..17 -> {
        log = MonitorLog(MonitorMetric.RESPIRATION, MsgLog.STANDARD)
      }
      else -> {
        log = MonitorLog(MonitorMetric.RESPIRATION, MsgLog.OPTIMAL)
      }
    }
    return log
  }

  /**
   * This function is tasked with evaluating the temperature
   * of the MonitorData object to produce a MonitorLog object.
   *
   * @return a MonitorLog object, combining the monitor reading as well as its rating.
   */
  private fun evalTemperature(): MonitorLog {
    val log: MonitorLog;
    when {
      mon!!.getTemperature() <= 36.6 -> {
        log = MonitorLog(MonitorMetric.TEMPERATURE, MsgLog.SUB_OPTIMAL)
      }

      mon.getTemperature() in 36.61..37.1 -> {
        log = MonitorLog(MonitorMetric.TEMPERATURE, MsgLog.STANDARD)
      }
      else -> {
        log = MonitorLog(MonitorMetric.TEMPERATURE, MsgLog.OPTIMAL)
      }
    }
    return log
  }
  /**
   * This function is tasked with generating a list of MonitorLog objects from the prior methods.
   *
   * @return a list of MonitorLog objects, combining the all the metric and
   *         their respective ratings.
   */
   fun getLogList(): List<MonitorLog> {
    return listOf(evalPulse(), evalBloodPressure(),
      evalOxygenSaturation(), evalRespiration(),
      evalTemperature()
    )
  }


  /**
   * This function generates a log message from a list of MonitorLog objects.
   *
   * @return a string representation of the log message.
   */
  fun generateLogMessage(logList: List<MonitorLog>): String {
    var msg: String = "MONITOR #${mon!!.getMonitorNumber()}:"
    for (item in logList) {
      msg += " ${item.getMetric()}: ${item.getLog().getStatus()}"
    }

    if (((0..1).random() == 1)) { dangerStatus = 1 }

    return msg
  }

  /**
   * A function revealing whether or the MonitorData object
   * is poses potential danger to the patient.
   *
   * @return the boolean indicating danger within the object.
   */
  fun getDangerStatus(): Int {
    return dangerStatus
  }
}