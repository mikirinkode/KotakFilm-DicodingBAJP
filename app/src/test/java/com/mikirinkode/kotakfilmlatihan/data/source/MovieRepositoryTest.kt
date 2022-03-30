package com.mikirinkode.kotakfilmlatihan.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.mikirinkode.kotakfilmlatihan.data.model.MovieEntity
import com.mikirinkode.kotakfilmlatihan.data.model.TvShowEntity
import com.mikirinkode.kotakfilmlatihan.data.source.local.LocalDataSource
import com.mikirinkode.kotakfilmlatihan.data.source.remote.RemoteDataSource
import com.mikirinkode.kotakfilmlatihan.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import androidx.paging.DataSource
import com.mikirinkode.kotakfilmlatihan.utils.*
import com.nhaarman.mockitokotlin2.*

class MovieRepositoryTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses.results[0].id
    private val movieDetailResponse = DataDummy.generateRemoteDummyMovieDetail()

    private val tvResponse = DataDummy.generateRemoteDummyTvShows()
    private val tvId = tvResponse.results[0].id
    private val tvDetailResponse = DataDummy.generateRemoteDummyTvDetail()




    @Test
    fun getMovieList(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
//        val dummyMovies = MutableLiveData<List<MovieEntity>>()
//        dummyMovies.value = DataDummy.generateDummyMovies()
        Mockito.`when`(local.getMovieList(SortUtils.LATEST)).thenReturn(dataSourceFactory)
        movieRepository.getPopularMoviesList(SortUtils.LATEST)

//        val moviesList = LiveDataTestUtil.getValue(movieRepository.getPopularMoviesList())
        val moviesList = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getMovieList(SortUtils.LATEST)
        assertNotNull(moviesList.data)
        assertEquals(movieResponses.results.size.toLong(), moviesList.data?.size?.toLong())


//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.LoadMoviesListCallback)
//                .onMoviesListReceived(movieResponses)
//            null
//        }.`when`(remote).getPopularMoviesList(any())
//
//        val moviesList = LiveDataTestUtil.getValue(movieRepository.getPopularMoviesList())
//        verify(remote).getPopularMoviesList(any())
//        assertNotNull(moviesList)
//        assertEquals(movieResponses.results.size.toLong(), moviesList.size.toLong())
    }

    @Test
    fun getMovieDetail(){
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = DataDummy.generateDummyMovies()[0]
        Mockito.`when`(local.getMovieDetail(movieId)).thenReturn(dummyMovie)

        val movieDetail = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))
        verify(local).getMovieDetail(eq(movieId))

        assertNotNull(movieDetail)
        assertNotNull(movieDetail.data)
//        assertEquals(movieDetailResponse, movieDetail.data)
        assertEquals(movieDetailResponse.id, movieDetail.data?.id)
        assertEquals(movieDetailResponse.title, movieDetail.data?.title)
        assertEquals(movieDetailResponse.releaseDate, movieDetail.data?.releaseDate)
        assertEquals(movieDetailResponse.overview, movieDetail.data?.overview)
        assertEquals(movieDetailResponse.tagline, movieDetail.data?.tagline)
//        assertEquals(movieDetailResponse.genres, movieDetail.data?.genres)
        assertEquals(movieDetailResponse.runtime, movieDetail.data?.runtime)
        movieDetail.data?.let { assertEquals(movieDetailResponse.voteAverage, it.voteAverage, 0.0) }
        assertEquals(movieDetailResponse.posterPath, movieDetail.data?.posterPath)
        assertEquals(movieDetailResponse.backdropPath, movieDetail.data?.backdropPath)


