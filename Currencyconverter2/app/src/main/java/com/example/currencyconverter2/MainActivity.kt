package com.example.currencyconverter2

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.currencyconverter2.ui.theme.CurrencyConverter2Theme
import com.example.currencyconverter2.ui.theme.green1

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverter2Theme {
                Scaffold(
                    topBar={
                        TopAppBar(
                            colors= TopAppBarDefaults.smallTopAppBarColors(
                                containerColor=green1
                            ),
                            title= {
                            Text("Currency converter 2", color= Color.White, textAlign = TextAlign.Center) },
                        )
                    }
                ){
                    innerPadding->
                    Column(Modifier.padding(innerPadding)){
                        ComboBox(this@MainActivity)
                    }
                }
            }
        }
    }
}