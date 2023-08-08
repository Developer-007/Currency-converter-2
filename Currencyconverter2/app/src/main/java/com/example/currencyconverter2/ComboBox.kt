package com.example.currencyconverter2

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currencyconverter2.ui.theme.green1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBox(context: Context){
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var currencyPair by remember {
        mutableStateOf("USD-EUR")
    }

    Column(modifier=Modifier.padding(5.dp).fillMaxWidth()){
        ExposedDropdownMenuBox(expanded = isExpanded,
            onExpandedChange = {isExpanded = it}) {
            TextField(value = currencyPair,
                onValueChange = {},
                readOnly=true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    textColor = Color.White,
                    containerColor=green1,
                    focusedTrailingIconColor = Color.White,
                    unfocusedTrailingIconColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().menuAnchor()

            )
            ExposedDropdownMenu(expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(text = { Text("USD-EUR") }, onClick = {
                    currencyPair="USD-EUR"
                    isExpanded = false
                })
                DropdownMenuItem(text = { Text("USD-GBP") }, onClick = {
                    currencyPair="USD-GBP"
                    isExpanded = false
                })
                DropdownMenuItem(text = { Text("USD-CAD") }, onClick = {
                    currencyPair="USD-CAD"
                    isExpanded = false
                })
                DropdownMenuItem(text = { Text("USD-CHF") }, onClick = {
                    currencyPair="USD-CHF"
                    isExpanded = false
                })
            }
        }
        if(currencyPair!="Choose currency"){
            Spacer(modifier=Modifier.height(7.dp))
            MainScreen(currencyPair, context)
        }
    }
}