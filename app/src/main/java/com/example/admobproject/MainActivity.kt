package com.example.admobproject

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.admobproject.ui.theme.AdmobProjectTheme
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdmobProjectTheme {
                Scaffold(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()) { innerPadding ->
//                    BannerView(innerPadding, Modifier, "ca-app-pub-3940256099942544/9214589741")

                    InterstitialBanner(innerPadding)
                }
            }
        }
    }
}

@Composable
fun BannerView(innerPadding: PaddingValues, modifier: Modifier, adId: String) {

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(24.dp))
        AndroidView(modifier = Modifier.fillMaxWidth(), factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = adId
                loadAd(AdRequest.Builder().build())


            }

        })

    }

}

@Composable
fun InterstitialBanner(innerPadding: PaddingValues) {
    val activity = LocalContext.current as Activity
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
    ) {

        Button(onClick = { showInterstitial(activity) }) {
            Text(text = "Mostrar Anuncio")

        }

    }


}

fun showInterstitial(activity: Activity) {

    loadInterstitialAd(activity) { interstitialAd ->
        if (interstitialAd != null) {

            interstitialAd.show(activity)

        } else {
            Log.d("Error", "Add Failed")

        }

    }

}


fun loadInterstitialAd(activity: Activity, callback: (InterstitialAd?) -> Unit) {

    val adRequest = AdRequest.Builder().build()

    InterstitialAd.load(
        activity,
        "ca-app-pub-3940256099942544/1033173712",
        adRequest,
        object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(error: LoadAdError) {
                super.onAdFailedToLoad(error)
                Log.d("Error", error.message)
                callback(null)
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                super.onAdLoaded(interstitialAd)
                callback(interstitialAd)


            }

        })


}