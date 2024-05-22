package com.android.smartwf.presentation.render

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RenderHelper(context: Context) {

    private val imageHelper = ImageHelper.getInstance(context)

    fun setBackground(canvas: Canvas, bounds: Rect) {
        val paint = Paint().apply {
            shader = LinearGradient(
                0f, 0f, 0f, bounds.centerY().toFloat() + 50,
                intArrayOf(Color.BLUE, Color.BLACK),
                null,
                Shader.TileMode.CLAMP
            )
        }

        canvas.drawPaint(paint)
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
            bounds.height().toFloat() * 0.25F,
            ImageHelper.digitalTimePaint
        )
    }

    fun setBottomBox(canvas: Canvas, bounds: Rect) {
        val colorMatrix1 = ColorMatrix().apply {
            setScale(0.5f, 0.5f, 0.5f, 0.3f)
        }

        val colorMatrix2 = ColorMatrix().apply {
            setScale(0.5f, 0.5f, 0.5f, 0.2f)
        }

        val path = Path()
        path.addRoundRect(
            RectF(
                bounds.width() * 0.25F,
                bounds.height() * 0.7F,
                bounds.width() * 0.75F,
                bounds.height() * 0.8F
            ),
            floatArrayOf(
                0F, 0F, // Top left corner radius
                0F, 0F, // Top right corner radius
                30F, 30F, // Bottom right corner radius
                30F, 30F // Bottom left corner radius
            ),
            Path.Direction.CW
        )

        canvas.drawPath(path, Paint().apply {
            color = Color.WHITE
            colorFilter = ColorMatrixColorFilter(colorMatrix2)
            setShadowLayer(0F, 20F, 20F, Color.BLACK)
        })

        // Large Box
        canvas.drawRoundRect(
            bounds.width() * 0.15F,
            bounds.height() * 0.4F,
            bounds.width() * 0.85F,
            bounds.height() * 0.7F,
            30F, // Corner radius
            30F, // Corner radius
            Paint().apply {
                color = Color.WHITE
                colorFilter = ColorMatrixColorFilter(colorMatrix1)
                setShadowLayer(40F, 40F, 40F, Color.BLACK)
            }
        )

        val topRightX = bounds.width() * 0.8F
        val topRightY = bounds.height() * 0.55F

        val imageWidth = 40F
        val imageHeight = 40F

        val imageBounds = RectF(
            topRightX - imageWidth,
            topRightY - (imageHeight / 2),
            topRightX,
            topRightY + (imageHeight / 2)
        )

        ImageHelper.smartSuggestionsImg?.let {
            canvas.drawBitmap(it, null, imageBounds, null)
        }

    }

    fun setWeather(canvas: Canvas, bounds: Rect) {

        val canvasWidth = bounds.width()
        val canvasHeight = bounds.height()

        val topMiddleX = canvasWidth / 2F
        val topMiddleY = canvasHeight * 0.05F

        val imgSize = 20F

        val imageBounds = RectF(
            (topMiddleX - imgSize / 2),
            topMiddleY,
            (topMiddleX + imgSize / 2),
            (topMiddleY + imgSize)
        )

        ImageHelper.smartSuggestionsImg?.let {
            canvas.drawBitmap(it, null, imageBounds, null)
        }

        val textY = imageBounds.right + 10F

        canvas.drawText("23F", imageBounds.right + 10F, imageBounds.centerY()+8F, Paint().apply {
            color = Color.WHITE
            textSize = 20F
        })
    }

}
