package com.example.recycleapp

import HowFragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.recycleapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    private lateinit var pointsTextView: TextView
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val shareUrl = "https://raw.githubusercontent.com/sankethp44/Recycle-AppApk/main/app-debug.apk"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        firebaseDatabase = FirebaseDatabase.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        var userPointsRef =
            firebaseDatabase.getReference("Users").child(userId ?: "").child("points")

        pointsTextView = findViewById(R.id.pointsTextView)

        // Set up ValueEventListener to listen for changes in user points
        userPointsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get user points from dataSnapshot and update pointsTextView
                val userPoints = dataSnapshot.getValue(Double::class.java) ?: 0.0
                pointsTextView.text = "Points earned: ${String.format("%.2f", userPoints)}"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                Log.e("MainActivity", "Failed to read user points.", databaseError.toException())
            }
        })

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> openFragment(HomeFragment())
                R.id.bottom_location -> openFragment(ExchangePointFragment())
                R.id.bottom_cart -> openFragment((ShopFragment()))
                R.id.bottom_proifle -> {
                    openFragmentWithAnimation()
                }
            }
            true
        }

        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

        binding.fab.setOnClickListener {
            val intent = Intent(this, Scanner123Fragment::class.java)
            startActivity(intent)
        }

        binding.extendedFab.setOnClickListener {
            val howFragment = HowFragment()

            // Set custom animations for the fragment transaction
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter_from_bottom,
                R.anim.exit_to_bottom,
                R.anim.enter_from_bottom,
                R.anim.exit_to_bottom
            )

            transaction.replace(R.id.fragment_container, howFragment)
            transaction.addToBackStack(null)

            transaction.commit()
        }
        binding.extendedFabHome.setOnClickListener {
            val homeFragment = HomeFragment()

            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter_from_bottom,
                R.anim.exit_to_bottom,
                R.anim.enter_from_bottom,
                R.anim.exit_to_bottom
            )
            transaction.replace(R.id.fragment_container, homeFragment)
            transaction.addToBackStack(null)

            transaction.commit()
        }
    }

    private fun openFragmentWithAnimation() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_right_enter,
            R.anim.slide_left_exit,
            R.anim.slide_left_enter,
            R.anim.slide_right_exit
        )
        transaction.replace(R.id.fragment_container, MyProfileFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.nav_profile ->openFragment(MyProfileFragment())
            R.id.nav_newaccount ->{
                startActivity(Intent(this, SignupActivity::class.java))

                finish()
            }
            R.id.nav_homepage ->openFragment(HomeFragment())
            R.id.nav_scanner -> {
                val intent = Intent(this, Scanner123Fragment::class.java)
                startActivity(intent)
            }            R.id.nav_locationpage ->openFragment(ExchangePointFragment())
            R.id.nav_feedback ->openFragment(FeedbackFragment())
            R.id.nav_logout -> {
                firebaseAuth = FirebaseAuth.getInstance()
                firebaseAuth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            R.id.nav_share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share App")
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    getString(R.string.share_app_text) +
                            shareUrl
                )
                startActivity(Intent.createChooser(intent, "Share App"))
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed(){
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }  else{
            val currentFragment = fragmentManager.findFragmentById(R.id.fragment_container)
            if (currentFragment !is HomeFragment) {
                openFragment(HomeFragment())
            } else {
                super.onBackPressed()
            }
        }
    }
    private fun openFragment(fragment:Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_share -> {
                shareContent()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareContent() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share App")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.share_app_text) + shareUrl
        )
        startActivity(Intent.createChooser(intent, "Share via"))
    }


}

