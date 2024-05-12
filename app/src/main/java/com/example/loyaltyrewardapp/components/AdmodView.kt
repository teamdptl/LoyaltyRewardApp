package com.example.loyaltyrewardapp.components

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.loyaltyrewardapp.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@SuppressLint("VisibleForTests")
@Composable
fun BannerAdView(
    isTest: Boolean = true
) {
    val unitId = if (isTest) stringResource(id = R.string.ad_mob_test_banner_id) else stringResource(
        id = R.string.ad_mob_banner_id
    )

    AndroidView(
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = unitId
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}