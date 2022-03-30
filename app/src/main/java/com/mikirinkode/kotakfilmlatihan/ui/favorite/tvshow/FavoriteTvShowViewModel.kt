package com.mikirinkode.kotakfilmlatihan.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mikirinkode.kotakfilmlatihan.data.MovieRepository
import com.mikirinkode.kotakfilmlatihan.data.model.TvShowEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteTvShowViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {


    fun getFavoriteTvShowList(): LiveData<PagedList<TvShowEntity>> {
        return movieRepository.getFavoriteTvShows()
    }

    fun setFavorite(tvShow: TvShowEntity){
        val newState = !tvShow.isFavorite
        movieRepository.setFavoriteTvShow(tvShow, newState)
    }
}