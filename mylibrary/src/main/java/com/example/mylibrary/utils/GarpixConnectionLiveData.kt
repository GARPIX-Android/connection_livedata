package com.example.mylibrary.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.MutableLiveData

/**
 * LiveData reflecting the state of the Internet connection (present or absent)
 * Use like:
 * val connectionLiveData = ConnectionLiveData(getApplicationContext)
 * connectionLiveData.observer(lifecycleOwner, {
 *   //do something   })
 */

class GarpixConnectionLiveData(private val context: Context) : MutableLiveData<Boolean>() {

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
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.extras != null) {
                val activeNetwork = intent.extras!![ConnectivityManager.EXTRA_NETWORK_INFO] as NetworkInfo?
                val isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting
                if (isConnected) {
                    postValue(true)
                } else {
                    postValue(false)
                }
            }
        }
    }
}