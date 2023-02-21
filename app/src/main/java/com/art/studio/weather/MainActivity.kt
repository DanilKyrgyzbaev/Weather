package com.art.studio.weather

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
//        this.window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
//        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }
}
