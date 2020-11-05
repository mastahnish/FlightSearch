package pl.myosolutions.flightsearch.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import pl.myosolutions.flightsearch.Constants
import pl.myosolutions.flightsearch.Constants.BaseUrl.getBaseUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val SECONDS_TIMEOUT = 30L

object RestApi {

    fun buildRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .client(getOkHttpClient())
            .build()

    private fun getOkHttpClient() =
        OkHttpClient.Builder()
            .connectTimeout(SECONDS_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(SECONDS_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(SECONDS_TIMEOUT, TimeUnit.SECONDS)
            .build()

    private fun getGson(): Gson =
        GsonBuilder()
            .setDateFormat(Constants.DATE_TIME_FORMAT)
            .create()


}