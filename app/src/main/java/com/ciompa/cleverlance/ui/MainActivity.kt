package com.ciompa.cleverlance.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ciompa.cleverlance.R
import com.ciompa.cleverlance.ui.main.MainView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainView.newInstance())
                .commitNow()
        }
    }

}
