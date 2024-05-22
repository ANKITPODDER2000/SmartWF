package com.android.smartwf.presentation.render

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.Rect
import android.view.SurfaceHolder
import androidx.wear.watchface.ComplicationSlotsManager
import androidx.wear.watchface.Renderer
import androidx.wear.watchface.WatchState
import androidx.wear.watchface.style.CurrentUserStyleRepository
import java.time.ZonedDateTime

private const val FRAME_PERIOD_MS_DEFAULT = 16L

class SmartRenderer(
    context: Context,
    surfaceHolder: SurfaceHolder,
    watchState: WatchState,
    private val complicationSlotsManager: ComplicationSlotsManager,
    currentUserStyleRepository: CurrentUserStyleRepository,
    canvasType: Int,
) : Renderer.CanvasRenderer2<SmartRenderer.DigitalSharedAssets>(
    surfaceHolder,
    currentUserStyleRepository,
    watchState,
    canvasType,
    FRAME_PERIOD_MS_DEFAULT,
    clearWithBackgroundTintBeforeRenderingHighlightLayer = false
) {
    val renderHelper = RenderHelper(context)

    class DigitalSharedAssets : SharedAssets {
        override fun onDestroy() {
            TODO("Not yet implemented")
        }
    }

    override suspend fun createSharedAssets(): DigitalSharedAssets {
        return DigitalSharedAssets()
    }

    override fun renderHighlightLayer(
        canvas: Canvas,
        bounds: Rect,
        zonedDateTime: ZonedDateTime,
        sharedAssets: DigitalSharedAssets,
    ) {
        TODO("Not yet implemented")
    }


    override fun render(
        canvas: Canvas,
        bounds: Rect,
        zonedDateTime: ZonedDateTime,
        sharedAssets: DigitalSharedAssets,
    ) {
        renderHelper.setBackground(canvas, bounds)
        renderHelper.setWeather(canvas, bounds)
        renderHelper.setSmartSuggestionsIcon(canvas, bounds)
        renderHelper.setDigitalWatchTime(canvas, bounds)
        renderHelper.setBottomBox(canvas, bounds)
    }
}