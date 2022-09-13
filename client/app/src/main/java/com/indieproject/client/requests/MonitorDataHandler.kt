package com.indieproject.client.requests

import com.indieproject.client.data.iot.MonitorData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * This interface is tasked with making HTTP requests involving monitor data.
 *
 * @author david
 */
interface MonitorDataHandler {

    /**
     * This method performs a GET request to
     * retrieve MonitorData objects from a remote server application.
     *
     * @return a MonitorData object wrapped in a response.
     */
    @GET("/monitor")
    fun getData(): Call<MonitorData>

    /**
     * This method performs a POST request to
     * send log messages to the blockchain via another remote server application.
     *
     * @return a successful or unsuccessful HTTP response.
     */
    @POST("/msg/{newMsg}")
    fun pushLogMessage(@Body newMsg: String): Call<String>
}