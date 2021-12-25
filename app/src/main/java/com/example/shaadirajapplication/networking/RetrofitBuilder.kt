
package com.example.shaadirajapplication.networking


import com.example.shaadirajapplication.BuildConfig
import com.example.shaadirajapplication.BuildConfig.API_BASE_URL
import com.example.shaadirajapplication.ShaadiApplication
import com.example.shaadirajapplication.common.NetworkConnectionInterceptor
import com.example.shaadirajapplication.common.Utils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object RetrofitBuilder {

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    var certPinner: CertificatePinner = CertificatePinner.Builder()
        .add(
            "*.randomuser.me",
            BuildConfig.PINNING_KEY_1
        )
        .build()

    private fun getOkHttpClient(): OkHttpClient {
       val logger = HttpLoggingInterceptor()
        val qminCacheSize = (5 * 1024 * 1024).toLong()
        val qminCache = Cache(ShaadiApplication.applicationContext().cacheDir, qminCacheSize)
        logger.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
         //.addInterceptor(logger)
            .addInterceptor(NetworkConnectionInterceptor(ShaadiApplication.applicationContext()))
            .addInterceptor(
                object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request: Request =
                            chain.request().newBuilder()
                                .build()
                        return chain.proceed(request)
                    }
                })
            .cache(qminCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (Utils.isNetworkAvailable(ShaadiApplication.applicationContext()))
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)

        return if (BuildConfig.DEBUG) {
            builder.build()

        } else {
            builder.certificatePinner(certPinner).build()
        }

       // return builder.build()
        }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}
