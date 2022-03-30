package com.mikirinkode.kotakfilmlatihan.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mikirinkode.kotakfilmlatihan.data.model.MovieEntity
import com.mikirinkode.kotakfilmlatihan.data.MovieRepository
import com.mikirinkode.kotakfilmlatihan.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int){
        this.movieId.value = movieId
    }


    var movie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { mMovieId ->
        movieRepository.getMovieDetail(mMovieId)
    }

    fun getPopularMoviesList(sort: String): LiveData<Resource<PagedList<MovieEntity>>>{
        return movieRepository.getPopularMoviesList(sort)
    }

//    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> {
//        return movieRepository.getMovieDetail(movieId)
//    }



    fun setFavoriteMovie(){
        val movieValue = movie.value
        if (movieValue != null){
            if (movieValue.data != null){
                val newState = !movieValue.data.isFavorite
                movieRepository.setFavoriteMovie(movieValue.data, newState)
            }
        }
    }


//    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean){
//        movieRepository.setFavoriteMovie(movie, newState)
//    }

//    fun getMovieSize(): Int{
//        if (movieList.value != null){
//            val movieEntities = movieList.value?.data
//            if (movieEntities != null){
//                return movieEntities.size
//            }
//        }
//        return 0
//    }

//    fun setNextPage(){
//        if (movieList != null) {
//            val position = movieList.value.data.
//        }
//    }
}