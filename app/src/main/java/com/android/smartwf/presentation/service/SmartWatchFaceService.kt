package com.android.smartwf.presentation.service

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.SurfaceHolder
import androidx.wear.watchface.CanvasType
import androidx.wear.watchface.ComplicationSlot
import androidx.wear.watchface.ComplicationSlotsManager
import androidx.wear.watchface.TapEvent
import androidx.wear.watchface.WatchFace
import androidx.wear.watchface.WatchFaceService
import androidx.wear.watchface.WatchFaceType
import androidx.wear.watchface.WatchState
import androidx.wear.watchface.style.CurrentUserStyleRepository
import com.android.smartwf.presentation.render.ImageHelper
import com.android.smartwf.presentation.render.SmartRenderer
import com.android.smartwf.presentation.ui.activity.SettingsActivity
import com.android.smartwf.presentation.ui.activity.SmartSuggestionsActivity
import com.android.smartwf.presentation.utils.ComplicationUtils

class SmartWatchFaceService : WatchFaceService(), WatchFace.TapListener {

    private lateinit var imageHelper: ImageHelper

    override fun createComplicationSlotsManager(currentUserStyleRepository: CurrentUserStyleRepository): ComplicationSlotsManager {
        return ComplicationUtils.createComplicationSlotsManager(
            applicationContext,
            currentUserStyleRepository
        )
    }

    override suspend fun createWatchFace(
        surfaceHolder: SurfaceHolder,
        watchState: WatchState,
        complicationSlotsManager: ComplicationSlotsManager,
        currentUserStyleRepository: CurrentUserStyleRepository,
    ): WatchFace {
        imageHelper = ImageHelper.getInstance(applicationContext)
        val smartRenderer = SmartRenderer(
            applicationContext,
            surfaceHolder,
            watchState,
            complicationSlotsManager,
            currentUserStyleRepository,
            CanvasType.HARDWARE
        )

        val wf = WatchFace(
            watchFaceType = WatchFaceType.DIGITAL,
            renderer = smartRenderer
        )
        wf.setTapListener(this)

        return wf
    }

    @SuppressLint("WearRecents")
    override fun onTapEvent(tapType: Int, tapEvent: TapEvent, complicationSlot: ComplicationSlot?) {
        /*ImageHelper.iconPosRect?.let { iconPos ->
            val intent =
                if (tapEvent.xPos in iconPos.left..iconPos.right && tapEvent.yPos in iconPos.top..iconPos.bottom) {
                    Intent(this, SmartSuggestionsActivity::class.java)
                } else {
                    Intent(this, SettingsActivity::class.java)
                }

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }*/
        Log.d("DEBUG_ANKIT", "onTapEvent: is called tapType $tapType")
    }
}