package com.android.smartwf.presentation.render

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.android.smartwf.R

import kotlin.math.ceil

class ImageHelper private constructor(context: Context) {
    fun getIconPosRect(bounds: Rect): Rect {
        val watchFaceWidth = bounds.width()
        val watchFaceHeight = bounds.height()
        val bottomMiddleX = watchFaceWidth / 2
        val bottomMiddleY = ceil(watchFaceHeight * 0.9).toInt()
        val iconRect = Rect(
            bottomMiddleX - (SMART_SUGGESTION_ICON_SIZE / 2),
            bottomMiddleY - (SMART_SUGGESTION_ICON_SIZE / 2),
            bottomMiddleX + (SMART_SUGGESTION_ICON_SIZE / 2),
            bottomMiddleY + (SMART_SUGGESTION_ICON_SIZE / 2)
        )
        iconPosRect = iconRect
        return iconPosRect!!
    }

    companion object {

        const val SMART_SUGGESTION_ICON_SIZE = 25

        var iconPosRect: Rect? = null

        lateinit var backgroundImg: Bitmap
        var smartSuggestionsImg: Bitmap? = null
        lateinit var backgroundColorFilterPaint: Paint

        val digitalTimePaint = Paint().apply {
            color = Color.WHITE
            isAntiAlias = true
            textSize = 70f
            textAlign = Paint.Align.CENTER
        }

        @Volatile
        private var sInstance: ImageHelper? = null

        fun getInstance(context: Context): ImageHelper {
            if (sInstance == null) {
                sInstance = ImageHelper(context)
            }
            return sInstance!!
        }
    }

    init {
        try {
            backgroundColorFilterPaint = Paint()
            val colorMatrix = ColorMatrix().apply {
                setScale(0.2f, 0.2f, 0.2f, 1.0f)
            }
            backgroundColorFilterPaint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        } catch (e: Exception) {
            Log.e("DEBUG_ANKIT", "render: e : $e")
        }

        try {
            smartSuggestionsImg =
                ContextCompat.getDrawable(context, R.drawable.star_of_life_solid)?.toBitmap()
            Log.d("DEBUG_ANKIT", "star image : $smartSuggestionsImg")
        } catch (e: Exception) {
            Log.e("DEBUG_ANKIT_ERROR", "error : e : $e")
        }
    }
}