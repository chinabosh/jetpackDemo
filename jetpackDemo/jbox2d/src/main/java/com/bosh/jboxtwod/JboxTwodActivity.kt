package com.bosh.jboxtwod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class JboxTwodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jbox_twod)
         val jboxView : JboxView = findViewById(R.id.jbox)
        val btn : Button = findViewById(R.id.btn)
        btn.setOnClickListener {
            jboxView.jboxImpl.startWorld()
        }

    }
}