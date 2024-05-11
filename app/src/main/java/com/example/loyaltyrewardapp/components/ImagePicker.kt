package com.example.loyaltyrewardapp.components

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddAPhoto
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.loyaltyrewardapp.data.viewmodel.AdminCURCouponViewModel

@Composable
fun ImagePicker(updateUri: (String) -> Unit, text: String, imageUri: String = "", enable : Boolean = false){
    var selectedImage by remember { mutableStateOf(imageUri) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        if (uri != null){
            selectedImage = uri.toString()
            updateUri(selectedImage)
        }
    }

    ButtonImagePicker(text, selectedImage, enable) {
        launcher.launch("image/jpeg")
        Log.d("ImagePicker", "link: " + selectedImage)
    }
}
@Composable
fun ButtonImagePicker(
    text: String,
    selectedImage: String,
    enable: Boolean,
    onClickImage: () -> Unit){
    Log.d("ImagePicker", "link: " + selectedImage)

        if (selectedImage != "") {
            Log.d("Image Picker", selectedImage)
            if("https" in selectedImage){
                AsyncImage(model = selectedImage,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                    .clickable(
                        enabled = enable
                    ) { onClickImage() })

            }else{
                Image(
                    painter = rememberAsyncImagePainter(model = selectedImage),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp).clickable { onClickImage() }
                )
            }

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
//    ImagePicker("Choose Image", "")
}