package com.example.recycleapp

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleapp.databinding.ActivitySignupBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var passwordInputLayout2: TextInputLayout
    private lateinit var passwordEditText2: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        passwordInputLayout = findViewById(R.id.passwordTextInputLayout)
        passwordEditText = findViewById(R.id.signup_password)

        passwordInputLayout2 = findViewById(R.id.passwordTextInputLayout2)
        passwordEditText2 = findViewById(R.id.signup_confirm)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupButton.setOnClickListener {
            val email = binding.signupEmail.text.toString()
            val password = binding.signupPassword.text.toString()
            val confirmPassword = binding.signupConfirm.text.toString()
            val name = binding.signupName.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && name.isNotEmpty()) {
                if (password == confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = firebaseAuth.currentUser

                                val profileUpdates = UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build()

                                user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                                    if (updateTask.isSuccessful) {
                                        // Send email verification
                                        sendEmailVerification()
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Failed to update user profile: ${updateTask.exception?.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    this,
                                    "Registration failed: ${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginRedirectText.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
        passwordInputLayout.setEndIconOnClickListener {
            togglePasswordVisibility()
        }
        passwordInputLayout2.setEndIconOnClickListener {
            togglePasswordVisibility2()
        }
    }

    private fun sendEmailVerification() {
        val user = firebaseAuth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this,
                    "Please check your email for verification.",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Email verification failed: ${task.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun togglePasswordVisibility() {
        if (passwordEditText.transformationMethod == PasswordTransformationMethod.getInstance()) {
            passwordEditText.transformationMethod = null
        } else {
            passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }
    private fun togglePasswordVisibility2() {
        if (passwordEditText2.transformationMethod == PasswordTransformationMethod.getInstance()) {
            passwordEditText2.transformationMethod = null
        } else {
            passwordEditText2.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }
}