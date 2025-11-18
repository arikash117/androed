package com.example.ariandroid.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.Car
import com.example.ariandroid.ui.theme.AppTypography
import com.example.ariandroid.ui.theme.BlackCurrant

@Composable
fun CarCard(
    car: Car,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "${car.model} ${car.bodyType}",
                        style = AppTypography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = car.brand,
                        style = AppTypography.titleSmall.copy(color = Color.Gray),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Row {
                        Text(
                            text = "${car.price}${car.currency}",
                            style = AppTypography.labelMedium.copy(color = BlackCurrant),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = stringResource(R.string.in_day),
                            style = AppTypography.titleMedium.copy(color = Color.LightGray)
                        )
                    }


                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.gearbox),
                            contentDescription = "A/T",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = car.transmission,
                            style = AppTypography.labelSmall,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.fuel),
                            contentDescription = "fuel",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = car.fuelType,
                            style = AppTypography.labelSmall,
                        )
                    }
                }

                Image(
                    painter = painterResource(id = car.imageUrl),
                    contentDescription = "${car.brand} ${car.model}",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(start = 16.dp),
                    contentScale = ContentScale.Fit
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                TextButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackCurrant),
                ) {
                    ProvideTextStyle(value = AppTypography.bodyMedium) {
                        Text(text = stringResource(R.string.to_rent), color = Color.White)
                    }
                }

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = stringResource(R.string.details), style = AppTypography.bodyMedium)
                }
            }
        }
    }
}

@Preview
@Composable
fun CarCardPreview() {
    CarCard(Car(
        id = "1",
        brand = "Dodge",
        model = "Challenger",
        price = 7500,
        bodyType = "HellCat",
        currency = "P",
        fuelType = "Бензин",
        transmission =   "A/T",
        imageUrl = R.drawable.car_img
    ))
}