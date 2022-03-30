package com.mikirinkode.kotakfilmlatihan.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mikirinkode.kotakfilmlatihan.data.model.MovieEntity
import com.mikirinkode.kotakfilmlatihan.data.MovieRepository
import com.mikirinkode.kotakfilmlatihan.ui.home.movie.MovieViewModel
import com.mikirinkode.kotakfilmlatihan.utils.DataDummy
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
class FavoriteMovieViewModelTest {
    private lateinit var viewModel: FavoriteMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

//    @Mock lateinit var movieListObserver: Observer<Resource<PagedList<MovieEntity>>>
    @Mock lateinit var movieObserver: Observer<MovieEntity>
    @Mock lateinit var movieFavoriteObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp(){
        viewModel = FavoriteMovieViewModel(movieRepository)
    }


    @Test
    fun getFavoriteMovieList(){
        val dummyMovies = pagedList
//        val dummyMovies = DataDummy.generateDummyMovies()
        `when`(dummyMovies.size).thenReturn(10)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(movieRepository.getFavoriteMovies()).thenReturn(movies)
        val movieEntities = viewModel.getFavoriteMovieList().value

        verify<MovieRepository>(movieRepository).getFavoriteMovies()

        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getFavoriteMovieList().observeForever(movieFavoriteObserver)
        verify(movieFavoriteObserver).onChanged(dummyMovies)
    }

    @Test
    fun setFavoriteMovie(){
//        val dummyMovie = dummyMovies[0]
//        dummyMovie.isFavorite = true

//        `when`(movieRepository.setFavoriteMovie(dummyMovie, true)).thenReturn(dummyMovie)

//        assertEquals(false, dummyMovie.isFavorite)
        viewModel.setFavorite(dummyMovie)
        verify(movieRepository).setFavoriteMovie(dummyMovie, true)
        verifyNoMoreInteractions(movieObserver)
    }
}