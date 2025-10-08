package com.example.ariandroid.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ariandroid.R
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
fun OnboardingScreen (
    navigateToAutorization: () -> Unit,
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = pages[currentPage].currentImage),
            contentDescription = "Car image",
            modifier = Modifier
                .fillMaxSize(),

            )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 32.dp)
                .systemBarsPadding()
        ) {
            //Пропустить
            Text(
                text = stringResource(R.string.skip),
                style = MaterialTheme.typography.bodyLarge,
                //fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToAutorization },
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 26.dp)
                ) {
                    Text(
                        text = pages[currentPage].currentTitle,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = pages[currentPage].currentDescription,
                    )
                }

                Spacer(modifier = Modifier.height(64.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    OnboardingProgressIndicator(totalPages = pages.size, currentPage = currentPage)

                    Spacer(modifier = Modifier.weight(1f))

                    TextButton(
                        onClick = {
                            if (currentPage == pages.lastIndex) {
                                navigateToAutorization()
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
                            text = if (currentPage == pages.lastIndex) "Поехали" else "Далее",
                            color = Color(0xFFFFFFFF),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

        }

    }
}

// индекатор страниц
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
                        if (index == currentPage) BlackCurrant else Color.LightGray
                    )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OnboardingScreenPreview() {
    OnboardingScreen(
        navigateToAutorization = {}
    )
}