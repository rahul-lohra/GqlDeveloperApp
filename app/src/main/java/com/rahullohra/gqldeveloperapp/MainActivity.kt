package com.rahullohra.gqldeveloperapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(getFakeIntent())
//        finish()

        RvAdapter()
        RoundTextView(this)
    }

    fun getFakeIntent(): Intent {
        val actionName = BuildConfig.APPLICATION_ID + ".fakeresponse.gqlTesting"
        return Intent(actionName).addCategory(Intent.CATEGORY_DEFAULT)
    }
}
