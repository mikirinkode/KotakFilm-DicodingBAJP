package com.mikirinkode.kotakfilmlatihan.ui.home.tvshow

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
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest{

    private lateinit var viewModel: TvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var tvShowListObserver: Observer<Resource<PagedList<TvShowEntity>>>
    @Mock private lateinit var tvShowDetailObserver: Observer<Resource<TvShowEntity>>
//    @Mock private lateinit var tvShowFavoriteObserver: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(movieRepository)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getTvShows(){
//        val dummyTvShows = Resource.success(DataDummy.generateDummyTvShows())
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(10)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(movieRepository.getPopularTvShowsList(SortUtils.LATEST)).thenReturn(tvShows)
        val tvEntities = viewModel.getPopularTvShowsList(SortUtils.LATEST).value?.data

        verify(movieRepository).getPopularTvShowsList(SortUtils.LATEST)

        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        viewModel.getPopularTvShowsList(SortUtils.LATEST).observeForever(tvShowListObserver)
        verify(tvShowListObserver).onChanged(dummyTvShows)
    }

    @Test
    fun getTvShowDetail(){
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        val resource = Resource.success(dummyTvShow)
        tvShow.value = resource

        `when`(movieRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)

//        val tvShowResponse = viewModel.getTvShowDetail(tvShowId).value
//        verify(movieRepository).getTvShowDetail(tvShowId)
//
//        assertNotNull(tvShowResponse)
//        assertEquals(tvShowResponse, dummyTvShow)

        viewModel.tvShow.observeForever(tvShowDetailObserver)
        verify(tvShowDetailObserver).onChanged(resource)
    }

//    @Test
//    fun getFavoriteTvShowList(){
////        val dummyTvShow = DataDummy.generateDummyTvShows()
//        val dummyTvShows = pagedList
//        `when`(dummyTvShows.size).thenReturn(10)
//        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
//        tvShows.value = dummyTvShows
//
//        `when`(movieRepository.getFavoriteTvShows()).thenReturn(tvShows)
//        val tvShowEntities = viewModel.getFavoriteTvShowList().value
//
//        verify<MovieRepository>(movieRepository).getFavoriteTvShows()
//
//        assertNotNull(tvShowEntities)
//        assertEquals(10, tvShowEntities?.size)
//
//        viewModel.getFavoriteTvShowList().observeForever(tvShowFavoriteObserver)
//        verify(tvShowFavoriteObserver).onChanged(dummyTvShows)
//    }
    @Test
    fun setFavoriteTvShow(){
//        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
//        val resource = Resource.success(dummyTvShow)
//        tvShow.value = resource
//
//        `when`(movieRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)

    //        val tvShowResponse = viewModel.getTvShowDetail(tvShowId).value
    //        verify(movieRepository).getTvShowDetail(tvShowId)
    //
    //        assertNotNull(tvShowResponse)
    //        assertEquals(tvShowResponse, dummyTvShow)

        val tvShow = viewModel.tvShow.value?.data

        viewModel.setFavoriteTvShow()
    tvShow?.let { verify(movieRepository).setFavoriteTvShow(it, true) }
    }
}
