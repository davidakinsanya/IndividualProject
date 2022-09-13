package com.indieproject.client.data.iot


/**
 * This class models the conditions of specific hospital environments.
 *
 *
 * @param environment An Environment object.
 * @param identifier An arbitrary integer identifying the environment object.
 * @param status A Status object.
 * @author David
 */
data class EnvironmentData(private val environment: Environment,
                           private val identifier: Int,
                           private val status: Status
) {
  
  /**
   * This method retrieves the environment identifier.
   *
   * @return the environment identifier.
   */
  fun getIdentifier(): Int {
    return identifier
  }
  
  
  /**
   * This method retrieves the environment condition rating.
   *
   * @return the environment condition rating.
   */
  fun getStatus(): Status {
    return status
  }
  
  /**
   * This method retrieves the
   * specific hospital environment subject to rating.
   *
   * @return the specific hospital environment subject to rating.
   */
  fun getEnvironment(): Environment {
    return environment
  }
  
  
  /**
   * This method provides a string representation
   * of the data points modeled by the EnvironmentData class.
   *
   *
   * @return a string representation
   * of the data points modeled by the EnvironmentData class.
   */
  override fun toString(): String {
    return "${this.getEnvironment()} #${this.getIdentifier()} " +
            "Condition reads as ${this.getStatus()} " +
            "(${this.getStatus().getRate()}/10)"
  }
}
