package tarsila.costalonga.searchwordapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tarsila.costalonga.searchwordapp.network.WordAPI
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object APIModule {

    private const val BASE_URL = "https://owlbot.info/"

    @Singleton
    @Provides
    fun provideAPI(): WordAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient())
            .baseUrl(BASE_URL)
            .build()
            .create(WordAPI::class.java)
    }

    fun okHttpClient(): OkHttpClient {

        val okhttpInterceptor = HttpLoggingInterceptor()

        okhttpInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)

        // Intercepta a request adicionando um Header e a apikey
        okHttpClient.addInterceptor {chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .header("Authorization", "Token b23bdcf34876e935b4584541c4175c03bbcb888f")
                .build()
            chain.proceed(request)
        }
        okHttpClient.addInterceptor(okhttpInterceptor)

        return okHttpClient.build()
    }
}