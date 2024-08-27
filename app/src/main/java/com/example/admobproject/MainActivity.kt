package com.example.admobproject

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.admobproject.ui.theme.AdmobProjectTheme
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdmobProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   BannerView(innerPadding, Modifier, "ca-app-pub-3940256099942544/9214589741")
                }
            }
        }
    }
}

@Composable
fun BannerView(innerPadding: PaddingValues, modifier: Modifier, adId:String){

    Column(modifier= modifier) {
        Spacer(modifier=Modifier.size(24.dp))
        AndroidView( modifier = Modifier.fillMaxWidth(),   factory ={context->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = adId
                loadAd(AdRequest.Builder().build())


            }

        } )

    }




}