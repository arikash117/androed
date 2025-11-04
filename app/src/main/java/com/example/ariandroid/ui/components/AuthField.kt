package com.example.ariandroid.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ariandroid.ui.theme.AppTypography

@Composable
fun AuthField(
    modifier: Modifier = Modifier,
    titleText: String,
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    error: Int? = null,
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
            isError = error != null,
            supportingText = {
                if (error != null) {
                    Text(
                        text = stringResource(id = error),
                        style = AppTypography.titleSmall,
                    )
                }
            },
        )
    }
}