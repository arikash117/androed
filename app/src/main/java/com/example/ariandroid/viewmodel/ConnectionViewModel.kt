package com.example.ariandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ariandroid.util.NetworkingUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class ConnectionViewModel @Inject constructor(
    private val networkUtil: NetworkingUtil
) : ViewModel() {

    private val _connectionState = MutableStateFlow(true) // true = есть интернет
    val connectionState: StateFlow<Boolean> = _connectionState.asStateFlow()

    fun checkConnection() {
        _connectionState.value = networkUtil.isInternetAvailable()
    }

    fun refreshConnection() {
        checkConnection()
    }
}