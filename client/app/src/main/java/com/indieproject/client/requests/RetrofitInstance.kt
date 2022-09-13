package com.indieproject.client.requests

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This object instantiates a Retrofit Instances for performing HTTP requests.
 *
 * @author david
 */
object RetrofitInstance {

  private const val base_url = "https://iot-simulator-iksj4.ondigitalocean.app"
  private const val second_base_url = "https://rpc-client-kywd6.ondigitalocean.app"

  val env: EnvDataHandler by lazy {
    Retrofit.Builder()
      .baseUrl(base_url)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(EnvDataHandler::class.java)
  }

  val mon: MonitorDataHandler by lazy {
    Retrofit.Builder()
      .baseUrl(base_url)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(MonitorDataHandler::class.java)
  }

  val envTwo: EnvDataHandler by lazy {
    Retrofit.Builder()
      .baseUrl(second_base_url)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(EnvDataHandler::class.java)
  }

  val monTwo: MonitorDataHandler by lazy {
    Retrofit.Builder()
      .baseUrl(second_base_url)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(MonitorDataHandler::class.java)
  }
}