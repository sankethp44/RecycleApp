package com.example.recycleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity : AppCompatActivity() {

    private lateinit var _bgHomepageEk2: View
    private lateinit var menu: ImageView
    private lateinit var search: ImageView
    private lateinit var line1: ImageView
    private lateinit var peopleRecyclingAtHomeConceptFreeVector1: ImageView
    private lateinit var earnPointsByDiscardingTrash: TextView
    private lateinit var categories: TextView
    private lateinit var home: ImageView
    private lateinit var location: ImageView
    private lateinit var scanner: ImageView
    private lateinit var person: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _bgHomepageEk2 = findViewById(R.id._bg__homepage_ek2)
        menu = findViewById(R.id.menu)
        search = findViewById(R.id.search)
        line1 = findViewById(R.id.line_1)
        peopleRecyclingAtHomeConceptFreeVector1 = findViewById(R.id.people_recycling_at_home_concept_free_vector_1)
        earnPointsByDiscardingTrash = findViewById(R.id.earn_points_by_discarding_trash)
        categories = findViewById(R.id.categories)
        home = findViewById(R.id.home)
        location = findViewById(R.id.location)
        scanner = findViewById(R.id.scanner)
        person = findViewById(R.id.person)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val drawerView = findViewById<LinearLayout>(R.id.drawer)

        fun openDrawer(view: View) {
            drawerLayout.openDrawer(drawerView)
        }

    }

}