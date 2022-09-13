package com.indieproject.client.repository

import android.util.Log
import com.indieproject.client.data.iot.MonitorData
import com.indieproject.client.db.CardModel
import com.indieproject.client.db.DbHelper
import com.indieproject.client.msg.MonitorMsg
import com.indieproject.client.requests.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This class controls the handling of Monitor data on the client side.
 *
 * @author david.
 */
class MonitorRepository() {

  /**
   * This function creates a log message using the appropriate methods in the MonitorMsg class.
   *
   * @param mon an MonitorData object.
   * @param db a DbHelper object, used to interact with an sql local database.
   */
  fun generateLogMessage(mon: MonitorData?, db: DbHelper) {
    val createMsg = MonitorMsg(mon)
    val msg = createMsg.generateLogMessage(createMsg.getLogList())
    pushLogMessage(mon!!, db, msg, createMsg.getDangerStatus())
  }

  /**
   * This function initiates the process of logging data to the blockchain.
   *
   * @param db a DbHelper object.
   */
  fun logData(db: DbHelper) {
    RetrofitInstance.mon.getData().enqueue(object : Callback<MonitorData?> {
      override fun onResponse(call: Call<MonitorData?>, response: Response<MonitorData?>) {
        generateLogMessage(response.body(), db)
      }

      override fun onFailure(call: Call<MonitorData?>, t: Throwable) {
        Log.d("Error", "GET REQUEST FAILED")
      }
    })
  }

  /**
   * This method pushes the log message to the blockchain
   * and adds it to the local database.
   *
   * @param mon a MonitorData object.
   * @param db a DbHelper object.
   * @param newMsg the log message produced.
   * @param danger a boolean value indicating if the patient could be in danger.
   */
  private fun pushLogMessage(mon: MonitorData, db: DbHelper, newMsg: String, danger: Int) {
    RetrofitInstance.monTwo.pushLogMessage(newMsg).enqueue(object : Callback<String?> {
      override fun onResponse(call: Call<String?>, response: Response<String?>) {
        db.addCard(CardModel().createCard(mon.getMonitorNumber(), "MON", newMsg, danger))
      }

      override fun onFailure(call: Call<String?>, t: Throwable) {
        Log.d("Error", "POST REQUEST FAILED")
      }
    })
  }
}