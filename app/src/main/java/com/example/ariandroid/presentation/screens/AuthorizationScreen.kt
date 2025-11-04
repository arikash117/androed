package com.example.ariandroid.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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


@Composable
fun AuthorizationScreen(
    navigateToLogIn: () -> Unit,
    navigateToSignUp: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .systemBarsPadding()
        ) {
            //Заголовок
            Text(
                text = stringResource(R.string.app_drivenext),
                style = AppTypography.titleLarge.copy(color = BlackCurrant),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.title_description),
                style = AppTypography.titleMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(110.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.logo_dsc),
                modifier = Modifier.size(350.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            //Колонна кнопок
            Column {
                // Войти
                TextButton(
                    onClick = { navigateToLogIn() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = BlackCurrant, shape = RoundedCornerShape(8.dp)),
                ) {
                    Text(
                        text = stringResource(R.string.sign_in),
                        style = AppTypography.labelMedium,
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                // Зарегистрироваться
                TextButton(
                    onClick = { navigateToSignUp() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Background, shape = RoundedCornerShape(8.dp))
                        .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = stringResource(R.string.sign_up),
                        style = AppTypography.labelMedium.copy(color = Color.Gray),
                        textAlign = TextAlign.Center,
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorizationScreenPreview () {
    AuthorizationScreen(
        navigateToLogIn = {},
        navigateToSignUp = {},
    )
}