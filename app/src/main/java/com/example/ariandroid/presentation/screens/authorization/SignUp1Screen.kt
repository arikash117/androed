package com.example.ariandroid.presentation.screens.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.SignUpValidationEvent
import com.example.ariandroid.presentation.viewmodel.signup.SignUp1ViewModel
import com.example.ariandroid.ui.components.AccountCreate
import com.example.ariandroid.ui.components.AuthField
import com.example.ariandroid.ui.components.PassField
import com.example.ariandroid.ui.theme.AppTypography
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.ui.theme.BlackCurrant


@Composable
fun SignUp1Screen(
    navigateToNext: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: SignUp1ViewModel = hiltViewModel()
) {
    val signupData by viewModel.signupData.collectAsState()
    val validationSignUpResult by viewModel.validationSignUpResult.collectAsState()
    val signUpValidationEvent by viewModel.signUpValidationEvent.collectAsState()

    LaunchedEffect(signUpValidationEvent) {
        when (signUpValidationEvent) {
            is SignUpValidationEvent.Success -> {
                navigateToNext()
                viewModel.clearValidationEvent()
            }
            is SignUpValidationEvent.Error -> {
                viewModel.clearValidationEvent()
            }
            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 32.dp)
                .systemBarsPadding()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AccountCreate(navigateBack = navigateBack)

                Spacer(modifier = Modifier.height(100.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AuthField(
                        titleText = stringResource(R.string.email_title_field),
                        text =stringResource(R.string.enter_email),
                        value = signupData.email,
                        onValueChange = viewModel::onEmailChange,
                        error = validationSignUpResult.emailError,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    PassField(
                        titleText = stringResource(R.string.password_field_title),
                        text = stringResource(R.string.enter_password),
                        value = signupData.password,
                        onValueChange = viewModel::onPasswordChange,
                        error = validationSignUpResult.passwordError,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    PassField(
                        titleText = stringResource(R.string.repeat_password_field_title),
                        text = stringResource(R.string.enter_password),
                        value = signupData.confirmPassword,
                        onValueChange = viewModel::onConfirmPasswordChange,
                        error = validationSignUpResult.confirmPasswordError,
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(40.dp)
                    ) {
                        Checkbox(
                            checked = signupData.acceptTerms,
                            onCheckedChange = viewModel::onAcceptTermsChange,
                            modifier = Modifier.size(20.dp),
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.LightGray,
                                uncheckedColor = Color.LightGray,
                                checkmarkColor = BlackCurrant,
                            )
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = stringResource(R.string.terms_agreement),
                            style = AppTypography.labelSmall.copy(lineHeight = 14.sp),
                        )
                    }
                    validationSignUpResult.termsError?.let { errorResId ->
                        Text(
                            text = stringResource(id = errorResId),
                            style = AppTypography.titleSmall,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = { viewModel.signup(navigateToNext) },
                modifier = Modifier
                    .size(width = 350.dp, height = 50.dp)
                    .background(
                        color = BlackCurrant, shape = RoundedCornerShape(14.dp)
                    ),
            ) {
                Text(
                    text = stringResource(R.string.next),
                    style = AppTypography.labelMedium,
                    textAlign = TextAlign.Center,
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUp1ScreenPreview () {
    SignUp1Screen(
        navigateToNext = {},
        navigateBack = {},
    )
}