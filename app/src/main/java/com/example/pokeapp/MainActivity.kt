package com.example.pokeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pokeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _->
            Log.println(Log.INFO, "MainActivity", "DesId"+nd.id.toString())
            if(nd.id == R.id.navigation_game || nd.id == R.id.navigation_game_won || nd.id == R.id.navigation_game_over){
                navView.visibility = View.GONE
            }else{
                navView.visibility = View.VISIBLE
            }

        }

        val isLogin = true
        if (!isLogin){
            forwardToLogin()
        }
    }

    fun forwardToLogin(){
        val init = Intent(this, LoginActivity::class.java)
        startActivity(init)
        finish()
    }

}