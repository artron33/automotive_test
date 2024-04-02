package com.rust.nmea1A;

import static com.rust.nmea1A.LocationServiceKt.requestLocationUpdates;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.rust.nmea1A.R;

public class MyActivity extends Activity {

    private LocationManager locationManager;
    private TextView nmeaTextView;

    private OnNmeaMessageListener nmeaMessageListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Log.e("nMea", "onCreate");

       // nmeaTextView = findViewById(R.id.nmeaTextView); // Assume you have a TextView in your layout to display NMEA messages
        Log.e("nMea", "onCreate  LocationManager");

        requestLocationUpdates(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.e("nMea", "onCreate  NULL NULL");
            Log.e("nMea", "onCreate  NULL NULL");
            Log.e("nMea", "onCreate  NULL NULL");
            return;
        }




/**

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,   // <-- important, le type de provider
                System.currentTimeMillis(), 0,
                location -> { } // une call back pas utile
        );
        nmeaMessageListener = (tram_nmea, timestamp) -> {
            // This method is called when an NMEA message is received
        };
        locationManager.addNmeaListener(nmeaMessageListener);


 *
 03-22 20:21:26.087  1915  1959 W ActivityManager: Slow operation: 78ms so far, now at startProcess: building log message
 03-22 20:21:26.087  1915  1959 I ActivityManager: Start proc 2158:com.android.networkstack/1073 for service {com.android.networkstack/com.android.server.NetworkStackService}
 03-22 20:21:26.087  1915  1959 W ActivityManager: Slow operation: 78ms so far, now at startProcess: starting to update pids map
 03-22 20:21:26.087  1915  1959 W ActivityManager: Slow operation: 79ms so far, now at startProcess: done updating pids map
 03-22 20:21:26.089  1454  1454 I hwservicemanager: getTransport: Cannot find entry android.hardware.gnss@2.0::IGnss/default in either framework or device manifest.
 03-22 20:21:26.090  1915  1915 D GnssLocationProvider: gnssHal 2.0 was null, trying 1.1
 03-22 20:21:26.090  1454  1454 I hwservicemanager: getTransport: Cannot find entry android.hardware.gnss@1.1::IGnss/default in either framework or device manifest.
 03-22 20:21:26.090  1915  1915 D GnssLocationProvider: gnssHal 1.1 was null, trying 1.0
 03-22 20:21:26.104  2115  2115 I ndroid.systemu: The ClassLoaderContext is a special shared library.
 03-22 20:21:26.105  1915  1915 W LocationManagerService: no network location provider found
 03-22 20:21:26.107  1915  1915 E LocationManagerService: no geocoder provider found
 03-22 20:21:26.107  1915  1915 D LocationManagerService: Unable to bind FLP Geofence proxy.
 03-22 20:21:26.107  1915  1915 E ActivityRecognitionHardware: activity_recognition HAL is deprecated. class_init is effectively a no-op
 03-22 20:21:26.107  1915  1915 E ActivityRecognitionHardware: activity_recognition HAL is deprecated. is_supported is effectively a no-op
 03-22 20:21:26.107  1915  1915 D LocationManagerService: Hardware Activity-Recognition not supported.
 03-22 20:21:26.108  1915  1915 D LocationManagerService: Unable to bind ActivityRecognitionProxy.
 03-22 20:21:26.113  1819  1935 I OMXClient: IOmx service obtained
 03-22 20:21:26.114  1825  1933 I OMXMaster: makeComponentInstance(OMX.google.vp9.encoder) in android.hardwar process
 03-22 20:21:26.116  1819  1935 W OMXUtils: do not know color format 0x7f000789 = 2130708361
 03-22 20:21:26.116  1825  1933 E OMXNodeInstance: getConfig(0xafefac00:google.vp9.encoder, ConfigAndroidIntraRefresh(0x6f60000a)) ERROR: Undefined(0x80001001)
 03-22 20:21:26.116  1825  1933 E OMXNodeInstance: getParameter(0xafefac00:google.vp9.encoder, ParamVideoIntraRefresh(0x6000006)) ERROR: UnsupportedIndex(0x8000101a)
 03-22 20:21:26.116  1819  1935 D MediaCodecInfo: detail feature-frame-parsing wasn't present to remove
 03-22 20:21:26.118  1915  1949 D GnssLocationProvider: Link to death notification successful
 03-22 20:21:26.120  1724  1751 I GnssHAL_GnssInterface: setCallback
 03-22 20:21:26.121  1915  1949 I GnssLocationProvider: Unable to initialize IGnssXtra interface.
 03-22 20:21:26.121  1915  1949 I GnssLocationProvider: Unable to initialize IAGnss interface.
 03-22 20:21:26.121  1915  1949 I GnssLocationProvider: Unable to initialize IGnssGeofencing interface.
 03-22 20:21:26.121  1915  1949 I GnssLocationProvider: Unable to initialize IGnssNi interface.
 03-22 20:21:26.121  1915  1949 I GnssLocationProvider: Unable to initialize IAGnssRil interface.
 03-22 20:21:26.121  1724  1751 I GnssHAL_GnssInterface: cleanup
 03-22 20:21:26.125  1819  1935 I Codec2Client: Available Codec2 services: "software"
 03-22 20:21:26.125  1819  1935 I Codec2Client: Creating a Codec2 client to service "software"
 03-22 20:21:26.128  1819  1935 I Codec2Client: Client to Codec2 service "software" created
 03-22 20:21:26.128  1835  1936 V C2Store : in init
 */

        try {
            // Register the OnNmeaMessageListener
        } catch (SecurityException e) {
            // Handle the case where the required location permissions are not granted
            Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Don't forget to unregister the listener when it's no longer needed to avoid memory leaks
        if (locationManager != null && nmeaMessageListener != null) {
            locationManager.removeNmeaListener(nmeaMessageListener);
        }
    }
}