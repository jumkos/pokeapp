package com.example.pokeapp.ui.login

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LoginViewModel: ViewModel() {

    var email = MutableLiveData<String>()
    var  password = MutableLiveData<String>()

    var  _errorMessages = MutableLiveData<String>()
    val errorMessages: LiveData<String>
    get() = _errorMessages

    var _isForwardMainMenu =  MutableLiveData<Boolean>()
    val isForwardMainMenu: LiveData<Boolean>
        get() = _isForwardMainMenu

    var _goToSignUp = MutableLiveData<Boolean>()
    val goToSignUp: LiveData<Boolean>
    get() = _goToSignUp

    init {
        _isForwardMainMenu.value = false
        email.value =""
        password.value = ""
        _errorMessages.value = ""
        _goToSignUp.value = false
    }

    val MIN_PASSWORD_LENGTH = 6

    fun validateInput() {
        if (email.value == "") {
            _errorMessages.value = "Please Enter Email"
            _isForwardMainMenu.value = false
            return
        }
        if (password.value == "") {
            _errorMessages.value = "Please Enter Password"
            _isForwardMainMenu.value = false
            return
        }

        // checking the proper email format
        if (!isEmailValidFormat(email.value)) {
            _errorMessages.value = "Please Enter Valid Email"
            _isForwardMainMenu.value = false
            return
        }
        _errorMessages.value = ""
        _isForwardMainMenu.value = true
    }

    private fun isEmailValidFormat(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun goToSignUp(){
        _goToSignUp.value = true
    }

    fun resetGoToSignUp(){
        _goToSignUp.value = false
    }



    // Hook Click Event
    /*fun performSignUp(v: View) {
        if (validateInput()) {

            // Input is valid, here send data to your server
            val email = email!!.text.toString()
            val password = password!!.text.toString()
            //Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
            // Here you can call you API
            // Check this tutorial to call server api through Google Volley Library https://handyopinion.com
        }
    }*/
}