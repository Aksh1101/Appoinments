package com.example.appoinments.feature.topDoctors

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appoinments.R
import com.example.appoinments.core.model.DoctorModel
import com.example.appoinments.feature.topDoctors.components.DoctorsRowCard

@Composable
fun TopDoctorsScreen(
    doctors: List<DoctorModel>,
    onBack:()-> Unit,
    onOpenDetail: (DoctorModel) -> Unit,
    isLoading: Boolean = false
){
    val listState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize())
    {
        LazyColumn(state = listState,
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .height(56.dp))
                {
                    IconButton(onClick = onBack,
                        modifier = Modifier.align(Alignment.CenterStart))
                    {
                        Icon(painterResource(R.drawable.back),
                            contentDescription = null,
                            tint = Color.Unspecified)
                    }
                    Text(text = "Recommended List",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center))
                }
            }
            items(
                items = doctors,
                key = {it.Name+"_"+it.Mobile}
            ){doc->
                DoctorsRowCard(
                    item = doc,
                    onMakeCLick = {onOpenDetail(doc)}
                )
            }
        }
        when{
            isLoading->{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            doctors.isEmpty()->{
                Text(text = "It's Empty",
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center))
            }
        }
    }


}