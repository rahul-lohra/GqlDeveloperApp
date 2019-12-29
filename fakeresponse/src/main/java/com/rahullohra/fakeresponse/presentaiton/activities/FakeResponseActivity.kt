package com.rahullohra.fakeresponse.presentaiton.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.rahullohra.fakeresponse.R
import com.rahullohra.fakeresponse.presentaiton.fragments.DownloadFragment
import com.rahullohra.fakeresponse.presentaiton.fragments.FakeResponseFragment

class FakeResponseActivity : AppCompatActivity() {

    lateinit var fm:FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fake_response)

        fm = findViewById(R.id.fm)

        if(savedInstanceState == null)
            showFragment()
    }

    fun showFragment(){

        supportFragmentManager
            .beginTransaction()
            .add(fm.id, DownloadFragment.newInstance())
            .commit()
    }

}
