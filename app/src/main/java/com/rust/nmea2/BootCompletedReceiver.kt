package com.rust.nmea1A

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("yallahhh", "onIntent "+intent)
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
            Log.e("yallahhh", "onIntent:: Start Background ")
            val serviceIntent = Intent(context, LocationService::class.java)
            context?.startForegroundService(serviceIntent)
        }
    }
}