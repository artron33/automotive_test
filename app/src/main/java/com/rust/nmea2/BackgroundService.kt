package com.rust.nmea1A

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BackgroundService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("yallahhh", "BackgroundService:: onStartCommand ")
        Log.e("yallahhh", "BackgroundService:: start LocationService ")
        val locationServiceIntent = Intent(this, LocationService::class.java)
        startForegroundService(locationServiceIntent)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e("yallahhh", "BackgroundService:: onBind ")
        // This is a background service, so no binding provided
        return null
    }

}
