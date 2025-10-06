package com.example.appoinments.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import com.example.appoinments.R
import com.example.appoinments.core.model.DoctorModel
import com.example.appoinments.feature.detail.components.ActionItem
import com.example.appoinments.feature.detail.components.RatingStat
import com.example.appoinments.feature.detail.components.StateColumn
import com.example.appoinments.feature.detail.components.VerticalDivider

@Composable
fun DetailBody(
    item: DoctorModel,
    onOpenWebsite: (String) -> Unit,
    onSendSms: (String, String) -> Unit,
    onDial: (String) -> Unit,
    onDirection: (String) -> Unit,
    onShare: (String, String) -> Unit
) {
    val darkPurple = colorResource(R.color.darkPurple)
    val lightPurple = colorResource(R.color.lightPurple)
    val grey = colorResource(R.color.grey)

    Column(modifier = Modifier.fillMaxWidth()
        .padding(bottom = 16.dp))
    {
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = item.Name,
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp))

        Text(text = item.Special,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 16.dp, top = 16.dp, start = 16.dp))
        {
            Image(painterResource(R.drawable.location), contentDescription = null)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.Address,
                color = darkPurple,
                modifier = Modifier.weight(1f))

        }

        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically)
        {
            StateColumn(title = "Patients" , value = item.Patiens)
            VerticalDivider(color = grey)
            StateColumn(title = "Experience" , value = "${item.Expriense} Years")
            VerticalDivider(color = grey)
            RatingStat(title = "Rating", rating = item.Rating)
        }
        Text(text = "Biography",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp)
        )
        Text(text = item.Biography,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()
            .padding(top = 16.dp, bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically)
        {
            ActionItem(
                label = "Website",
                icon = R.drawable.website,
                lightPurple = lightPurple,
                enabled = !item.Site.isNullOrBlank()
            ){
                item.Site.let { onOpenWebsite }
            }
            ActionItem(
                label = "Message",
                icon = R.drawable.message,
                lightPurple = lightPurple,
                enabled = !item.Mobile.isNullOrBlank()
            ){
                onSendSms(item.Mobile, "the SMS Text")
            }
            ActionItem(
                label = "Call",
                icon = R.drawable.call,
                lightPurple = lightPurple,
                enabled = item.Site.isNullOrBlank()
            ){
                onDial(item.Mobile)
            }
            ActionItem(
                label = "Direction",
                icon = R.drawable.direction,
                lightPurple = lightPurple,
                enabled = !item.Site.isNullOrBlank()
            ){
                item.Location.let {onDirection}
            }
            ActionItem(
                label = "Share",
                icon = R.drawable.share,
                lightPurple = lightPurple,
                enabled = !item.Site.isNullOrBlank()
            ){
                val subject = item.Name
                val text  = "${item.Name} ${item.Address} ${item.Mobile}"
                onShare(subject, text)
            }
        }
    }

}