package com.android.smartwf.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.TimeTextDefaults
import com.android.smartwf.R

@Composable
fun SettingsMainScreen() {
    Scaffold(
        timeText = {
            TimeText(
                timeTextStyle = TimeTextDefaults.timeTextStyle(
                    fontSize = 12.sp
                )
            )
        }
    ) {
        Column {
            ScalingLazyColumn {
                item {
                    Text(
                        text = "Left Complication",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                items(1) {
                    Chip(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        onClick = { /*TODO*/ },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.person_running_solid),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        colors = ChipDefaults.secondaryChipColors(),
                        label = {
                            Text(
                                text = "Complication 1",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        },
                        secondaryLabel = {
                            Text(
                                text = "Steps Count...",
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    )
                }
                item {
                    Text(
                        text = "Right Complication",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }
                items(1) {
                    Chip(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        onClick = { /*TODO*/ },
                        colors = ChipDefaults.secondaryChipColors(),
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.cloud_solid),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = "Complication 2",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        },
                        secondaryLabel = {
                            Text(
                                text = "Weather ...",
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    )
                }
            }
        }
    }

}