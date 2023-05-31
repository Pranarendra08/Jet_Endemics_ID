package com.example.jetendemicsid

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetendemicsid.ui.theme.JetEndemicsIDTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.foto_saya),
            contentDescription = "Foto Diri",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(top = 32.dp)
                .size(200.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)

        )
        Text(
            text = "Pranarendra Dwikurnia",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(top = 16.dp)
                .fillMaxWidth()

        )
        Text(
            text = "pranarendra@gmail.com",
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(top = 8.dp)
                .fillMaxWidth()

        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JetEndemicsIDTheme {
        ProfileScreen()
    }
}