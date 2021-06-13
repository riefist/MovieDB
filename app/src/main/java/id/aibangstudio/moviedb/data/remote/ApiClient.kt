package id.aibangstudio.moviedb.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import id.aibangstudio.moviedb.BuildConfig
import java.util.concurrent.TimeUnit


fun provideOkHttpClient(interceptor: MovieApiInterceptor): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.apply {
        writeTimeout(60, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        callTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(logging)
        }
        addInterceptor(interceptor)
    }
    return httpClient.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}


class MovieApiInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val url = request.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("language", "en-US")
            .addQueryParameter("include_adult", "false")
            .addQueryParameter("include_video", "false")
            .addQueryParameter("sort_by", "popularity.desc")
            .build()

        val requestBuilder = request.newBuilder()
            .url(url)
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .build()

        return chain.proceed(requestBuilder)
    }
}

