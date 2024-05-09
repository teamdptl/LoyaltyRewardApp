package com.example.loyaltyrewardapp.components

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.outlined.AddAPhoto
import androidx.compose.material.icons.rounded.AddAPhoto
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.loyaltyrewardapp.R

@Composable
fun ImagePicker(text: String, imageUri: String = ""){
    var selectedImage by remember { mutableStateOf(imageUri) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        selectedImage = uri?.toString().toString()
    }

    ButtonImagePicker(text, selectedImage) {
        launcher.launch("image/jpeg")
    }
}
@Composable
fun ButtonImagePicker(
    text: String,
    selectedImage: String,
    onClickImage: () -> Unit){
        if (selectedImage != "") {
            Log.d("Image Picker", selectedImage)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://placehold.co/400")
                    .crossfade(true)
                    .build(),
//                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
            )
        }
        else{
            OutlinedButton(onClick = onClickImage) {
                Icon(Icons.Rounded.AddAPhoto, contentDescription = "")
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = text)
            }
        }
}

@Preview
@Composable
private fun Preview() {
    ImagePicker("Choose Image", "")
}