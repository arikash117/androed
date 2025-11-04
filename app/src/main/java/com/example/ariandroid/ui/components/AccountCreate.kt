package com.example.ariandroid.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ariandroid.R
import com.example.ariandroid.ui.theme.AppTypography


@Composable
fun AccountCreate(
    navigateBack: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.left_arrow),
            contentDescription = stringResource(R.string.left_arrow_dsc),
            modifier = Modifier
                .size(20.dp)
                .clickable(onClick = { navigateBack() })
        )

        Text(
            text = stringResource(R.string.create_account_title),
            style = AppTypography.titleLarge.copy(fontSize = 20.sp),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewAccount() {
    AccountCreate(
        navigateBack = {},
    )
}