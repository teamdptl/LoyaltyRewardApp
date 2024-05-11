package com.example.loyaltyrewardapp.screens

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.birjuvachhani.locus.Locus
import org.publicvalue.multiplatform.qrcode.CodeType
import org.publicvalue.multiplatform.qrcode.ScannerWithPermissions

@Composable
fun TestCamera() {
    ScannerWithPermissions(onScanned = { Log.d("TestCamera", it); true }, types = listOf(CodeType.QR),
            modifier = Modifier.width(50.dp).height(50.dp)
    )

    Locus.getCurrentLocation(LocalContext.current) { result ->
        result.location?.let { Log.d("TestCamera", "Location: ${it.longitude} ${it.latitude}") }
        result.error?.let { /* Received error! */ }
    }
}