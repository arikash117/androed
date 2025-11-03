package com.example.ariandroid.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ariandroid.R
import com.example.ariandroid.ui.theme.AppTypography

@Composable
fun SignUpField(
    modifier: Modifier = Modifier,
    titleText: String,
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    error: String? = null,
    trailingIcon: Boolean = true,
) {
    Column() {
        Text(
            text = titleText,
            style = AppTypography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = text,
                style = AppTypography.titleMedium.copy(color = Color.Gray)
            ) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            trailingIcon = if (trailingIcon) {
                {
                    Image(
                        painter = painterResource(id = R.drawable.visible),
                        contentDescription = "Toggle password visibility",
                        modifier = Modifier.size(15.dp)
                    )
                }
            } else {
                null
            },
            isError = error != null,
            supportingText = {
                if (!error.isNullOrBlank()) {
                    Text(text = error, color = Color.Red)
                }
            },
        )
    }
}