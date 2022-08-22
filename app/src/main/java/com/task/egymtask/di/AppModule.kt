package com.task.egymtask.di

import android.app.Application
import androidx.room.Room
import com.task.egymtask.model.StoriesDatabase
import com.task.egymtask.model.api.ApiService
import com.task.egymtask.model.api.GeneralApiHelperImpl
import com.task.egymtask.model.repo.GeneralRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(18, TimeUnit.SECONDS)
            .writeTimeout(18, TimeUnit.SECONDS)
            .readTimeout(18, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            })
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = clientBuilder
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiRepo(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGeneralRepo(apiService: ApiService,db:StoriesDatabase): GeneralRepo {
        return GeneralRepo(
            GeneralApiHelperImpl(
                apiService,
                db
            )
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : StoriesDatabase =
        Room.databaseBuilder(app, StoriesDatabase::class.java, "stories_database")
            .build()
}