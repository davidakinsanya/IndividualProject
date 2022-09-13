package com.indieproject.client.data.iot

/**
 *  This class models the data displayed on
 *  a vitals monitor for
 *  hospital patients.
 *
 *
 *  @param MonitorNumber A monitor number from the simulated IOT device.
 *  @param Pulse A pulse reading from the simulated IOT device.
 *  @param BloodPressure A blood pressure reading from the simulated IOT device.
 *  @param OxygenSaturation An oxygen saturation reading from the simulated IOT device.
 *  @param Respiration A respiration rate reading from the simulated IOT device.
 *  @param Temperature A temperature reading from the simulated IOT device.
 *  @author David
 */
data class MonitorData(
                       private val MonitorNumber: Int,
                       private val Pulse: Int,
                       private val BloodPressure: Array<Int>,
                       private val OxygenSaturation: Int,
                       private val Respiration: Int,
                       private val Temperature: Double,
                       ) {
  
  /**
   * This method retrieves the pulse reading from the
   * MonitorData object sent from the simulated IOT device.
   *
   *
   * @return the monitor number from the
   * MonitorData object sent from the
   * simulated IOT device.
   */
  fun getMonitorNumber(): Int {
  return MonitorNumber
  }
  /**
   * This method retrieves the pulse reading from the
   * MonitorData object sent from the simulated IOT device.
   *
   *
   * @return the pulse reading from the
   * MonitorData object sent from the
   * simulated IOT device.
   */
  fun getPulse(): Int {
    return Pulse
  }
  /**
   * This method retrieves the blood pressure reading from the
   * MonitorData object sent from the simulated IOT device.
   *
   *
   * @return the blood pressure reading from the
   * MonitorData object sent from the
   * simulated IOT device.
   */
  fun getBloodPressure(): Array<Int> {
    return BloodPressure
  }
  
  /**
   * This method retrieves the oxygen saturation reading from the
   * MonitorData object sent from the simulated IOT device.
   *
   *
   * @return the oxygen saturation from the
   * MonitorData object sent from the
   * simulated IOT device.
   */
  fun getOxygenSaturation(): Int {
    return OxygenSaturation
  }
  
  /**
   * This method retrieves the respiration rate reading
   * from the MonitorData object sent
   * from the simulated IOT device.
   *
   *
   * @return the respiration rate from the
   * MonitorData object sent from the
   * simulated IOT device.
   */
  fun getRespiration(): Int {
    return Respiration
  }
  
  /**
   * This method retrieves the temperature reading from the
   * MonitorData object sent from the simulated IOT device.
   *
   *
   * @return the temperature reading from the
   * MonitorData object sent from the
   * simulated IOT device.
   */
  fun getTemperature(): Double {
    return Temperature
  }
  
  /**
   * This method provides
   * a string representation of the data points
   * in the MonitorData class.
   *
   * @return a string representation of the data points
   * in the MonitorData class.
   */
  override fun toString(): String {
    return "Monitor #${this.getMonitorNumber()} reads: " +
            "\n\nPulse: ${this.getPulse()} Beats Per Minute" +
            "\nBlood Pressure: ${this.getBloodPressure()[0]}/${this.getBloodPressure()[1]}mmHg" +
            "\nOxygen Saturation: ${this.getOxygenSaturation()}%" +
            "\nResipration: ${this.getRespiration()} Breaths Per Minute" +
            "\nTemperature: ${this.getTemperature()}°C"
  }
  
}
