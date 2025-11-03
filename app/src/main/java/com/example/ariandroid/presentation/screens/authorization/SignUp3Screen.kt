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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.ImagePickerEvent
import com.example.ariandroid.presentation.domain.model.SignUpValidationEvent
import com.example.ariandroid.presentation.viewmodel.signup.SignUp3ViewModel
import com.example.ariandroid.ui.components.AccountCreate
import com.example.ariandroid.ui.components.SignUpField
import com.example.ariandroid.ui.theme.AppTypography
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.ui.theme.BlackCurrant
import kotlinx.coroutines.delay
import rememberImagePicker

@Composable
fun SignUp3Screen(
    navigateToCongrats: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: SignUp3ViewModel = hiltViewModel()
) {
    val signupData by viewModel.signupData.collectAsState()
    val validationSignUpResult by viewModel.validationSignUpResult.collectAsState()
    val signUpValidationEvent by viewModel.signUpValidationEvent.collectAsState()
    val imagePickerEvent by viewModel.imagePickerEvent.collectAsState()

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }


    val imagePicker = rememberImagePicker(
        onImageSelected = { uri ->
            if (uri != null) {
                viewModel.onPFPChange(uri)
            }
        },
        onPermissionDenied = {
            snackbarMessage = "Для выбора фото необходимо разрешение на доступ к галерее"
            showSnackbar = true
        }
    )

    LaunchedEffect(imagePickerEvent) {
        when (imagePickerEvent) {
            is ImagePickerEvent.PickImage -> {
                imagePicker.pickImage()
                viewModel.clearImagePickerEvent()
            }
            else -> {}
        }
    }

    LaunchedEffect(signUpValidationEvent) {
        when (signUpValidationEvent) {
            is SignUpValidationEvent.Success -> {
                navigateToCongrats()
                viewModel.clearValidationEvent()
            }
            is SignUpValidationEvent.Error -> {
                viewModel.clearValidationEvent()
            }
            else -> {}
        }
    }

    if (showSnackbar) {
        LaunchedEffect(showSnackbar) {
            delay(3000)
            showSnackbar = false
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF323232),
            ) {
                Text(
                    text = snackbarMessage,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 32.dp)
                .systemBarsPadding()
        ) {
            // Заголовок + Аватар + Форма
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AccountCreate(navigateBack = navigateBack)

                Spacer(modifier = Modifier.height(32.dp))

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .clickable{ viewModel.triggerImagePicker() },
                                contentAlignment = Alignment.Center
                            ) {
                                if (signupData.pfp != null) {
                                    AsyncImage(
                                        model = signupData.pfp,
                                        contentDescription = "Profile photo",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                } else {
                                    Image(
                                        painter = painterResource(id = R.drawable.pfp),
                                        contentDescription = "Profile photo placeholder",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(CircleShape)
                                    )
                                }
                            }
                            Image(
                                painter = painterResource(id = R.drawable.add_pfp),
                                contentDescription = "Add photo",
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.TopEnd)
                                    .offset(y = 75.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = stringResource(R.string.create_pfp_description),
                            style = AppTypography.titleMedium,
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Форма
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SignUpField(
                            titleText = stringResource(R.string.driver_id_field_title),
                            text = stringResource(R.string.driver_id),
                            value = signupData.driverID,
                            onValueChange = viewModel::onDriverIDChange,
                            error = validationSignUpResult.driverIDError,
                            trailingIcon = false,
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        SignUpField(
                            titleText = stringResource(R.string.issue_date),
                            text = stringResource(R.string.dd_mm_yyyy),
                            value = signupData.driverIDIssueDate,
                            onValueChange = viewModel::onIDIssueDateChange,
                            error = validationSignUpResult.driverIDIssueDateError,
                            trailingIcon = false,
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Загрузка водительского удостоверения
                        Column (
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(R.string.upload_driver_id),
                                style = AppTypography.titleMedium,
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                            )
                            {
                                Image(
                                    painter = painterResource(id = R.drawable.upload),
                                    contentDescription = "upload icon",
                                    modifier = Modifier.size(50.dp)
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = stringResource(R.string.upload_photo),
                                    style = AppTypography.titleMedium.copy(color = Color.Gray)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Column (
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(R.string.upload_passport),
                                style = AppTypography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                            )
                            {
                                Image(
                                    painter = painterResource(id = R.drawable.upload),
                                    contentDescription = "Visible icon",
                                    modifier = Modifier.size(50.dp)
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = stringResource(R.string.upload_photo),
                                    style = AppTypography.titleMedium.copy(color = Color.Gray)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = { viewModel.signup(navigateToCongrats) },
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
fun SignUp3ScreenPreview () {
    SignUp3Screen(
        navigateToCongrats = {},
        navigateBack = {},
    )
}