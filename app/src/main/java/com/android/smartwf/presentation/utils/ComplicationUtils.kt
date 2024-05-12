package com.android.smartwf.presentation.utils

import android.content.Context
import android.graphics.RectF
import androidx.wear.watchface.CanvasComplicationFactory
import androidx.wear.watchface.ComplicationSlot
import androidx.wear.watchface.ComplicationSlotsManager
import androidx.wear.watchface.complications.ComplicationSlotBounds
import androidx.wear.watchface.complications.DefaultComplicationDataSourcePolicy
import androidx.wear.watchface.complications.SystemDataSources
import androidx.wear.watchface.complications.data.ComplicationType
import androidx.wear.watchface.complications.rendering.CanvasComplicationDrawable
import androidx.wear.watchface.complications.rendering.ComplicationDrawable
import androidx.wear.watchface.style.CurrentUserStyleRepository
import com.android.smartwf.R

class ComplicationUtils {
    companion object {
        fun createComplicationSlotsManager(
            context: Context,
            currentUserStyleRepository: CurrentUserStyleRepository,
        ): ComplicationSlotsManager {

            val defaultCanvasComplicationFactory =
                CanvasComplicationFactory { watchState, listener ->
                    CanvasComplicationDrawable(
                        ComplicationDrawable.getDrawable(
                            context,
                            R.drawable.complication_style_left
                        )!!,
                        watchState,
                        listener
                    )
                }

            val leftComplication = ComplicationSlot.createRoundRectComplicationSlotBuilder(
                5,
                defaultCanvasComplicationFactory,
                listOf(
                    ComplicationType.RANGED_VALUE,
                    ComplicationType.MONOCHROMATIC_IMAGE,
                    ComplicationType.SHORT_TEXT,
                    ComplicationType.SMALL_IMAGE
                ),
                defaultDataSourcePolicy = DefaultComplicationDataSourcePolicy(
                    SystemDataSources.DATA_SOURCE_STEP_COUNT,
                    ComplicationType.SHORT_TEXT
                ),
                bounds = ComplicationSlotBounds(
                    RectF(
                        0.15F, 0.3F, 0.25F, 0.4F
                    )
                )
            ).build()

            val rightComplication = ComplicationSlot.createRoundRectComplicationSlotBuilder(
                6,
                defaultCanvasComplicationFactory,
                listOf(
                    ComplicationType.RANGED_VALUE,
                    ComplicationType.MONOCHROMATIC_IMAGE,
                    ComplicationType.SHORT_TEXT,
                    ComplicationType.SMALL_IMAGE
                ),
                defaultDataSourcePolicy = DefaultComplicationDataSourcePolicy(
                    SystemDataSources.DATA_SOURCE_WATCH_BATTERY,
                    ComplicationType.SHORT_TEXT
                ),
                bounds = ComplicationSlotBounds(
                    RectF(
                        0.75F, 0.3F, 0.85F, 0.4F
                    )
                )
            ).build()

            return ComplicationSlotsManager(
                listOf(leftComplication, rightComplication),
                currentUserStyleRepository
            )
        }
    }
}