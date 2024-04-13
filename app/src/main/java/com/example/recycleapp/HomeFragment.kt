package com.example.recycleapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {
    private lateinit var pointsTextView: TextView
    private lateinit var userPointsRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pointsTextView = view.findViewById(R.id.pointsTextView)

        // Initialize Firebase database and get reference to user's points
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userPointsRef = firebaseDatabase.getReference("Users").child(userId ?: "").child("points")

        // Set up ValueEventListener to listen for changes in user points
        userPointsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get user points from dataSnapshot and update pointsTextView
                val userPoints = dataSnapshot.getValue(Double::class.java) ?: 0.0
                pointsTextView.text = "Points earned: $userPoints"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                Log.e("HomeFragment", "Failed to read user points.", databaseError.toException())
            }
        })

        // Set up click listeners for image views to open links
        view.findViewById<ImageView>(R.id.image1)?.setOnClickListener {
            openLinkInChrome("https://www.bottlesforchange.in/about-pet")
        }
        view.findViewById<ImageView>(R.id.image2)?.setOnClickListener {
            openLinkInChrome("https://www.rubicon.com/blog/paper-recycling-process/")
        }
        view.findViewById<ImageView>(R.id.image3)?.setOnClickListener {
            openLinkInChrome("https://www.downtoearth.org.in/blog/waste/recycling-of-e-waste-in-india-and-its-potential-64034")
        }
        view.findViewById<ImageView>(R.id.image4)?.setOnClickListener {
            openLinkInChrome("https://greensutra.in/glass-recycling-process/")
        }
        view.findViewById<ImageView>(R.id.image5)?.setOnClickListener {
            openLinkInChrome("https://www.recyclenow.com/how-to-recycle/can-recycling")
        }

        hideFab()
    }

    private fun hideFab() {
        val extendedFabHome: ExtendedFloatingActionButton =
            requireActivity().findViewById(R.id.extendedFabHome)
        extendedFabHome.visibility = View.GONE
    }

    private fun showFab() {
        val extendedFab: ExtendedFloatingActionButton = requireActivity().findViewById(R.id.extendedFab)
        extendedFab.visibility = View.VISIBLE

        val regularFab: FloatingActionButton = requireActivity().findViewById(R.id.fab)
        regularFab.visibility = View.VISIBLE

        val home: BottomNavigationView =
            requireActivity().findViewById(R.id.bottom_navigation)
        home.visibility = View.VISIBLE

        val appbarhome: BottomAppBar =
            requireActivity().findViewById(R.id.bottomAppBar)
        appbarhome.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        showFab()
    }

    private fun openLinkInChrome(link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)
    }
}