package com.example.recycleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChangePasswordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideFab()
    }
    private fun hideFab() {
        val extendedFab: ExtendedFloatingActionButton =
            requireActivity().findViewById(R.id.extendedFab)
        extendedFab.visibility = View.GONE

        val regularFab: FloatingActionButton =
            requireActivity().findViewById(R.id.fab)
        regularFab.visibility = View.GONE

        val home: BottomNavigationView =
            requireActivity().findViewById(R.id.bottom_navigation)
        home.visibility = View.GONE

        val appbarhome: BottomAppBar =
            requireActivity().findViewById(R.id.bottomAppBar)
        appbarhome.visibility = View.GONE
    }

    private fun showFab() {
        val extendedFabHome: ExtendedFloatingActionButton = requireActivity().findViewById(R.id.extendedFabHome)
        extendedFabHome.visibility = View.VISIBLE
    }
    override fun onResume() {
        super.onResume()
        showFab()
    }
}