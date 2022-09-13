package com.indieproject.client.requests

import com.indieproject.client.data.iot.EnvironmentData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * This interface is tasked with making HTTP requests involving environment data.
 *
 * @author david
 */
interface EnvDataHandler {

    /**
     * This method performs a GET request to
     * retrieve EnvironmentData objects from a remote server application.
     *
     * @return an EnvironmentData object wrapped in a response.
     */
    @GET("/environment")
    fun getData(): Call<EnvironmentData>

    /**
     * This method performs a POST request to
     * send log messages to the blockchain via another remote server application.
     *
     * @return a successful or unsuccessful HTTP response.
     */
    @POST("/msg/{newMsg}")
    fun pushLogMessage(@Body newMsg: String): Call<String>
}