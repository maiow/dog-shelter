package com.redpine.dogshelter.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.redpine.dogshelter.R
import com.redpine.dogshelter.app.App
import com.redpine.dogshelter.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as App).appComponent.inject(this)
        viewModel  = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        val navView: BottomNavigationView = binding.bottomNavView
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == com.redpine.home.R.id.onboardingFragment
                || destination.id == com.redpine.home.R.id.authFragment
                || destination.id == com.redpine.home.R.id.registrationFragment
                || destination.id == com.redpine.home.R.id.resetPasswordFragment
                || destination.id == com.redpine.home.R.id.authMessageFragment
                || destination.id == com.redpine.home.R.id.filterFragment
            )
                navView.visibility = View.GONE
            else navView.visibility = View.VISIBLE
        }

        if (viewModel.isOnboardingShown)
            navController.navigate(
                com.redpine.home.R.id.action_onboardingFragment_to_home_nav_graph
            )
    }
}