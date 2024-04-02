package com.rust.nmea1A

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.OnNmeaMessageListener
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import com.rust.nmea1A.R

class LocationService : Service() {
    private val CHANNEL_ID = "ForegroundServiceChannel"
    private lateinit var locationManager: LocationManager

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Existing implementation...
        Log.e("yallahhh", "LocationService:: onStartCommand ")
            createNotificationChannel()
            val notification: Notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Location Service")
                .setContentText("Fetching location updates...")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build()
            startForeground(1, notification)

            Log.e("yallahhh", "LocationService:: requestLocationUpdates  () ")
            requestLocationUpdates(this)

      //  locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return START_STICKY
        }
        Log.e("yallahhh", "LocationService:: addNmeaListener ")
       // locationManager.addNmeaListener(nmeaListener)

        return START_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Example Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("yallahhh", "LocationService:: onDestroy")
    }



    override fun onBind(p0: Intent?): IBinder? {
        Log.e("yallahhh", "LocationService:: onBind")
        return null
    }

    // Remaining methods unchanged...
}

fun requestLocationUpdates(context: Context) {
    val locationManager = context.getSystemService(Service.LOCATION_SERVICE) as LocationManager

    Log.e("yallahhh", "LocationService:: requestLocationUpdates  (1) ")
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        Log.e("yallahhh", "LocationService:: requestLocationUpdates  (SAD) "); return
    }

    Log.e("yallahhh", "LocationService:: requestLocationUpdates  (requesting) ")
    locationManager.requestLocationUpdates(
        LocationManager.GPS_PROVIDER,  // <z-- important, le type de provider
        System.currentTimeMillis(), 0f,
        object : LocationListener {
            override fun onLocationChanged(p0: Location?) {
                Log.e("yallahhh", "LocationService:: aze:: onLocationChanged $p0")
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                Log.e("yallahhh", "LocationService:: aze:: onStatusChanged $p0")
            }

            override fun onProviderEnabled(p0: String?) {
                Log.e("yallahhh", "LocationService:: aze:: onProviderEnabled $p0")
            }

            override fun onProviderDisabled(p0: String?) {
                Log.e("yallahhh", "LocationService:: onProviderDisabled $p0")
            }

        }
    )
    val nmeaListener = OnNmeaMessageListener { message, timestamp ->
        // Here you can handle the NMEA messages received from the GPS
        // For example, you can parse the NMEA sentences and use them as needed
        Log.e("yallahhh", "LocationService:: addNmeaListener message == $message ")
    }
    locationManager.addNmeaListener(nmeaListener)
}