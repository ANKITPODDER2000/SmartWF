package com.android.smartwf.presentation.service

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
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
import com.android.smartwf.presentation.MainActivity
import com.android.smartwf.presentation.render.ImageHelper
import com.android.smartwf.presentation.render.SmartRenderer
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
        ImageHelper.iconPosRect?.let { iconPos ->
            if (tapEvent.xPos in iconPos.left..iconPos.right && tapEvent.yPos in iconPos.top..iconPos.bottom) {

                // Starting an activity

                val intent = Intent(this, MainActivity::class.java)
                intent.flags = FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

                Log.d("DEBUG_ANKIT", "onTapEvent: is clicked for smart suggestion...")
            } else {
                Log.d("DEBUG_ANKIT", "onTapEvent: clicked out side")
            }
        }
    }
}