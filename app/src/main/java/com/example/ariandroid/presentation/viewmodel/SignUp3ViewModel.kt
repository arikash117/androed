package com.example.ariandroid.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ariandroid.presentation.domain.model.SignUpData
import com.example.ariandroid.presentation.domain.model.SignUpValidationEvent
import com.example.ariandroid.presentation.domain.model.ValidationSignUpResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

@HiltViewModel
class SignUp3ViewModel @Inject constructor() : ViewModel() {

    private val _signupData = MutableStateFlow(SignUpData())
    val signupData: StateFlow<SignUpData> = _signupData.asStateFlow()
    private val _validationSignUpResult = MutableStateFlow(ValidationSignUpResult())
    val validationSignUpResult: StateFlow<ValidationSignUpResult> = _validationSignUpResult.asStateFlow()
    private val _signupValidationEvent = MutableStateFlow<SignUpValidationEvent?>(null)
    val signUpValidationEvent: StateFlow<SignUpValidationEvent?> = _signupValidationEvent.asStateFlow()

    fun onDriverIDChange(driverID: String) {
        _signupData.value = _signupData.value.copy(driverID = driverID)
        clearDriverIDError()
    }
    fun onIDIssueDateChange(driverIDIssueDate: String) {
        _signupData.value = _signupData.value.copy(driverIDIssueDate = driverIDIssueDate)
        clearIDIssueDateError()
    }
//    fun onUploadDriverIDChange(uploaded: Boolean) {
//        _signupData.value = _signupData.value.copy(uploadDriverID = uploaded)
//        clearUploadDriverIDError()
//    }
//    fun onUploadPassportChange(uploadPassport: Boolean) {
//        _signupData.value = _signupData.value.copy(uploadPassport = uploadPassport)
//        clearUploadPassportError()
//    }

    fun validateData() : Boolean {
        val data = _signupData.value

        val driverIDError = when {
            data.driverID.isBlank() -> ""
            data.driverID.length == 10 -> "Номер водительского удостоверения должен состоять из 10 цифр"
            !data.driverID.all { it.isDigit() } -> "Номер водительского удостоверения содержит только цифры"
            else -> null
        }
        val driverIDIssueDateError = when {
            data.driverIDIssueDate.isBlank() -> ""
            !isValidateIDIssueDate(data.driverIDIssueDate) -> "Неверный формат даты. Используйте ДД.ММ.ГГГГ"
            else -> null
        }
//        val uploadDriverIDError = if (!data.uploadDriverID) "Необходимо загрузить фото" else null
//        val uploadPassportError = if (!data.uploadPassport) "Необходимо загрузить фото" else null

        _validationSignUpResult.value = ValidationSignUpResult(
            driverIDError = driverIDError,
            driverIDIssueDateError = driverIDIssueDateError,
//            uploadDriverIDError = uploadDriverIDError,
//            uploadPassportError = uploadPassportError,
            isSuccess = driverIDError == null && driverIDIssueDateError == null
//                    && uploadDriverIDError == null && uploadPassportError == null
        )
        return validationSignUpResult.value.isSuccess
    }

    fun signup(navigateToCongrats: () -> Unit) {
        viewModelScope.launch {
            if (validateData()) {
                try {
                    _signupValidationEvent.value = SignUpValidationEvent.Success
                } catch (e: Exception) {
                    _signupValidationEvent.value = SignUpValidationEvent.Error("${e.message}")
                }
            }
        }
    }

    private fun isValidateIDIssueDate(driverIDIssueDate: String): Boolean {
        return try {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val date = LocalDate.parse(driverIDIssueDate, formatter)

            if (date.isAfter(LocalDate.now())) {
                false // дата в будущем — недопустима
            } else if (date.year < 1900) {
                false // слишком старая дата
            } else {
                true
            }
        } catch (e: DateTimeParseException) {
            false
        }
    }

    private fun clearDriverIDError() {
        _validationSignUpResult.value = _validationSignUpResult.value.copy(driverIDError = null)
    }
    private fun clearIDIssueDateError() {
        _validationSignUpResult.value = _validationSignUpResult.value.copy(driverIDIssueDateError = null)
    }
//    private fun clearUploadDriverIDError() {
//        _validationSignUpResult.value = _validationSignUpResult.value.copy(uploadDriverIDError = null)
//    }
//    private fun clearUploadPassportError() {
//        _validationSignUpResult.value = _validationSignUpResult.value.copy(uploadPassportError = null)
//    }
    fun clearValidationEvent() {
        _signupValidationEvent.value =null
    }
}