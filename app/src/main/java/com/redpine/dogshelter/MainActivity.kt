package com.redpine.dogshelter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.redpine.api.Api

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      Log.e("Kart",Api().getInfo())
        setContentView(R.layout.activity_main)
    }
}