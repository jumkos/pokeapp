package com.example.pokeapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.R

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<Int>().apply {
        value = R.string.rules_text
    }
    val text: LiveData<Int> = _text
}