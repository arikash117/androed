package com.example.ariandroid.presentation.viewmodel.signup

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.ImagePickerEvent
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
    private val _imagePickerEvent = MutableStateFlow<ImagePickerEvent?>(null)
    val imagePickerEvent: StateFlow<ImagePickerEvent?> = _imagePickerEvent.asStateFlow()

    fun clearImagePickerEvent() {
        _imagePickerEvent.value = null
    }
    fun triggerImagePicker() {
        _imagePickerEvent.value = ImagePickerEvent.PickImage
    }

    fun onPFPChange(uri: Uri) {
        _signupData.value = _signupData.value.copy(pfp = uri)
    }
    fun onDriverIDChange(driverID: String) {
        _signupData.value = _signupData.value.copy(driverID = driverID)
        clearDriverIDError()
    }
    fun onIDIssueDateChange(driverIDIssueDate: String) {
        _signupData.value = _signupData.value.copy(driverIDIssueDate = driverIDIssueDate)
        clearIDIssueDateError()
    }

    fun validateData() : Boolean {
        val data = _signupData.value

        val driverIDError = when {
            data.driverID.isBlank() -> R.string.empty_field
            data.driverID.length == 10 -> R.string.driver_id_lenght
            !data.driverID.all { it.isDigit() } -> R.string.driver_id_has_num
            else -> null
        }
        val driverIDIssueDateError = when {
            data.driverIDIssueDate.isBlank() -> R.string.empty_field
            !isValidateIDIssueDate(data.driverIDIssueDate) -> R.string.date_invalid_format
            else -> null
        }

        _validationSignUpResult.value = ValidationSignUpResult(
            driverIDError = driverIDError,
            driverIDIssueDateError = driverIDIssueDateError,
            isSuccess = driverIDError == null && driverIDIssueDateError == null
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
                false
            } else if (date.year < 1900) {
                false
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
    fun clearValidationEvent() {
        _signupValidationEvent.value =null
    }
}