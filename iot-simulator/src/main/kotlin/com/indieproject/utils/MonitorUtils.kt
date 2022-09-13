package com.indieproject.utils

import com.indieproject.data.MonitorData
import kotlin.random.Random

/**
 * This class is tasked with feeding data to and generating MonitorData objects.
 *
 * @author David
 */
class MonitorUtils {
  
  /**
   * This method generates a random value
   * to be assigned as the objects monitor number.
   *
   * @return a random value representing a monitor number.
   */
  fun setMonitorNumber(): Int {
    return Random.nextInt(1, 250)
  }
  
  
  /**
   * This method generates a random value
   * to be assigned as the pulse reading.
   *
   * @return a random value representing a pulse reading.
   */
  fun setPulse(): Int {
    return Random.nextInt(70,100) // 60-100 normaL
  }
  
  /**
   * This method generates a random two-integer array
   * to be assigned as the blood pressure reading.
   *
   * @return an array representation of a
   * blood pressure reading.
   */
  fun setBloodPressure(): Array<Int> {
    return arrayOf(Random.nextInt(99,120), Random.nextInt(75,90)) // [90,60] - [120,80] normal
  }
  
  
  /**
   * This method generates a random integer
   * to be assigned as the respiration rate reading.
   *
   * @return a random value representing a respiration rate reading.
   */
  fun setRespiration(): Int {
    return Random.nextInt(12,20) // 10-20 normal
  }
  
  
  /**
   * This method generates a random integer
   * to be assigned as the oxygen saturation reading.
   *
   * @return a random value representing an oxygen saturation reading.
   */
  fun setOxygenSaturation(): Int {
    return Random.nextInt(85, 100)
  }
  
  
  /**
   * This method generates a random integer
   * to be assigned as the temperature reading.
   *
   * @return a random value representing a temperature reading.
   */
  fun setTemperature(): Double {
    return Random.nextDouble(36.485, 37.901)
  }
  
  
  /**
   * This method generates a MonitorData object and feeds it with dummy data.
   *
   * @return a MonitorData object.
   */
  fun generateMonitorObject(): MonitorData {
    return MonitorData(
      this.setMonitorNumber(),
      this.setPulse(),
      this.setBloodPressure(),
      this.setOxygenSaturation(),
      this.setRespiration(),
      this.setTemperature()
    )
  }
}