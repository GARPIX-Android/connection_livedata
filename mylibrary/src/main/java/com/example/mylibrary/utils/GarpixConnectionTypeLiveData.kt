@file:Suppress("DEPRECATION")

package com.example.mylibrary.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData

/**
 * LiveData reflecting the state of the Internet connection (Wi-FI, Mobile or None)
 * Use like:
 * val connectionLiveData = ConnectionLiveData(getApplicationContext)
 * connectionLiveData.observer(lifecycleOwner, {
 *   when (it.type) {
 *   ConnectionType.WIFI -> {}
 *   ConnectionType.MOBILE -> {}
 *   ConnectionType.NONE -> {}
 *   }
 */


class GarpixConnectionTypeLiveData(private val context: Context) : LiveData<ConnectionModel?>() {

    override fun onActive() {
        super.onActive()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver, filter)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(networkReceiver)
    }

    private val networkReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            if (intent.extras != null) {
                val activeNetwork =
                    intent.extras!![ConnectivityManager.EXTRA_NETWORK_INFO] as NetworkInfo?
                val isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting
                if (isConnected) {
                    when (activeNetwork!!.type) {
                        ConnectivityManager.TYPE_WIFI -> postValue(ConnectionModel(ConnectionType.WIFI, true))
                        ConnectivityManager.TYPE_MOBILE -> postValue(
                            ConnectionModel(
                                ConnectionType.MOBILE,
                                true
                            )
                        )
                    }
                } else {
                    postValue(ConnectionModel(ConnectionType.NONE, false))
                }
            }
        }
    }
}