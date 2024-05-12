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
        renderHelper.setBackground(renderParameters, canvas, bounds)
        renderHelper.setSmartSuggestionsIcon(canvas, bounds)
        renderHelper.setDigitalWatchTime(canvas, bounds)
        drawComplications(canvas, zonedDateTime)

        val colorMatrix = ColorMatrix().apply {
            setScale(0.5f, 0.5f, 0.5f, 0.2f)
        }
        canvas.drawRect(
            bounds.width() * 0.15F,
            bounds.height() * 0.6F,
            bounds.width() * 0.85F,
            bounds.height() * 0.8F,
            Paint().apply {
                color = Color.WHITE
                colorFilter = ColorMatrixColorFilter(colorMatrix)
                setShadowLayer(0F, 10F, 10F, Color.BLACK)
            }
        )

        val text = "Good morning!"
        val textBounds = Rect()
        val p = Paint().apply {
            color = Color.WHITE
            textSize = 26f
        }
        p.getTextBounds(text, 0, text.length, textBounds)
        val centerX = bounds.width() * 0.5F
        val centerY = bounds.height() * 0.7F // Adjust this value to vertically center the text

        canvas.drawText(
            text,
            centerX - textBounds.width() / 2,
            centerY + textBounds.height() / 2,
            p
        )
    }

    private fun drawComplications(canvas: Canvas, zonedDateTime: ZonedDateTime) {
        for ((_, complication) in complicationSlotsManager.complicationSlots) {
            if (complication.enabled) {
                complication.render(canvas, zonedDateTime, renderParameters)
            }
        }
    }
}