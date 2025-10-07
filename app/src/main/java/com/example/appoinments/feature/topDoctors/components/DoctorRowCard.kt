package com.example.appoinments.feature.topDoctors.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.appoinments.R
import com.example.appoinments.core.model.DoctorModel

@Composable
fun DoctorsRowCard(item : DoctorModel,
                   onMakeCLick : () -> Unit,) {
    val lightPurple = colorResource(R.color.lightPurple)
    val darkPurple = colorResource(R.color.darkPurple)
    val black = colorResource(R.color.black)
    val grey = colorResource(R.color.grey)

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            )
            {
                Row(modifier = Modifier.fillMaxWidth())
                {
                    Box(
                        modifier = Modifier.size(96.dp)
                            .background(color = lightPurple, shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    )
                    {
                        AsyncImage(
                            model = item.Picture,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                        Spacer(modifier = Modifier.width(16.dp))

                        Column(Modifier.weight(1f)) {
                            DegreeChip(text = "Professional Doctor")
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = item.Name , color = black , fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = item.Special, color = grey)

                            Row(verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(top = 8.dp))
                            {
                                ComposeRatingBar(rating = (item.Rating).toFloat())
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = item.Rating.toString(),
                                    color = black,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    OutlinedButton(onClick = onMakeCLick,
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 16.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = lightPurple,
                            contentColor = darkPurple
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            width = 1.dp,
                            brush = SolidColor(darkPurple)
                        )) {
                        Text(text = "Make an Appointment", fontWeight = FontWeight.Bold)
                    }
                }
                IconButton(onClick = {},
                    modifier = Modifier.padding(8.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Icon(painter = painterResource(R.drawable.fav_bold),
                        null,
                        tint = Color.Unspecified)
                }
            }
        }
    }