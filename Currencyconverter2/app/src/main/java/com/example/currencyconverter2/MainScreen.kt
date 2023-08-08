package com.example.currencyconverter2

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.currencyconverter2.ui.theme.green1
import com.example.currencyconverter2.ui.theme.grey1
import org.json.JSONObject
import java.math.RoundingMode

const val API_KEY="17ab432786b3d109d99d499b5384a634"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(currencyPair: String, context: Context){
    var currency1Enter by remember{
        mutableStateOf(TextFieldValue(""))
    }
    val currency2Value = remember{mutableStateOf("0.00")}
    val otherCurrency1Value = remember{mutableStateOf("0.00")}
    val otherCurrency2Value = remember{mutableStateOf("0.00")}
    val otherCurrency3Value = remember{mutableStateOf("0.00")}
    val dialogState1 = remember {
        mutableStateOf(false)
    }
    val dialogState2 = remember {
        mutableStateOf(false)
    }
    if(dialogState1.value){
        AlertDialog(dialogState1, "Enter a value in \"USD\" field!")
    }
    if(dialogState2.value){
        AlertDialog(dialogState2, "The app can't get data!")
    }

    var imageId1: Int? =null
    var imageId2: Int?=null
    var imageId3: Int?=null
    var imageId4: Int?=null
    var imageId5: Int?=null
    var currency1: String?=null
    var currency2: String?=null
    var otherCurrency1: String? = null
    var otherCurrency2: String? = null
    var otherCurrency3: String? = null
    when(currencyPair){
        "USD-EUR"->{
            imageId1=R.drawable.us_flag
            imageId2=R.drawable.eu_flag
            imageId3=R.drawable.uk_flag
            imageId4=R.drawable.canada_flag
            imageId5=R.drawable.switzerland_flag
            currency1="USD"
            currency2="EUR"
            otherCurrency1="GBP"
            otherCurrency2="CAD"
            otherCurrency3="CHF"
        }
        "USD-GBP"->{
            imageId1=R.drawable.us_flag
            imageId2=R.drawable.uk_flag
            imageId3=R.drawable.eu_flag
            imageId4=R.drawable.canada_flag
            imageId5=R.drawable.switzerland_flag
            currency1="USD"
            currency2="GBP"
            otherCurrency1="EUR"
            otherCurrency2="CAD"
            otherCurrency3="CHF"
        }
        "USD-CAD"->{
            imageId1=R.drawable.us_flag
            imageId2=R.drawable.canada_flag
            imageId3=R.drawable.eu_flag
            imageId4=R.drawable.uk_flag
            imageId5=R.drawable.switzerland_flag
            currency1="USD"
            currency2="CAD"
            otherCurrency1="EUR"
            otherCurrency2="GBP"
            otherCurrency3="CHF"
        }
        "USD-CHF"->{
            imageId1=R.drawable.us_flag
            imageId2=R.drawable.switzerland_flag
            imageId3=R.drawable.eu_flag
            imageId4=R.drawable.uk_flag
            imageId5=R.drawable.canada_flag
            currency1="USD"
            currency2="CHF"
            otherCurrency1="EUR"
            otherCurrency2="GBP"
            otherCurrency3="CAD"
        }
    }
    Card(modifier= Modifier.fillMaxWidth(),
        shape= RoundedCornerShape(15.dp),
        elevation=CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor=green1
        )
    ){
        Column(modifier=Modifier.padding(5.dp)
        ){
            //Валюта 1
            Row(modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(modifier=Modifier.padding(end=5.dp)){
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Image(painter=painterResource(id= imageId1!!),
                            contentDescription="image1",
                            modifier= Modifier.padding(end=5.dp)
                                .size(64.dp)
                                .clip(CircleShape)
                        )
                        Text(text=currency1!!, fontSize=25.sp, color = Color.White)
                    }
                }
                TextField(
                    value = currency1Enter,
                    onValueChange={ newText ->
                        currency1Enter=newText
                    },
                    keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Number),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(10.dp),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
                )
            }
            Spacer(modifier=Modifier.height(5.dp))
            //Валюта 2
            Row(modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(modifier=Modifier.padding(end=5.dp)){
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Image(painter=painterResource(id= imageId2!!),
                            contentDescription="image2",
                            modifier= Modifier.padding(end=5.dp)
                                .size(64.dp)
                                .clip(CircleShape)
                        )
                        Text(text=currency2!!, fontSize=25.sp, color=Color.White)
                    }
                }
                Text(text=currency2Value.value, textAlign=TextAlign.End, color=Color.White, modifier=Modifier.padding(end=15.dp))
            }
        }
    }
    Spacer(modifier=Modifier.height(5.dp))
    Column(modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween){
        Card(modifier= Modifier.fillMaxWidth(),
            shape= RoundedCornerShape(15.dp),
            elevation=CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(
                containerColor=grey1
            )
        ){
            Column(
            ){
                Box(modifier=Modifier.padding(5.dp)){
                    Column(){
                        Text("Other currencies:")
                        Spacer(modifier=Modifier.height(5.dp))
                        //Другая валюта 1
                        Row(modifier=Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Box(modifier=Modifier.padding(end=5.dp)){
                                Row(verticalAlignment = Alignment.CenterVertically){
                                    Image(painter=painterResource(id= imageId3!!),
                                        contentDescription="image3",
                                        modifier= Modifier.padding(end=5.dp)
                                            .size(64.dp)
                                            .clip(CircleShape)
                                    )
                                    Text(text=otherCurrency1!!, fontSize=25.sp)
                                }
                            }
                            Text(text=otherCurrency1Value.value, textAlign=TextAlign.End, modifier=Modifier.padding(end=15.dp))
                        }
                        Spacer(modifier=Modifier.height(5.dp))
                        Row(modifier=Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Box(modifier=Modifier.padding(end=5.dp)){
                                Row(verticalAlignment = Alignment.CenterVertically){
                                    Image(painter=painterResource(id= imageId4!!),
                                        contentDescription="image4",
                                        modifier= Modifier.padding(end=5.dp)
                                            .size(64.dp)
                                            .clip(CircleShape)
                                    )
                                    Text(text=otherCurrency2!!, fontSize=25.sp)
                                }
                            }
                            Text(text=otherCurrency2Value.value, textAlign=TextAlign.End, modifier=Modifier.padding(end=15.dp))
                        }
                        Spacer(modifier=Modifier.height(5.dp))
                        Row(modifier=Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Box(modifier=Modifier.padding(end=5.dp)){
                                Row(verticalAlignment = Alignment.CenterVertically){
                                    Image(painter=painterResource(id= imageId5!!),
                                        contentDescription="image5",
                                        modifier= Modifier.padding(end=5.dp)
                                            .size(64.dp)
                                            .clip(CircleShape)
                                    )
                                    Text(text=otherCurrency3!!, fontSize=25.sp)
                                }
                            }
                            Text(text=otherCurrency3Value.value, textAlign=TextAlign.End, modifier=Modifier.padding(end=15.dp))
                        }
                    }
                }
            }
        }
        Button(onClick={
            if(currency1Enter.text!=""){
                val url="http://apilayer.net/api/live?access_key=$API_KEY&currencies=EUR,GBP,CAD,CHF&source=USD&format=1"
                val queue = Volley.newRequestQueue(context)
                val stringRequest = StringRequest(
                    Request.Method.GET,
                    url,
                    {
                            response ->
                        val obj = JSONObject(response)

                        val usd_eur=obj.getJSONObject("quotes").getString("USDEUR")
                        val usd_gbp=obj.getJSONObject("quotes").getString("USDGBP")
                        val usd_cad=obj.getJSONObject("quotes").getString("USDCAD")
                        val usd_chf=obj.getJSONObject("quotes").getString("USDCHF")

                        when(currencyPair){
                            "USD-EUR"->{
                                currency2Value.value=calculateCurrency(currency1Enter.text, usd_eur)
                                otherCurrency1Value.value = calculateCurrency(currency1Enter.text, usd_gbp)
                                otherCurrency2Value.value = calculateCurrency(currency1Enter.text, usd_cad)
                                otherCurrency3Value.value = calculateCurrency(currency1Enter.text, usd_chf)
                            }
                            "USD-GBP"->{
                                currency2Value.value=calculateCurrency(currency1Enter.text, usd_gbp)
                                otherCurrency1Value.value = calculateCurrency(currency1Enter.text, usd_eur)
                                otherCurrency2Value.value = calculateCurrency(currency1Enter.text, usd_cad)
                                otherCurrency3Value.value = calculateCurrency(currency1Enter.text, usd_chf)
                            }
                            "USD-CAD"->{
                                currency2Value.value=calculateCurrency(currency1Enter.text, usd_cad)
                                otherCurrency1Value.value = calculateCurrency(currency1Enter.text, usd_eur)
                                otherCurrency2Value.value = calculateCurrency(currency1Enter.text, usd_gbp)
                                otherCurrency3Value.value = calculateCurrency(currency1Enter.text, usd_chf)
                            }
                            "USD-CHF"->{
                                currency2Value.value=calculateCurrency(currency1Enter.text, usd_chf)
                                otherCurrency1Value.value = calculateCurrency(currency1Enter.text, usd_eur)
                                otherCurrency2Value.value = calculateCurrency(currency1Enter.text, usd_gbp)
                                otherCurrency3Value.value = calculateCurrency(currency1Enter.text, usd_cad)
                            }
                        }
                    },
                    {
                            error ->
                        dialogState2.value=true
                    }
                )
                queue.add(stringRequest)
            } else {
                dialogState1.value=true
            }
        },
            modifier=Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor=green1
            )
        ){
            Text("Calculate")
        }
    }
}

fun calculateCurrency(currency: String, rate: String): String{
    val curr=currency.toFloat()
    val rt=rate.toFloat()
    val result = curr*rt
    val roundedResult=result.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toFloat()
    return roundedResult.toString()
}