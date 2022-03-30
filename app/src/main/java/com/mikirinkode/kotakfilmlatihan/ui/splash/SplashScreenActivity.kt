package com.mikirinkode.kotakfilmlatihan.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mikirinkode.kotakfilmlatihan.databinding.ActivitySplashScreenBinding
import com.mikirinkode.kotakfilmlatihan.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvAppName.alpha = 0f
            tvAppName.animate().setDuration(1500).alpha(1f)
            ivAppIcon.alpha = 0f
            ivAppIcon.animate().setDuration(1500).alpha(1f).withEndAction {
                val moveToMainActivity = Intent(this@SplashScreenActivity, HomeActivity::class.java)
                startActivity(moveToMainActivity)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }
}