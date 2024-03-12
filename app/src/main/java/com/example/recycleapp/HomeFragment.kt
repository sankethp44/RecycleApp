package com.example.recycleapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView1: ImageView? = view.findViewById(R.id.image1)
        imageView1?.setOnClickListener {
            openLinkInChrome("https://www.bottlesforchange.in/about-pet")
        }
        val imageView2: ImageView? = view.findViewById(R.id.image2)
        imageView2?.setOnClickListener {
            openLinkInChrome("https://www.rubicon.com/blog/paper-recycling-process/")
        }
        val imageView3: ImageView? = view.findViewById(R.id.image3)
        imageView3?.setOnClickListener {
            openLinkInChrome("https://www.downtoearth.org.in/blog/waste/recycling-of-e-waste-in-india-and-its-potential-64034")
        }
        val imageView4: ImageView? = view.findViewById(R.id.image4)
        imageView4?.setOnClickListener {
            openLinkInChrome("https://greensutra.in/glass-recycling-process/")
        }
        val imageView5: ImageView? = view.findViewById(R.id.image5)
        imageView5?.setOnClickListener {
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