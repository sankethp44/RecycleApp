package com.example.recycleapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleapp.databinding.SplashScreenBinding
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {
    private lateinit var binding: SplashScreenBinding
    private lateinit var firebaseAuth: FirebaseAuth
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

        firebaseAuth = FirebaseAuth.getInstance()

        // Check if user is logged in or not
        Handler().postDelayed({
            if (firebaseAuth.currentUser != null) {
                // User is logged in, open MainActivity
                val intent = Intent(this@SplashscreenActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                // User is not logged in, open SignInActivity
                val intent = Intent(this@SplashscreenActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 3000)
    }
}
