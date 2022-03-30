package com.mikirinkode.kotakfilmlatihan.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mikirinkode.kotakfilmlatihan.R
import com.mikirinkode.kotakfilmlatihan.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val navView: BottomNavigationView = navBottom
            val navController = findNavController(R.id.nav_host_fragment_activity_home)
            val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.nav_movie, R.id.nav_tv_show, R.id.nav_favorite
            ).build()
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
    }
}


