package com.example.ariandroid.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ariandroid.ui.components.SettingsRow
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.firstGroupItems
import com.example.ariandroid.presentation.domain.model.myBookingsItem
import com.example.ariandroid.presentation.domain.model.secondGroupItems
import com.example.ariandroid.ui.components.NavBar
import com.example.ariandroid.ui.theme.BlackCurrant


@Composable
fun SettingsScreen(
    navigateToNext: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToBookmarks: () ->Unit,
    navigateToSettings: () ->Unit,
    navigateToProfile: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 40.dp)
        ){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Настройки",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){

                Row(
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ivan),
                        contentDescription = "pfp",
                        Modifier.size(80.dp).clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(20.dp))

                    Column(
                        modifier = Modifier.fillMaxHeight()
                    ){
                        Text(
                            text = "Иван Иванов",
                            color = BlackCurrant,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "ivan@mtuci.ru",
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(id = R.drawable.right_arrow),
                        contentDescription = "rignt arrow",
                        modifier = Modifier
                            .size(24.dp).clickable { navigateToProfile() }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                SettingsRow(
                    title = myBookingsItem.title,
                    iconResId = myBookingsItem.iconResId,
                    navigateToNext = navigateToNext,
                )

                Spacer(modifier = Modifier.height(8.dp))

                firstGroupItems.forEach { item ->
                    SettingsRow(
                        title = item.title,
                        iconResId = item.iconResId,
                        navigateToNext = navigateToNext,
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                secondGroupItems.forEach { item ->
                    SettingsRow(
                        title = item.title,
                        iconResId = item.iconResId,
                        navigateToNext = navigateToNext,
                    )
                }

            }
            Spacer(modifier = Modifier.weight(1f))
        }
        NavBar(
            currentScreen = "SettingsScreen",
            navigateToHome = navigateToHome,
            navigateToBookmarks = navigateToBookmarks,
            navigateToSettings = navigateToSettings,
        )
    }
}

@Preview
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(
        navigateToHome = {},
        navigateToBookmarks = {},
        navigateToSettings = {},
        navigateToNext = {},
        navigateToProfile = {},
    )
}