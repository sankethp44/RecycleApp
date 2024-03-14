package com.example.recycleapp

import android.content.Context
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExchangePointFragment : Fragment() {

    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var searchEditText: EditText
    private lateinit var resultTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exchange_point, container, false)

        searchEditText = view.findViewById(R.id.searchEditText)
        resultTextView = view.findViewById(R.id.resultTextView)
        val getLocationButton: Button = view.findViewById(R.id.getLocationButton)

        getLocationButton.setOnClickListener {
            requestLocation()
        }

        // Initialize location manager and listener
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                // Handle location updates
                displayExchangePoints(location)
            }

            @Deprecated("Deprecated in Java")
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        // Inflate the layout for this fragment
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        val appbarhome: BottomAppBar = requireActivity().findViewById(R.id.bottomAppBar)
        appbarhome.visibility = View.VISIBLE
    }
    override fun onResume() {
        super.onResume()
        showFab()
    }
    private fun requestLocation() {
        // Check for location permission
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request location permission
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Start listening for location updates
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_TIME_BETWEEN_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                locationListener
            )
        }
    }

    private fun displayExchangePoints(location: Location) {
        // Implement logic to display top 5 exchange points based on the user's location
        // You can use the location.latitude and location.longitude to fetch nearby exchange points

        // Dummy example
        val exchangePoints = listOf("Point 1", "Point 2", "Point 3", "Point 4", "Point 5")

        // Display results
        resultTextView.text = exchangePoints.joinToString("\n")
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 123
        private const val MIN_TIME_BETWEEN_UPDATES = 1000L // 1 second
        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES = 1.0f // 1 meter
    }
}