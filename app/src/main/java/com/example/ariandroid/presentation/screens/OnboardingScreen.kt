package com.example.ariandroid.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ariandroid.R
import com.example.ariandroid.ui.theme.AppTypography
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.ui.theme.BlackCurrant
import kotlin.Int
import kotlin.String

data class OnboardingPage(
    val currentImage: Int,
    val currentTitle: String,
    val currentDescription: String,
)


@Composable
fun OnboardingScreen(
    navigateToAuthorization: () -> Unit,
) {

    val pages = listOf(
        OnboardingPage(
            currentImage = R.drawable.onboarding_one,
            currentTitle = stringResource(R.string.onboarding_first_title),
            currentDescription = stringResource(R.string.onboarding_first_description),
        ),
        OnboardingPage(
            currentImage = R.drawable.onboarding_two,
            currentTitle = stringResource(R.string.onboarding_second_title),
            currentDescription = stringResource(R.string.onboarding_second_description),
        ),
        OnboardingPage(
            currentImage = R.drawable.onboarding_three,
            currentTitle = stringResource(R.string.onboarding_third_title),
            currentDescription = stringResource(R.string.onboarding_third_description),
        ),
    )
    var currentPage by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = stringResource(R.string.skip),
                style = AppTypography.labelMedium.copy(
                    color = BlackCurrant,
                ),
                modifier = Modifier
                    .clickable { navigateToAuthorization() },
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(52.dp))

        Image(
            painter = painterResource(id = pages[currentPage].currentImage),
            contentDescription = stringResource(R.string.car_image_dsc),
            modifier = Modifier
                .size(500.dp),
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)

        ) {
            // Текст
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = pages[currentPage].currentTitle,
                    style = AppTypography.titleLarge,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = pages[currentPage].currentDescription,
                    style = AppTypography.bodyMedium,
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            // Индекатор страниц + кнопка
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                OnboardingProgressIndicator(totalPages = pages.size, currentPage = currentPage)

                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    onClick = {
                        if (currentPage == pages.lastIndex) {
                            navigateToAuthorization()
                        } else {
                            currentPage++
                        }
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .background(
                            color = BlackCurrant, shape = RoundedCornerShape(14.dp)
                        ),
                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = if (currentPage == pages.lastIndex) stringResource(R.string.lets_ride) else stringResource(
                            R.string.next
                        ),
                        style = AppTypography.labelMedium,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

// Индекатор страниц
@Composable
fun OnboardingProgressIndicator(
    totalPages: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalPages) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == currentPage) BlackCurrant else Color.LightGray),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(
        navigateToAuthorization = {}
    )
}