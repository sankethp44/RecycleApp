package com.example.recycleapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleapp.databinding.SplashScreenBinding
import java.util.logging.Handler

class splash_screen_activity : AppCompatActivity() {
    private var _bg__splash_screen_ek2: View? = null
    private var vector_9: ImageView? = null
    private var rectangle_2: View? = null
    private var leaf: ImageView? = null
    private var vector: ImageView? = null
    private var welcome: TextView? = null
    private var vector_ek1: ImageView? = null
    private var vector_ek2: ImageView? = null
    private var time: TextView? = null
    private var recycle_your_waste_materials: TextView? = null
    private var _bg__frame_1_ek1: View? = null
    private var get_started: TextView? = null
    private var can: ImageView? = null
    private var vector_1: ImageView? = null
    private var vector_2: ImageView? = null
    private var vector_3: ImageView? = null
    private val rectangle_1: View? = null
    private var leaf1: ImageView? = null
    private var news: ImageView? = null
    private var dustbin: ImageView? = null
    private lateinit var binding: SplashScreenBinding
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Now, you can directly use the views through the binding object
        val bgSplashScreenEk2: View = findViewById(R.id._bg__splash_screen_ek2)
        binding.vector9 // Use this for vector_9
        binding.rectangle2 // Use this for rectangle_2
        binding.leaf // Use this for leaf
        binding.welcome // Use this for welcome
        binding.recycleYourWasteMaterials // Use this for recycle_your_waste_materials
        binding.can // Use this for can
        binding.vector1 // Use this for vector_1
        binding.vector2 // Use this for vector_2
        binding.vector3 // Use this for vector_3
        binding.leaf1 // Use this for leaf1
        binding.news // Use this for news
        binding.dustbin // Use this for dustbin

        // Load animation
        val slideUp: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        // Set up the layout with the animation
        bgSplashScreenEk2.startAnimation(slideUp)
        val SPLASH_DURATION: Long = 4000
        android.os.Handler().postDelayed({
            val mainIntent = Intent(this@splash_screen_activity, LoginActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DURATION)
    }
}
