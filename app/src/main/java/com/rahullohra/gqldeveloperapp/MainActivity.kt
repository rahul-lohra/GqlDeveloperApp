package com.rahullohra.gqldeveloperapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rahullohra.fakeresponse.presentaiton.activities.FakeResponseActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, FakeResponseActivity::class.java))
        finish()
    }
}
