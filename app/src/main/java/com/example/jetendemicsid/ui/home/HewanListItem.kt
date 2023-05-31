package com.example.jetendemicsid

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetendemicsid.ui.theme.JetEndemicsIDTheme
import com.example.jetendemicsid.ui.theme.Shapes

@Composable
fun HewanListItem(
    nama: String,
    ilmiah: String,
    foto: String,
    modifier: Modifier = Modifier,
) {
    Card(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp).fillMaxWidth()
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = foto,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .clip(Shapes.medium)
            )
            Column(
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = nama,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                )
                Text(
                    text = ilmiah,
                    fontStyle = FontStyle.Italic,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HewanListItemPreview() {
    JetEndemicsIDTheme {
        HewanListItem(
            nama = "Harimau Sumatera",
            ilmiah = "Panthera tigris sumatrae",
            foto = "https://www.indonesia.travel/content/dam/indtravelrevamp/en/trip-ideas/10-hewan-endemik-yang-bisa-kamu-temukan-di-indonesia/macan.jpg"
        )
    }
}