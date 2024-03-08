package com.example.recycleapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleapp.databinding.SplashScreenBinding

class SplashscreenActivity : AppCompatActivity() {
    private lateinit var binding: SplashScreenBinding
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
