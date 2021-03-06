package com.mikirinkode.kotakfilmlatihan.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mikirinkode.kotakfilmlatihan.R
import com.mikirinkode.kotakfilmlatihan.databinding.ActivityDetailCatalogueBinding
import com.mikirinkode.kotakfilmlatihan.ui.home.movie.MovieViewModel
import com.mikirinkode.kotakfilmlatihan.ui.home.tvshow.TvShowViewModel
import com.mikirinkode.kotakfilmlatihan.utils.Constants.Companion.IMAGE_BASE_URL
import com.mikirinkode.kotakfilmlatihan.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCatalogueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCatalogueBinding
    private lateinit var movieTitle: String
    private val movieViewModel: MovieViewModel by viewModels()
    private val tvShowViewModel: TvShowViewModel by viewModels()
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCatalogueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extra = intent.extras
        val id = extra?.getInt(EXTRA_ID)
        val type = extra?.getString(EXTRA_TYPE).toString()
        id?.let { findDetail(it, type) }

        with(binding) {
            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnShare.setOnClickListener {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Watch $movieTitle on KotakFilm")
                shareIntent.type = "text/plain"
                startActivity(Intent.createChooser(shareIntent, "Share To:"))
            }

            btnTryAgain.setOnClickListener {
                if (id != null) {
                    findDetail(id, type)
                }
            }

            toggleFavorite.setOnClickListener {
                isFavorite = !isFavorite
                toggleFavorite.isChecked = isFavorite
                if (isFavorite) Toast.makeText(this@DetailCatalogueActivity, "Added to Favorite", Toast.LENGTH_SHORT).show()
                else Toast.makeText(this@DetailCatalogueActivity, "Removed from Favorite", Toast.LENGTH_SHORT).show()

                if (type == "MOVIE"){
                    movieViewModel.setFavoriteMovie()
                } else if (type == "TV SHOW"){
                    tvShowViewModel.setFavoriteTvShow()
                }
            }
        }
    }

    private fun findDetail(id: Int, type: String) {
        binding.apply {
            icLoading.visibility = View.VISIBLE
            onFailMsg.visibility = View.GONE
            tvLabelRelease.visibility = View.GONE
        }
        if (type == "MOVIE") getDetailMovie(id, type) else getDetailTvShow(id, type)
    }

    private fun getDetailMovie(id: Int, type: String) {
        binding.apply {
            movieViewModel.setSelectedMovie(id)
            movieViewModel.movie.observe(this@DetailCatalogueActivity, { movie ->
                if (movie != null){
                    when (movie.status){
                        Status.LOADING -> {
                            icLoading.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            icLoading.visibility = View.GONE
                            movie.data?.let {
                                isFavorite = it.isFavorite
                                toggleFavorite.isChecked = isFavorite
                                movieTitle = it.title
                                setData(
                                    it.title,
                                    it.overview,
                                    it.genres,
                                    it.releaseDate,
                                    it.tagline,
                                    it.voteAverage,
                                    it.runtime,
                                    it.posterPath.toString(),
                                    it.backdropPath.toString(),
                                    type
                                )
                            }
                        }
                        Status.ERROR -> {
                            icLoading.visibility = View.GONE
                            onFailMsg.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }


    private fun getDetailTvShow(id: Int, type: String) {
        binding.apply {
            tvShowViewModel.setSelectedTvShow(id)
            tvShowViewModel.tvShow.observe(this@DetailCatalogueActivity, { tvShow ->
                if (tvShow != null){
                    when (tvShow.status){
                        Status.LOADING -> {
                            icLoading.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            icLoading.visibility = View.GONE
                            tvShow.data?.let {
                                isFavorite = it.isFavorite
                                toggleFavorite.isChecked = isFavorite
                                movieTitle = it.title
                                setData(
                                    it.title,
                                    it.overview,
                                    it.genres,
                                    it.releaseDate,
                                    it.tagline,
                                    it.voteAverage,
                                    it.runtime,
                                    it.posterPath.toString(),
                                    it.backdropPath.toString(),
                                    type
                                )
                            }
                        }
                        Status.ERROR -> {
                            icLoading.visibility = View.GONE
                            onFailMsg.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }

    private fun setData(
        title: String,
        overview: String?,
        genres: String?,
        releaseDate: String,
        tagline: String?,
        voteAverage: Double,
        runtime: Int?,
        posterPath: String?,
        backdropPath: String?,
        category: String
    ) {
        binding.apply {
            tvDetailTitle.text = title
            if (overview == "" || overview == null) getString(R.string.no_data) else tvDetailDescription.text = overview
            tvDetailRelease.text = releaseDate ?: "-No Data-"
            tvDetailQuote.text = tagline ?: ""
            tvDetailRating.text = voteAverage.toString()
            tvDetailCategory.text = getString(R.string.category, category)
            if (runtime != null){
                if (category == "MOVIE"){
                    val hours = runtime.div(60)
                    val minutes = runtime.rem(60)
                    tvDetailDuration.text = getString(R.string.runtime, hours, minutes)
                } else {
                    tvDetailDuration.text = getString(R.string.episodeRuntime, runtime)
                }
            } else {
                tvDetailDuration.text = getString(R.string.no_data)
            }

            tvLabelRelease.visibility = View.VISIBLE

            tvDetailGenre.text = genres ?: getString(R.string.no_data)

            ivDetailPoster.loadImage("${IMAGE_BASE_URL}${posterPath}")
            ivDetailPosterBg.loadImage("${IMAGE_BASE_URL}${backdropPath}")
        }
    }

    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh))
            .error(R.drawable.ic_error)
            .into(this)
    }


    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }
}