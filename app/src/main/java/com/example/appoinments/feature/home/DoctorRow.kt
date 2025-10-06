package com.example.appoinments.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.appoinments.R
import com.example.appoinments.core.model.DoctorModel

@Composable
private fun DoctorCard(item: DoctorModel, onCardClick: () -> Unit){
    Card(
        modifier = Modifier.padding(8.dp)
            .size(width = 190.dp, height = 260.dp),
        shape = RoundedCornerShape(size = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onCardClick
    ){
        Column(modifier = Modifier.padding(8.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Box(modifier = Modifier.size(165.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = colorResource(R.color.lightPurple)),
                contentAlignment = Alignment.Center)
            {
                AsyncImage(
                    model = item.Picture,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.Name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.Special,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = colorResource(R.color.grey))
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(painter = painterResource(R.drawable.star),
                    contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = (item.Rating).toString(),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
                Spacer(modifier = Modifier.weight(1f))
                Image(painter = painterResource(R.drawable.experience),
                    contentDescription = null)
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "${item.Expriense} Year",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
            }
        }
    }
}

@Composable
fun DoctorRow(
    items: List<DoctorModel>,
    onCardClick: (DoctorModel) -> Unit
){
    Box(modifier = Modifier.fillMaxWidth().heightIn(min = 260.dp).padding(top = 16.dp)){
        if (items.isEmpty()){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }else{
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)){
                items(items){ item ->
                    DoctorCard(item = item, onCardClick = {onCardClick(item)})
                }
            }
        }
    }

}


@Preview
@Composable
fun DoctorCardPreview(){
    val item = DoctorModel(Name = "Dr. John Doe", Special = "Cardiology", Rating = 4.5)
    DoctorCard(item = item, onCardClick = {})

}