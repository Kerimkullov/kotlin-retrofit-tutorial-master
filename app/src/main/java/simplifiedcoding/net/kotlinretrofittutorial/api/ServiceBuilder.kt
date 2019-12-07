package simplifiedcoding.net.kotlinretrofittutorial.api


import android.os.Build
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    private const val URL = "http://157.230.122.15:8800/auth/v1/"
    //create interceptor
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    //create a custom interceptor to apply headers application wide
    val headerInterceptor = object: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                    .addHeader("x-device-type", Build.DEVICE)
                    .addHeader("Accept-language", Locale.getDefault().language)
                    .build()
            val response = chain.proceed(request)
            return response
        }
    }
    //create OkHttp client
    private val okHttp = OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .addInterceptor(logger)
    //create Retrofit Builder
    private  val builder = Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttp.build())
    //create Retrofit instance
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}