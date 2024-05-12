package com.android.smartwf.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.TimeTextDefaults
import com.android.smartwf.R

@Composable
fun SmartSuggestionsMainScreen() {
    Scaffold(
        timeText = {
            TimeText(
                timeTextStyle = TimeTextDefaults.timeTextStyle(
                    fontSize = 12.sp
                )
            )
        }
    ) {
        ScalingLazyColumn {
            item {
                ListHeader {
                    Text(
                        text = "List Header ....",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }

            items(20) {
                Chip(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },
                    colors = ChipDefaults.primaryChipColors(),
                    label = {
                        Text(text = "Header...", maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.star_of_life_solid),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    secondaryLabel = {
                        Text(
                            text = "Content...",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }
    }
}