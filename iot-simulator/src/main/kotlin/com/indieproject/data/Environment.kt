package com.indieproject.data

import kotlinx.serialization.Serializable

/**
 * This class models the different static environments in generic hospitals.
 *
 * @author David
 */
@Serializable
enum class Environment() {
  PHARMACEUTICAL_STORAGE, PATIENT_WARD, INCUBATOR, LAVATORY;
}