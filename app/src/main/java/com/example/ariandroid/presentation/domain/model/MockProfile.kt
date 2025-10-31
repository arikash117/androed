package com.example.ariandroid.presentation.domain.model

data class ProfileItem(
    val title: String,
    val bottomTitle: String,
)

val ProfileItems = listOf(
    ProfileItem("Электронная почта", "ivanov@mtuci.ru"),
    ProfileItem("Пароль", "Поменять пароль"),
    ProfileItem("Пол", "Мужской"),
    ProfileItem("Google", "ivanov@gmail.com"),
    )