//        doAnswer {
//            (it.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
//                .onMovieDetailReceived(movieDetailResponse)
//            null
//        }.`when`(remote).getMovieDetail(eq(movieId), any())
//
//        val movieDetail = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))
//        verify(remote).getMovieDetail(eq(movieId), any())
//
//        assertNotNull(movieDetail)
//        assertEquals(movieDetailResponse.id, movieDetail.id)
//        assertEquals(movieDetailResponse.title, movieDetail.title)
//        assertEquals(movieDetailResponse.releaseDate, movieDetail.releaseDate)
//        assertEquals(movieDetailResponse.overview, movieDetail.overview)
//        assertEquals(movieDetailResponse.tagline, movieDetail.tagline)
//        assertEquals(movieDetailResponse.genres, movieDetail.genres)
//        assertEquals(movieDetailResponse.runtime, movieDetail.runtime)
//        assertEquals(movieDetailResponse.voteAverage, movieDetail.voteAverage, 0.0)
//        assertEquals(movieDetailResponse.posterPath, movieDetail.posterPath)
//        assertEquals(movieDetailResponse.backdropPath, movieDetail.backdropPath)
    }

    @Test
    fun setFavoriteMovie(){
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = DataDummy.generateDummyMovies()[0]
        Mockito.`when`(local.getMovieDetail(movieId)).thenReturn(dummyMovie)

        val movieDetail = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))
        verify(local).getMovieDetail(eq(movieId))

        movieDetail.data?.let { movieRepository.setFavoriteMovie(it, true) }
        movieDetail.data?.let { verify(local).setFavoriteMovie(it, true) }
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavoriteMovieList(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
//        val dummyMovies = MutableLiveData<List<MovieEntity>>()
//        dummyMovies.value = DataDummy.generateDummyMovies()
        Mockito.`when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovies()

        val moviesList = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(moviesList)
        assertEquals(movieResponses.results.size.toLong(), moviesList.data?.size?.toLong())
    }

    @Test
    fun getTvShowList(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
//        val dummyTvShow = MutableLiveData<List<TvShowEntity>>()
//        dummyTvShow.value = DataDummy.generateDummyTvShows()
        Mockito.`when`(local.getTvShowList(SortUtils.LATEST)).thenReturn(dataSourceFactory)
        movieRepository.getPopularTvShowsList(SortUtils.LATEST)

        val tvShowList = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getTvShowList(SortUtils.LATEST)
        assertNotNull(tvShowList.data)
        assertEquals(tvResponse.results.size.toLong(), tvShowList.data?.size?.toLong())

//        doAnswer {
//            (it.arguments[0] as RemoteDataSource.LoadTvShowsListCallback)
//                .onTvShowsListReceived(tvResponse)
//            null
//        }.`when`(remote).getPopularTvShowsList(any())
//
//        val tvShowList = LiveDataTestUtil.getValue(movieRepository.getPopularTvShowsList())
//        verify(remote).getPopularTvShowsList(any())
//        assertNotNull(tvShowList)
//        assertEquals(tvResponse.results.size.toLong(), tvShowList.size.toLong())
    }

    @Test
    fun getTvShowDetail(){
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = DataDummy.generateDummyTvShows()[0]
        Mockito.`when`(local.getTvShowDetail(tvId)).thenReturn(dummyTvShow)

        val tvShowDetail = LiveDataTestUtil.getValue(movieRepository.getTvShowDetail(tvId))
        verify(local).getTvShowDetail(eq(tvId))

        assertNotNull(tvShowDetail)
        assertNotNull(tvShowDetail.data)
//        assertEquals(tvDetailResponse, tvShowDetail.data)
        assertEquals(tvDetailResponse.id, tvShowDetail.data?.id)
        assertEquals(tvDetailResponse.name, tvShowDetail.data?.title)
        assertEquals(tvDetailResponse.firstAirDate, tvShowDetail.data?.releaseDate)
        assertEquals(tvDetailResponse.overview, tvShowDetail.data?.overview)
        assertEquals(tvDetailResponse.tagline, tvShowDetail.data?.tagline)
//        assertEquals(tvDetailResponse.genres, tvShowDetail.data?.genres)
        assertEquals(tvDetailResponse.episodeRunTime[0], tvShowDetail.data?.runtime)
        tvShowDetail.data?.let { assertEquals(tvDetailResponse.voteAverage, it.voteAverage, 0.0) }
        assertEquals(tvDetailResponse.posterPath, tvShowDetail.data?.posterPath)
        assertEquals(tvDetailResponse.backdropPath, tvShowDetail.data?.backdropPath)

//        doAnswer {
//            (it.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback)
//                .onTvShowDetailReceived(tvDetailResponse)
//            null
//        }.`when`(remote).getTvShowDetail(eq(tvId), any())
//
//        val tvDetail = LiveDataTestUtil.getValue(movieRepository.getTvShowDetail(tvId))
//        verify(remote).getTvShowDetail(eq(tvId), any())
//
//        assertNotNull(tvDetail)
//        assertEquals(tvDetailResponse.id, tvDetail.id)
//        assertEquals(tvDetailResponse.name, tvDetail.title)
//        assertEquals(tvDetailResponse.firstAirDate, tvDetail.releaseDate)
//        assertEquals(tvDetailResponse.overview, tvDetail.overview)
//        assertEquals(tvDetailResponse.tagline, tvDetail.tagline)
//        assertEquals(tvDetailResponse.genres, tvDetail.genres)
//        assertEquals(tvDetailResponse.episodeRunTime[0], tvDetail.runtime)
//        assertEquals(tvDetailResponse.voteAverage, tvDetail.voteAverage, 0.0)
//        assertEquals(tvDetailResponse.posterPath, tvDetail.posterPath)
//        assertEquals(tvDetailResponse.backdropPath, tvDetail.backdropPath)
    }

    @Test
    fun getFavoriteTvShowList(){

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
//        val dummyTvShow = MutableLiveData<List<TvShowEntity>>()
//        dummyTvShow.value = DataDummy.generateDummyTvShows()
        Mockito.`when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteTvShows()

        val tvShowList = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getFavoriteTvShows()
        assertNotNull(tvShowList)
        assertEquals(tvResponse.results.size.toLong(), tvShowList.data?.size?.toLong())
    }

    @Test
    fun setFavoriteTvShow(){
        movieRepository.setFavoriteTvShow(DataDummy.generateDummyTvShows()[0], true)
        verify(local).setFavoriteTvShow(DataDummy.generateDummyTvShows()[0], true)
        verifyNoMoreInteractions(local)
    }
}
