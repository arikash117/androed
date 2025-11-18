package com.example.ariandroid.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.ImagePickerEvent
import com.example.ariandroid.presentation.domain.model.ProfileItems
import com.example.ariandroid.presentation.viewmodel.signup.SignUp3ViewModel
import com.example.ariandroid.ui.components.NavBar
import com.example.ariandroid.ui.components.ProfileRow
import com.example.ariandroid.ui.theme.Background
import kotlinx.coroutines.delay
import rememberImagePicker

@Composable
fun ProfileScreen(
    navigateLogOut: () -> Unit,
    navigateToHome: () -> Unit = {},
    navigateToBookmarks: () -> Unit = {},
    navigateToSettings: () -> Unit = {},
    viewModel: SignUp3ViewModel = hiltViewModel(),
) {
    val signupData by viewModel.signupData.collectAsState()
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

    if (showSnackbar) {
        LaunchedEffect(true) {
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
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column(
                modifier = Modifier.height(240.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Профиль",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                )

                Spacer(modifier = Modifier.weight(1F))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                                    .size(95.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.ivan),
                                contentDescription = "Profile photo placeholder",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Иван Иванов",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "Присоединился в июле 2024",
                        fontSize = 14.sp,
                    )
                }
                Spacer(modifier = Modifier.weight(1F))
            }

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                ProfileItems.forEach {item ->
                    ProfileRow(
                        title = item.title,
                        bottomTitle = item.bottomTitle,
                    )
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 37.dp, vertical = 15.dp)
                    .clickable{ navigateLogOut() },
                text = "Выйти из профиля",
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )
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
fun PreviewProfileScreen() {
    ProfileScreen(
        navigateToHome = {},
        navigateToBookmarks = {},
        navigateToSettings = {},
        navigateLogOut = {},
    )
}
