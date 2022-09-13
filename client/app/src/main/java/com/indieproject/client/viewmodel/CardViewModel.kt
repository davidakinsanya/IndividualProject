package com.indieproject.client.viewmodel


import androidx.lifecycle.ViewModel
import com.indieproject.client.db.DbHelper
import com.indieproject.client.repository.EnvRepository
import com.indieproject.client.repository.MonitorRepository

/**
 * This class serves as a ViewModel intended to
 * collect log data using the client side backend.
 *
 * @param mon a MonitorRepository object.
 * @param env an EnvironmentRepository object.
 * @param db a DbHelper object.
 *
 * @author david.
 */
class CardViewModel constructor (private val mon: MonitorRepository,
                                 private val env: EnvRepository,
                                 private val db: DbHelper,
): ViewModel() {

  /**
   * This function randomly selects a type of data to logs
   * and performs a single log operation.
   */
  fun getBootCards() {
    val randomLog = (0..3).toList().random()
    if (randomLog < 2) this.mon.logData(db) else this.env.logData(db)
  }
}