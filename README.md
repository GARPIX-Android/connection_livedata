# Garpix Connection LiveData
GarpixConnectionLiveData and GarpixConnectionTypeLiveData designed to store the current state of the Internet connection

## Requirements
minSdkVersion 23 (Android 6.0 MarshMallow)

## Features
Allows the application to monitor the status of the internet connection in real time and respond to changes in the network connection

GarpixConnectionLiveData presents a simpler option and detects only the presence of a network connection (true/false)

GarpixConnectionTypeLiveData allows you to more accurately determine the information about the network connection used. Determines what type of connection is currently being used: Wi-FI or Mobile, or no connection

## Install

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```sh
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency

```sh
	 implementation 'com.github.GARPIX-Android:connection_livedata:1.0.0'
```
## Usage

> `GarpixConnectionLiveData`

```sh
	 val connectionLiveData: GarpixConnectionLiveData = GarpixConnectionLiveData(applicationContext);
         connectionLiveData.observe(this, {
            when (it) {
                true -> Log.d("TAG", "Connected to the network")
                false -> Log.d("TAG", "Disconnected from the network")
            }
        })
```

> `GarpixConnectionTypeLiveData`

```sh
	val connectionTypeLiveData: GarpixConnectionTypeLiveData = GarpixConnectionTypeLiveData(applicationContext);
        connectionTypeLiveData.observe(this, {
            it?.let {
                when (it.type) {
                    ConnectionType.WIFI -> Log.d("TAG", "Connected to the network by Wi-Fi")
                    ConnectionType.MOBILE -> Log.d("TAG", "Connected to the network by Mobile")
                    else -> Log.d("TAG", "Disconnected from the network")
                } 
            }   
        })
```

