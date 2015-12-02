package rest

import messages.AppStartRequest
import messages.AppStartResponse
import messages.ExceptionInstanceWrapper
import messages.ExceptionSentResponse
import retrofit.Call
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.http.Body
import retrofit.http.POST

/**
 * Created by palfi on 2015-11-21.
 */

private val retrofit = Retrofit.Builder()
        .baseUrl("http://52.30.237.250:8090")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

interface ThrowingRestEndpoint {
    @POST("/exception")
    fun throwException(@Body exception: ExceptionInstanceWrapper): Call<ExceptionSentResponse>
}

public fun constructThrowingEndpoint(): ThrowingRestEndpoint = retrofit.create(ThrowingRestEndpoint::class.java)

interface AppStartEndpoint {
    @POST("/application/firstAppStart")
    fun firstAppStart(@Body appStartRequest: AppStartRequest): Call<AppStartResponse>
}

public fun constructAppStartEndpoint() = retrofit.create(AppStartEndpoint::class.java)
