package com.example.ariandroid.presentation.screens.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.SignUpValidationEvent
import com.example.ariandroid.presentation.viewmodel.signup.SignUp1ViewModel
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.ui.theme.BlackCurrant


@Composable
fun SignUp1Screen(
    navigateToSignUp2: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: SignUp1ViewModel = hiltViewModel()
) {
    val signupData by viewModel.signupData.collectAsState()
    val validationSignUpResult by viewModel.validationSignUpResult.collectAsState()
    val signUpValidationEvent by viewModel.signUpValidationEvent.collectAsState()

    LaunchedEffect(signUpValidationEvent) {
        when (signUpValidationEvent) {
            is SignUpValidationEvent.Success -> {
                navigateToSignUp2()
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
            //Заголовок + форма
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.left_arrow),
                        contentDescription = "Left arrow",
                        modifier = Modifier.size(20.dp).clickable(onClick = {navigateBack()})
                    )

                    Text(
                        text = stringResource(R.string.create_account_title),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(100.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Почта
                    Column {
                        Text(
                            text = stringResource(R.string.email_title_field)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = signupData.email,
                            onValueChange = viewModel::onEmailChange,
                            placeholder = {
                                Text(
                                    stringResource(R.string.enter_email),
                                    color = Color.Gray,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            isError = validationSignUpResult.emailError != null,
                            supportingText = {
                                if (validationSignUpResult.emailError != null) {
                                    Text(
                                        text = validationSignUpResult.emailError!!,
                                        color = Color.Red,
                                    )
                                }
                            },
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Придумайте пароль
                    Column {
                        Text(
                            text = stringResource(R.string.password_field_title)
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = signupData.password,
                            onValueChange = viewModel::onPasswordChange,
                            placeholder = { Text(stringResource(R.string.enter_password), color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            trailingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.visible),
                                    contentDescription = "Visible icon",
                                    modifier = Modifier.size(15.dp)
                                )
                            },
                            isError = validationSignUpResult.passwordError != null,
                            supportingText = {
                                if (validationSignUpResult.passwordError != null) {
                                    Text(
                                        text = validationSignUpResult.passwordError!!,
                                        color = Color.Red,
                                    )
                                }
                            },
                        )

                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Повторите пароль
                    Column {
                        Text(
                            text = stringResource(R.string.repeat_password_field_title)
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = signupData.confirmPassword,
                            onValueChange = viewModel::onConfirmPasswordChange,
                            placeholder = { Text(stringResource(R.string.enter_password), color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            trailingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.visible),
                                    contentDescription = "Visible icon",
                                    modifier = Modifier.size(15.dp)
                                )
                            },
                            isError = validationSignUpResult.confirmPasswordError != null,
                            supportingText = {
                                if (validationSignUpResult.confirmPasswordError != null) {
                                    Text(
                                        text = validationSignUpResult.confirmPasswordError!!,
                                        color = Color.Red,
                                    )
                                }
                            },
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(40.dp)
                    ) {
                        Checkbox(
                            checked = signupData.acceptTerms,
                            onCheckedChange = viewModel::onAcceptTermsChange,
                            modifier = Modifier.size(20.dp),
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = stringResource(R.string.terms_agreement),
                            style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 14.sp),
                            fontSize = 12.sp,
                        )
                    }
                    if (validationSignUpResult.termsError != null) {
                        Text(
                            text = validationSignUpResult.termsError!!,
                            color = Color.Red,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = { viewModel.signup(navigateToSignUp2) },
                modifier = Modifier
                    .size(width = 350.dp, height = 50.dp)
                    .background(
                        color = BlackCurrant, shape = RoundedCornerShape(14.dp)
                    ),
            ) {
                Text(
                    text = stringResource(R.string.next),
                    color = Color(0xFFFFFFFF),
                    style = MaterialTheme.typography.bodyLarge,
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
        navigateToSignUp2 = {},
        navigateBack = {},
    )
}