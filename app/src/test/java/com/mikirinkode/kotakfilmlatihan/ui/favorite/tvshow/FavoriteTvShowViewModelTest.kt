package com.mikirinkode.kotakfilmlatihan.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mikirinkode.kotakfilmlatihan.data.model.TvShowEntity
import com.mikirinkode.kotakfilmlatihan.data.MovieRepository
import com.mikirinkode.kotakfilmlatihan.data.model.MovieEntity
import com.mikirinkode.kotakfilmlatihan.ui.home.movie.MovieViewModel
import com.mikirinkode.kotakfilmlatihan.ui.home.tvshow.TvShowViewModel
import com.mikirinkode.kotakfilmlatihan.utils.DataDummy
import com.mikirinkode.kotakfilmlatihan.utils.SortUtils
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import com.mikirinkode.kotakfilmlatihan.vo.Resource
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest{

    private lateinit var viewModel: FavoriteTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var tvShowObserver: Observer<TvShowEntity>
    @Mock private lateinit var tvShowFavoriteObserver: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp(){
        viewModel = FavoriteTvShowViewModel(movieRepository)
    }


    @Test
    fun getFavoriteTvShowList(){
//        val dummyTvShow = DataDummy.generateDummyTvShows()
        val dummyTvShows = pagedList
        `when`(dummyTvShows.size).thenReturn(10)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(movieRepository.getFavoriteTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getFavoriteTvShowList().value

        verify<MovieRepository>(movieRepository).getFavoriteTvShows()

        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.size)

        viewModel.getFavoriteTvShowList().observeForever(tvShowFavoriteObserver)
        verify(tvShowFavoriteObserver).onChanged(dummyTvShows)
    }

    @Test
    fun setFavoriteTvShow(){
//        assertEquals(false, dummyTvShow.isFavorite)
//        val resource = dummyTvShow

        viewModel.setFavorite(dummyTvShow)
        verify(movieRepository).setFavoriteTvShow(dummyTvShow, true)
        verifyNoMoreInteractions(tvShowObserver)
    }
}
