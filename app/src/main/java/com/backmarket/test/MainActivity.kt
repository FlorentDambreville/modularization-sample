package com.backmarket.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.backmarket.navigation.Activities
import com.backmarket.navigation.Navigation.intentTo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(intentTo(Activities.Login))
        finish()
    }
}
