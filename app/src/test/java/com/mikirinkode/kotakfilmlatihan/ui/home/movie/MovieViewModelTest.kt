package com.mikirinkode.kotakfilmlatihan.ui.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mikirinkode.kotakfilmlatihan.data.model.MovieEntity
import com.mikirinkode.kotakfilmlatihan.data.MovieRepository
import com.mikirinkode.kotakfilmlatihan.ui.home.movie.MovieViewModel
import com.mikirinkode.kotakfilmlatihan.utils.DataDummy
import com.mikirinkode.kotakfilmlatihan.utils.SortUtils
import com.mikirinkode.kotakfilmlatihan.vo.Resource
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock lateinit var movieListObserver: Observer<Resource<PagedList<MovieEntity>>>
    @Mock lateinit var movieDetailObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovies(){
//        val dummyMovies = Resource.success(DataDummy.generateDummyMovies())
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(10)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(movieRepository.getPopularMoviesList(SortUtils.LATEST)).thenReturn(movies)
        val movieEntities = viewModel.getPopularMoviesList(SortUtils.LATEST).value?.data

        verify<MovieRepository>(movieRepository).getPopularMoviesList(SortUtils.LATEST)

        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getPopularMoviesList(SortUtils.LATEST).observeForever(movieListObserver)
        verify(movieListObserver).onChanged(dummyMovies)
    }

    @Test
    fun getMovieDetail(){
        val movie = MutableLiveData<Resource<MovieEntity>>()
        val resource = Resource.success(dummyMovie)
        movie.value = resource
        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(movie)

//        val movieResponse = viewModel.movie.value
//        verify(movieRepository).getMovieDetail(movieId)

//        assertNotNull(movieResponse)
//        assertEquals(movieResponse, dummyMovie)

        viewModel.movie.observeForever(movieDetailObserver)
        verify(movieDetailObserver).onChanged(resource)
    }

    @Test
    fun setFavoriteMovie(){
//        val dummyMovies = DataDummy.generateDummyMovies()
//        val movies = MutableLiveData
//        val movie = MutableLiveData<Resource<MovieEntity>>()
//        val resource = Resource.success(dummyMovie)
//        movie.value = resource
//        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(movie)
        val movieEntity = viewModel.movie.value?.data

        viewModel.setFavoriteMovie()
        movieEntity?.let { verify(movieRepository).setFavoriteMovie(it, true) }
//        movie.value?.data?.let { verify(movieRepository).setFavoriteMovie(it, true) }
    }

//    @Test
//    fun getFavoriteMovieList(){
//        val dummyMovies = pagedList
////        val dummyMovies = DataDummy.generateDummyMovies()
//        `when`(dummyMovies.size).thenReturn(10)
//        val movies = MutableLiveData<PagedList<MovieEntity>>()
//        movies.value = dummyMovies
//
//        `when`(movieRepository.getFavoriteMovies()).thenReturn(movies)
//        val movieEntities = viewModel.getFavoriteMovieList().value
//
//        verify<MovieRepository>(movieRepository).getFavoriteMovies()
//
//        assertNotNull(movieEntities)
//        assertEquals(10, movieEntities?.size)
//
//        viewModel.getFavoriteMovieList().observeForever(movieFavoriteObserver)
//        verify(movieFavoriteObserver).onChanged(dummyMovies)
//    }
}