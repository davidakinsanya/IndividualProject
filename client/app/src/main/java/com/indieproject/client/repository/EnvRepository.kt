package com.indieproject.client.repository

import android.util.Log
import com.indieproject.client.data.iot.EnvironmentData
import com.indieproject.client.db.CardModel
import com.indieproject.client.db.DbHelper
import com.indieproject.client.msg.EnvMsg
import com.indieproject.client.requests.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This class controls the handling of Environment data on the client side.
 *
 * @author david.
 */
class EnvRepository {

  /**
   * This function creates a log message using the appropriate methods in the EnvMsg class.
   *
   * @param env an EnvironmentData object.
   * @param db a DbHelper object, used to interact with an sql local database.
   */
  fun generateLogMessage(env: EnvironmentData?, db: DbHelper) {
    val createMsg = EnvMsg(env)
    val msg = createMsg.generateLogMessage(createMsg.evalEnvironmentLog())
    pushLogMessage(env!!, msg, db, createMsg.getDangerStatus())
  }

  /**
   * This function initiates the process of logging data to the blockchain.
   *
   * @param db a DbHelper object.
   */
   fun logData(db: DbHelper) {
     RetrofitInstance.env.getData().enqueue(object : Callback<EnvironmentData?> {
       override fun onResponse(call: Call<EnvironmentData?>, response: Response<EnvironmentData?>) {
         generateLogMessage(response.body(), db)
       }

       override fun onFailure(call: Call<EnvironmentData?>, t: Throwable) {
         Log.d("Error", "GET REQUEST FAILED")

       }
     })
   }

  /**
   * This method pushes the log message to the blockchain
   * and adds it to the local database.
   *
   * @param env a EnvironmentData object.
   * @param db a DbHelper Object.
   * @param newMsg the log message produced.
   */
  private fun pushLogMessage(env: EnvironmentData, newMsg: String, db: DbHelper, danger: Int) {
    RetrofitInstance.envTwo.pushLogMessage(newMsg).enqueue(object : Callback<String?> {
      override fun onResponse(call: Call<String?>, response: Response<String?>) {
        db.addCard(CardModel().createCard(env.getIdentifier(), "ENV", newMsg, danger))
      }

      override fun onFailure(call: Call<String?>, t: Throwable) {
        Log.d("Error", "POST REQUEST FAILED")
      }
    })
  }
}