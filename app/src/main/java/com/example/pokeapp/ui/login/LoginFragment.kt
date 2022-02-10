package com.example.pokeapp.ui.login

import android.content.Intent
import android.graphics.Path
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pokeapp.MainActivity
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.errorMessages.observe(this.viewLifecycleOwner, Observer {
            if (it != "") {
                showToast(it)
            }
        })

        viewModel.isForwardMainMenu.observe(this.viewLifecycleOwner, Observer {
            if (it) {
                navToMainMenu()
            }
        })

        viewModel.goToSignUp.observe(this.viewLifecycleOwner, Observer {
            if (it){
                goToSignup()
                viewModel.resetGoToSignUp()
            }
        })

        return binding.root
    }

    private fun showToast(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun navToMainMenu() {
        val init = Intent(this.activity, MainActivity::class.java)
        startActivity(init)
        this.activity?.finish()
    }

    fun goToSignup() {
        findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
    }

}