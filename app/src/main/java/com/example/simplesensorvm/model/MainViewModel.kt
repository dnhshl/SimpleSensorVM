package com.example.simplesensorvm.model

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var _location = MutableLiveData<Location?>(null)
    val location: LiveData<Location?>
        get() = _location

    fun setLocation(location: Location) {
        _location.value = location
    }
}