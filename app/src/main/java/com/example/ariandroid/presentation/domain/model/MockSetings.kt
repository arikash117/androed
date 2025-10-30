package com.example.ariandroid.presentation.domain.model

import com.example.ariandroid.R

data class SettingsItem(
    val id: Int,
    val title: String,
    val iconResId: Int,
    val showDivider: Boolean = false
)

val myBookingsItem = SettingsItem(1, "Мои бронирования", R.drawable.my_booking_icon)

val firstGroupItems = listOf(
    SettingsItem(2, "Тема", R.drawable.theme_icon),
    SettingsItem(3, "Уведомления", R.drawable.notifications_icon),
    SettingsItem(4, "Подключить свой автомобиль", R.drawable.add_auto_icon)
)

val secondGroupItems = listOf(
    SettingsItem(5, "Помощь", R.drawable.help_icon),
    SettingsItem(6, "Пригласи друга", R.drawable.friend_link_icon)
)