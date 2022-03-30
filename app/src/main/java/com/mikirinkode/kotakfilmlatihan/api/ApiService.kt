package com.mikirinkode.kotakfilmlatihan.api

import com.mikirinkode.kotakfilmlatihan.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMoviesList(
        @Query("api_key") apiKey: String
    ): Call<MovieListResponse>


    @GET("movie/{movieId}")
    fun getDetailMovie(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetailResponse>


    @GET("tv/popular")
    fun getPopularTvShowsList(
        @Query("api_key") apiKey: String
    ): Call<TvShowListResponse>

    @GET("tv/{tvShowId}")
    fun getDetailTvShow(
        @Path("tvShowId") tvShowId: Int,
        @Query("api_key") apiKey: String
    ): Call<TvShowDetailResponse>

}
