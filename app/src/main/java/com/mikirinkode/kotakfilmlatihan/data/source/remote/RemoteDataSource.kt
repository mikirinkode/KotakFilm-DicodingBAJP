package com.mikirinkode.kotakfilmlatihan.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mikirinkode.kotakfilmlatihan.api.ApiService
import com.mikirinkode.kotakfilmlatihan.data.source.remote.response.MovieDetailResponse
import com.mikirinkode.kotakfilmlatihan.data.source.remote.response.MovieListResponse
import com.mikirinkode.kotakfilmlatihan.data.source.remote.response.TvShowDetailResponse
import com.mikirinkode.kotakfilmlatihan.data.source.remote.response.TvShowListResponse
import com.mikirinkode.kotakfilmlatihan.utils.Constants
import com.mikirinkode.kotakfilmlatihan.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: ApiService){

    private val apiKey = Constants.API_KEY

    fun getMovieList(): LiveData<ApiResponse<MovieListResponse>>{
        EspressoIdlingResource.increment()
        val movieListResult = MutableLiveData<ApiResponse<MovieListResponse>>()

        api.getPopularMoviesList(apiKey).enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if(response.isSuccessful){
                    movieListResult.value = response.body()?.let { ApiResponse.success(it) }
                }
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "Failed to Get Popular Movie List", t)
                Log.e("RemoteDataSource", t.message.toString())
                EspressoIdlingResource.decrement()
            }

        })
        return movieListResult
    }

//    fun getPopularMoviesList(callback:LoadMoviesListCallback){
//        EspressoIdlingResource.increment()
//        api.getPopularMoviesList(apiKey).enqueue(object : Callback<MovieListResponse> {
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if(response.isSuccessful){
//                    response.body()?.let { callback.onMoviesListReceived(it) }
//                }
//                EspressoIdlingResource.decrement()
//            }
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                Log.e("RemoteDataSource", "Failed to Get Popular Movie List", t)
//                EspressoIdlingResource.decrement()
//            }
//
//        })
//    }

    fun getMovieDetail(movieId: Int): LiveData<ApiResponse<MovieDetailResponse>>{
        EspressoIdlingResource.increment()
        val movieResult = MutableLiveData<ApiResponse<MovieDetailResponse>>()

        api.getDetailMovie(movieId, apiKey).enqueue(object : Callback<MovieDetailResponse>{
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful){
                    movieResult.value = response.body()?.let { ApiResponse.success(it) }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "Failed to Get Movie Detail", t)
                Log.e("RemoteDataSource", t.message.toString())
                EspressoIdlingResource.decrement()
            }

        })
        return movieResult
    }

//    fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback){
//        EspressoIdlingResource.increment()
//        api.getDetailMovie(movieId, apiKey).enqueue(object : Callback<MovieDetailResponse>{
//            override fun onResponse(
//                call: Call<MovieDetailResponse>,
//                response: Response<MovieDetailResponse>
//            ) {
//                if (response.isSuccessful){
//                    response.body()?.let { callback.onMovieDetailReceived(it) }
//                }
//                EspressoIdlingResource.decrement()
//            }
//
//            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
//                Log.e("RemoteDataSource", "Failed to Get Movie Detail", t)
//                EspressoIdlingResource.decrement()
//            }
//
//        })
//    }

    fun getTvShowList(): LiveData<ApiResponse<TvShowListResponse>>{
        EspressoIdlingResource.increment()
        val tvShowListResult = MutableLiveData<ApiResponse<TvShowListResponse>>()

        api.getPopularTvShowsList(apiKey).enqueue(object : Callback<TvShowListResponse> {
            override fun onResponse(
                call: Call<TvShowListResponse>,
                response: Response<TvShowListResponse>
            ) {
                if(response.isSuccessful){
                    tvShowListResult.value = response.body()?.let { ApiResponse.success(it) }
                }
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<TvShowListResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "Failed to Get Popular TvShow List", t)
                Log.e("RemoteDataSource", t.message.toString())
                EspressoIdlingResource.decrement()
            }

        })
        return tvShowListResult
    }

//    fun getPopularTvShowsList(callback: LoadTvShowsListCallback){
//        EspressoIdlingResource.increment()
//        api.getPopularTvShowsList(apiKey).enqueue(object : Callback<TvShowListResponse>{
//            override fun onResponse(
//                call: Call<TvShowListResponse>,
//                response: Response<TvShowListResponse>
//            ) {
//                if(response.isSuccessful){
//                    response.body()?.let { callback.onTvShowsListReceived(it) }
//                }
//                EspressoIdlingResource.decrement()
//            }
//
//            override fun onFailure(call: Call<TvShowListResponse>, t: Throwable) {
//                Log.e("RemoteDataSource", "Failed to Get Popular TvShow List", t)
//                EspressoIdlingResource.decrement()
//            }
//
//        })
//    }


    fun getTvShowDetail(tvShowId: Int): LiveData<ApiResponse<TvShowDetailResponse>>{
        EspressoIdlingResource.increment()
        val tvShowResult = MutableLiveData<ApiResponse<TvShowDetailResponse>>()
        api.getDetailTvShow(tvShowId, apiKey).enqueue(object : Callback<TvShowDetailResponse>{
            override fun onResponse(
                call: Call<TvShowDetailResponse>,
                response: Response<TvShowDetailResponse>
            ) {
                if(response.isSuccessful){
                    tvShowResult.value = response.body()?.let { ApiResponse.success(it) }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "Failed to Get Tv Show Detail", t)
                Log.e("RemoteDataSource", t.message.toString())
                EspressoIdlingResource.decrement()
            }
        })
        return tvShowResult
    }

//    fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback){
//        EspressoIdlingResource.increment()
//        api.getDetailTvShow(tvShowId, apiKey).enqueue(object : Callback<TvShowDetailResponse>{
//            override fun onResponse(
//                call: Call<TvShowDetailResponse>,
//                response: Response<TvShowDetailResponse>
//            ) {
//                if(response.isSuccessful){
//                    response.body()?.let { callback.onTvShowDetailReceived(it) }
//                }
//                EspressoIdlingResource.decrement()
//            }
//
//            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
//                Log.e("RemoteDataSource", "Failed to Get Tv Show Detail", t)
//                EspressoIdlingResource.decrement()
//            }
//
//        })
//    }


//    interface LoadMoviesListCallback{
//        fun onMoviesListReceived(movieResponses: MovieListResponse)
//    }
//    interface LoadMovieDetailCallback{
//        fun onMovieDetailReceived(movieResponse: MovieDetailResponse)
//    }
//    interface LoadTvShowsListCallback{
//        fun onTvShowsListReceived(tvShowResponses: TvShowListResponse)
//    }
//    interface LoadTvShowDetailCallback{
//        fun onTvShowDetailReceived(tvShowResponse: TvShowDetailResponse)
//    }

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(api: ApiService): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(api).apply { instance = this }
            }
    }
}