package com.example.loyaltyrewardapp.screens.manager

import android.graphics.drawable.Drawable
import android.util.Log
import android.util.Rational
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.COORDINATE_SYSTEM_ORIGINAL
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.view.size
import com.example.loyaltyrewardapp.R
import com.example.loyaltyrewardapp.components.MainBackgroundScreen
import com.example.loyaltyrewardapp.ui.theme.White
import com.google.mlkit.vision.barcode.BarcodeScanner
import java.util.concurrent.Executors
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.barcode.common.Barcode.BarcodeFormat

@Composable
fun QRCodePreview() {
    AndroidView({ context ->
        val cameraExecutor = Executors.newSingleThreadExecutor()

        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()

        val barcodeScanner = BarcodeScanning.getClient(options)

        // Màn hình camera review
        val previewView = PreviewView(context).also {
            it.scaleType = PreviewView.ScaleType.FILL_CENTER // Hoặc FILL_CENTER
        }
        previewView.setBackgroundColor(Color.White.hashCode())

        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            val imageCapture = ImageCapture.Builder().build()
            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, MlKitAnalyzer(
                        listOf(barcodeScanner),
                        COORDINATE_SYSTEM_ORIGINAL,
                        ContextCompat.getMainExecutor(context)
                    ) { result: MlKitAnalyzer.Result? ->
                        val barcodeResults = result?.getValue(barcodeScanner)
                        if ((barcodeResults == null) ||
                            (barcodeResults.size == 0) ||
                            (barcodeResults.first() == null)
                        ) {
                            return@MlKitAnalyzer
                        }
                        Log.d("QRCode", "QR Code: ${barcodeResults[0].rawValue}")
                    })
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    context as ComponentActivity, cameraSelector, preview, imageCapture, imageAnalyzer)

            } catch(exc: Exception) {
                Log.e("DEBUG", "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(context))
        previewView
    },  modifier = Modifier.fillMaxSize())
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun ScanScreen(){
    var numberPhone by remember {
        mutableStateOf("")
    }

    MainBackgroundScreen(title = "Quét mã QR"){
        Column(modifier = Modifier.padding(top=20.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Hãy đảm bảo rằng có đủ ánh sáng, mã QR nằm trong khung bên dưới", textAlign = TextAlign.Center,
                color = Color.Black.copy(alpha = 0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 40.dp))
            Box(modifier = Modifier
                .padding(top = 20.dp)
                .width(300.dp)
                .height(300.dp)
                .border(2.dp, Color.Black.copy(alpha = 0.3f), RoundedCornerShape(10.dp))
            ){
                QRCodePreview()
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 30.dp, horizontal = 60.dp))
            Text(text = "Hoặc nhập số điện thoại của khách", textAlign = TextAlign.Center)
            OutlinedTextField(
                value = numberPhone,
                onValueChange = { numberPhone = it },
                modifier = Modifier.padding(vertical = 20.dp),
                label = { Text("Số điện thoại") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(vertical = 10.dp)
            ){
                Text(text = "Xác nhận")
            }

//
        }

    }
}

