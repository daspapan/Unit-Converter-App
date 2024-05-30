package com.example.currencyconverterapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.currencyconverterapp.ui.theme.CurrencyConverterAppTheme
import kotlin.math.roundToInt

// https://www.youtube.com/watch?v=D1b0Hv7G5AU&pp=ygUSa290bGluIGFkZCBqZXRwYWNr
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    val inputConversionFactor = remember { mutableStateOf(1.00) }
    val outputConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * inputConversionFactor.value * 100.0/ outputConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Currency Converter App",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            label = { Text(text = "Enter a Value")}
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row() {

            // val context = LocalContext.current
            // Toast.makeText(context, "Thanks for clicking!", Toast.LENGTH_LONG).show()
            Box() {
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(
                    expanded = iExpanded,
                    onDismissRequest = { iExpanded = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            inputConversionFactor.value = 0.01
                            convertUnits()
                        }
                    ) { Text(text = "Centimeters")}

                    DropdownMenuItem(
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            inputConversionFactor.value = 1.0
                            convertUnits()
                        }
                    ) { Text(text = "Meters")}

                    DropdownMenuItem(
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            inputConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    ) { Text(text = "Feet")}

                    DropdownMenuItem(
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milimeters"
                            inputConversionFactor.value = 0.001
                            convertUnits()
                        }
                    ) { Text(text = "Milimeters")}

                }
            }

            Spacer(modifier = Modifier.width(24.dp))

            Box() {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(
                    expanded = oExpanded,
                    onDismissRequest = { oExpanded = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            outputConversionFactor.value = 0.01
                            convertUnits()
                        }
                    ) { Text(text = "Centimeters")}

                    DropdownMenuItem(
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            outputConversionFactor.value = 1.0
                            convertUnits()
                        }
                    ) { Text(text = "Meters")}

                    DropdownMenuItem(
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            outputConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    ) { Text(text = "Feet")}

                    DropdownMenuItem(
                        onClick = {
                            oExpanded = false
                            outputUnit = "Milimeters"
                            outputConversionFactor.value = 0.001
                            convertUnits()
                        }
                    ) { Text(text = "Milimeters")}

                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Result:${inputValue} ${inputUnit} is ${outputValue} ${outputUnit}",
            style = MaterialTheme.typography.h5
        )


    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}


