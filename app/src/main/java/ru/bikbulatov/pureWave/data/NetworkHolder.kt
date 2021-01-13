package ru.bikbulatov.pureWave.data

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkHolder {
    const val baseurl = "https://api.ufanet.ru/"
    var retrofitClient: Retrofit
    var authenticatorRetrofitClient: Retrofit

    //    var apiRepository: ApiRepository
    var httpClient: OkHttpClient

    init {
        authenticatorRetrofitClient = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseurl)
            .build()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 2

        httpClient = OkHttpClient().newBuilder()
            .followRedirects(false)
            .followSslRedirects(false)
            .addInterceptor(interceptor)
//            .authenticator(ApiAuthenticator())
            .dispatcher(dispatcher)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        retrofitClient = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseurl)
            .client(httpClient)
            .build()

//        apiRepository = ApiRepository(
//            retrofitClient.create(ICommonApi::class.java),
//            retrofitClient.create(IAuthApi::class.java),
//            retrofitClient.create(ISimCardApi::class.java),
//            retrofitClient.create(IFirebaseApi::class.java),
//            retrofitClient.create(IDocumentApi::class.java),
//            retrofitClient.create(IChatApi::class.java),
//            retrofitClient.create(IPaymentApi::class.java),
//            retrofitClient.create(IOperationsApi::class.java),
//            retrofitClient.create(ITariffApi::class.java),
//            retrofitClient.create(INpsApi::class.java),
//            retrofitClient.create(CerberCryptApi::class.java)
//        )
    }
}