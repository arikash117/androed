package com.example.ariandroid.ui.screens.authorization

import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ariandroid.R
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.ui.theme.BlackCurrant

@Preview
@Composable
fun SignUp2Screen(

) {

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
                .padding(horizontal = 24.dp).padding(top = 16.dp, bottom = 32.dp)
                .systemBarsPadding()
        ) {
            //Заголовок + форма
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box (
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image (
                        painter = painterResource(id = R.drawable.left_arrow),
                        contentDescription = "Google icon",
                        modifier = Modifier.size(20.dp)
                    )

                    Text (
                        text = "Создать аккаунт",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(100.dp))

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Фамилия
                    Column {
                        Text(
                            text = "Фамилия"
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = TextFieldValue(""),
                            onValueChange = { },
                            placeholder = { Text("Введите фамилию",
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Имя
                    Column {
                        Text(
                            text = "Имя"
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = TextFieldValue(""),
                            onValueChange = { },
                            placeholder = { Text("Введите имя",
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Отчество
                    Column {
                        Text(
                            text = "Отчество"
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = TextFieldValue(""),
                            onValueChange = { },
                            placeholder = { Text("Введите отчество",
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Дата
                    Column {
                        Text(
                            text = "Дата рождения"
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = TextFieldValue(""),
                            onValueChange = { },
                            placeholder = { Text("DD/MM/YYYY",
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.calendar),
                                    contentDescription = "Visible icon",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Column (
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text( text = "Пол" )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(40.dp).fillMaxWidth(),
                        ) {
                            val maleSelected = remember { mutableStateOf(true) }
                            val femaleSelected = remember { mutableStateOf(false) }
                            // Мужской
                            RadioButton(
                                selected = maleSelected.value,
                                onClick = { },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Black,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text( text = "Мужской")

                            Spacer(modifier = Modifier.width(10.dp))

                            //Женский
                            RadioButton(
                                selected = femaleSelected.value,
                                onClick = { },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Black,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text(text = "Женский")
                        }

                    }

                }
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = {},
                modifier = Modifier
                    .size(width = 350.dp, height = 50.dp)
                    .background(
                        color = BlackCurrant, shape = RoundedCornerShape(8.dp)
                    ),
            ) {
                Text(
                    text = "Далее",
                    color = Color(0xFFFFFFFF),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            }

        }
    }
}