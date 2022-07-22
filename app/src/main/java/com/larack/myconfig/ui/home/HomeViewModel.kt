package com.larack.myconfig.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.larack.netconfig.NativeLib

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
        value = NativeLib().stringFromJNI()
    }
    val text: LiveData<String> = _text

}