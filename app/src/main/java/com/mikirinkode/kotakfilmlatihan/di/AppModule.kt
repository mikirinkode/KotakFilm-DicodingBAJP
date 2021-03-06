package com.mikirinkode.kotakfilmlatihan.di

import android.content.Context
import androidx.room.Room
import com.mikirinkode.kotakfilmlatihan.api.ApiService
import com.mikirinkode.kotakfilmlatihan.data.MovieRepository
import com.mikirinkode.kotakfilmlatihan.data.source.local.LocalDataSource
import com.mikirinkode.kotakfilmlatihan.data.source.local.MovieDao
import com.mikirinkode.kotakfilmlatihan.data.source.local.MovieDatabase
import com.mikirinkode.kotakfilmlatihan.data.source.remote.RemoteDataSource
import com.mikirinkode.kotakfilmlatihan.utils.AppExecutors
import com.mikirinkode.kotakfilmlatihan.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(api: ApiService): RemoteDataSource{
        return RemoteDataSource.getInstance(api)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(movieDao: MovieDao): LocalDataSource{
        return LocalDataSource.getInstance(movieDao)
    }

    @Singleton
    @Provides
    fun provideRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): MovieRepository {
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext app: Context): MovieDatabase {
//        val db = MovieDatabase.getInstance(app)
        return Room.databaseBuilder(app, MovieDatabase::class.java, "kotakfilm_db").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }
}