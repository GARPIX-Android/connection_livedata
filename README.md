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
## License

```sh
MIT License

Copyright (c) 2021 GARPIX-Android

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
