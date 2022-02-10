package com.example.pokeapp.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel: ViewModel() {

    private var _goToLogin = MutableLiveData<Boolean>()
    val goToLogin: LiveData<Boolean>
        get() = _goToLogin

    init {
        _goToLogin.value = false
    }

    fun goToLogin(){
        _goToLogin.value = true
    }

    fun resetGoToLogin(){
        _goToLogin.value = false
    }
}