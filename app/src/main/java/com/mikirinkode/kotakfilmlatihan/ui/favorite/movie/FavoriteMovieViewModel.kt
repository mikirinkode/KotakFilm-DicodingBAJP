package com.mikirinkode.kotakfilmlatihan.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mikirinkode.kotakfilmlatihan.data.MovieRepository
import com.mikirinkode.kotakfilmlatihan.data.model.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    fun getFavoriteMovieList(): LiveData<PagedList<MovieEntity>> {
        return movieRepository.getFavoriteMovies()
    }

    fun setFavorite(movie: MovieEntity){
        val newState = !movie.isFavorite
        movieRepository.setFavoriteMovie(movie, newState)
    }

}