package com.example.recycleapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleapp.databinding.SplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {
    private lateinit var binding: SplashScreenBinding
    @Suppress("DEPRECATION")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.leaf
        binding.welcome
        binding.recycleYourWasteMaterials
        binding.can
        binding.vector1
        binding.vector2
        binding.vector3
        binding.leaf1
        binding.news
        binding.dustbin

        val splashDuration: Long = 3000
        android.os.Handler().postDelayed({
            val mainIntent = Intent(this@SplashscreenActivity, LoginActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, splashDuration)
    }
}
