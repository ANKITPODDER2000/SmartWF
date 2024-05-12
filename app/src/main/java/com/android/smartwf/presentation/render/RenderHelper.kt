package com.android.smartwf.presentation.render

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import androidx.wear.watchface.DrawMode
import androidx.wear.watchface.RenderParameters
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RenderHelper(context: Context) {

    private val imageHelper = ImageHelper.getInstance(context)

    fun setBackground(renderParameters: RenderParameters, canvas: Canvas, bounds: Rect) {
        if (renderParameters.drawMode == DrawMode.AMBIENT) {
            canvas.drawColor(Color.BLACK)
        } else {
            canvas.drawBitmap(
                ImageHelper.backgroundImg,
                null,
                bounds,
                ImageHelper.backgroundColorFilterPaint
            )
        }
    }

    fun setSmartSuggestionsIcon(canvas: Canvas, bounds: Rect) {
        ImageHelper.smartSuggestionsImg?.let {
            val iconPosRect = if (ImageHelper.iconPosRect != null)
                ImageHelper.iconPosRect!!
            else
                imageHelper.getIconPosRect(bounds)

            canvas.drawBitmap(it, null, iconPosRect, null)
        }
    }

    fun setDigitalWatchTime(canvas: Canvas, bounds: Rect) {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val timeText = timeFormat.format(Calendar.getInstance().time)
        canvas.drawText(
            timeText,
            bounds.centerX().toFloat(),
            bounds.height().toFloat() * 0.4F,
            ImageHelper.digitalTimePaint
        )
    }

}
