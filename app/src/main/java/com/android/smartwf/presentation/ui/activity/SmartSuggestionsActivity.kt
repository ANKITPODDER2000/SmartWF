package com.android.smartwf.presentation.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.smartwf.presentation.ui.screen.SmartSuggestionsMainScreen

class SmartSuggestionsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartSuggestionsMainScreen()
        }
    }

}