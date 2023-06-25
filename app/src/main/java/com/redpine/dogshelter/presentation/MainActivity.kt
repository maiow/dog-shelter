package com.redpine.dogshelter.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.redpine.dogshelter.R
import com.redpine.dogshelter.app.App
import com.redpine.dogshelter.databinding.ActivityMainBinding
import com.redpine.home.presentation.onboarding.OnboardingFragmentDirections
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

        if (viewModel.isOnboardingShown) {
            navController.navigate(OnboardingFragmentDirections.actionOnboardingFragmentToHomeNavGraph())
        }

        val navView: BottomNavigationView = binding.bottomNavView
        navView.setupWithNavController(navController)

        val navViewGoneList = listOf(
            com.redpine.home.R.id.onboardingFragment,
            com.redpine.home.R.id.authFragment,
            com.redpine.home.R.id.registrationFragment,
            com.redpine.home.R.id.resetPasswordFragment,
            com.redpine.home.R.id.authMessageFragment,
            com.redpine.home.R.id.filterFragment
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in navViewGoneList) navView.visibility = View.GONE
            else navView.visibility = View.VISIBLE
        }

        navView.setOnItemSelectedListener {item ->
            navController.navigate(
                resId = item.itemId,
                args = null,
                navOptions = NavOptions.Builder()
                    .setPopUpTo(destinationId = item.itemId, inclusive = true)
                    .setLaunchSingleTop(true)
                    .build()
            )
            return@setOnItemSelectedListener true
        }

        navView.setOnItemReselectedListener { item ->
            navController.navigate(
                resId = item.itemId,
                args = null,
                navOptions = NavOptions.Builder()
                    .setPopUpTo(destinationId = item.itemId, inclusive = true)
                    .setLaunchSingleTop(true)
                    .build()
            )
        }
    }
}