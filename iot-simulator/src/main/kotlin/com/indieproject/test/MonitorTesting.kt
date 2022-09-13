package com.indieproject.test

import com.indieproject.data.MonitorData
import com.indieproject.utils.MonitorUtils

class MonitorTesting {
  private val util = MonitorUtils()
  private val data: MonitorData =
    MonitorData(util.setMonitorNumber(),
                util.setPulse(),
                util.setBloodPressure(),
                util.setOxygenSaturation(),
                util.setRespiration(),
                util.setTemperature())
  
  
  fun main() {
    println(data.toString())
  }
}