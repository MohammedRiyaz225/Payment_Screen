package com.example.payment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.payment.ui.theme.PaymentTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaymentTheme {
                PaymentScreen()
            }
        }
    }
}

@Composable
fun PaymentScreen() {
    var isProcessing by remember { mutableStateOf(false) }
    var statusMessage by remember { mutableStateOf("Ready to Pay") }

    LaunchedEffect(isProcessing) {
        if (isProcessing) {
            statusMessage = "Verifying payment..."
            delay(3000)

            statusMessage = "Transaction Complete"
            isProcessing = false
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = statusMessage,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (isProcessing) {
            CircularProgressIndicator()

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Payment processing...")

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    isProcessing = false
                    statusMessage = "Payment Cancelled"
                }
            ) {
                Text("Cancel Payment")
            }
        } else {
            Button(
                onClick = {
                    isProcessing = true
                }
            ) {
                Text("Pay Now")
            }
        }
    }
}