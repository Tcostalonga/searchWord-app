package tarsila.costalonga.searchwordapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel @ViewModelInject constructor(): ViewModel() {

    val k = MutableLiveData("Zero")

}