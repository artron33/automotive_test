package com.rust.nmea1A

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.rust.nmea1A.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val serviceIntent = Intent(this, BackgroundService::class.java)
        startService(serviceIntent)

        // Check if we have location permission
        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission has already been granted, proceed with location-related tasks
            Toast.makeText(this, "Location permission is already granted.", Toast.LENGTH_SHORT)
                .show()
            // Proceed with your location-related tasks, like getting the user's location
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE ->                 // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted, yay! Do the location-related task you need to do.
                    Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT).show()
                    // Now you can use the location services API
                } else {
                    // Permission denied, boo! Disable the functionality that depends on this permission.
                    Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
                }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}