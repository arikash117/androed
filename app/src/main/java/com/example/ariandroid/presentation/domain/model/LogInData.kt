package com.example.ariandroid.presentation.domain.model

data class LogInData (
    val email: String = "",
    val password: String = "",
)

sealed class ValidationEvent {
    object Success : ValidationEvent()
}