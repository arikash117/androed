package com.example.ariandroid.presentation.domain.model

import android.net.Uri


data class SignUpData (
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val acceptTerms: Boolean = false,

    val pfp: Uri? = null,
    val surname: String = "",
    val name: String = "",
    val lastName: String = "",
    val birthDate: String = "",
    val sex: String? = null,

    val driverID: String = "",
    val driverIDIssueDate: String = "",
//    val uploadDriverID: Boolean = false,
//    val uploadPassport: Boolean = false,
)

data class ValidationSignUpResult(
    val isSuccess: Boolean = false,

    val emailError: Int? = null,
    val passwordError: Int? = null,
    val confirmPasswordError: Int? = null,
    val termsError: Int? = null,

    val surnameError: Int? = null,
    val nameError: Int? = null,
    val lastNameError: Int? = null,
    val birthDateError: Int? = null,
    val sexError: Int? = null,

    val driverIDError: Int? = null,
    val driverIDIssueDateError: Int? = null,
)

sealed class SignUpValidationEvent {
    object Success : SignUpValidationEvent()
    data class Error(val message: String) : SignUpValidationEvent()
}

sealed class ImagePickerEvent {
    object PickImage : ImagePickerEvent()
}
