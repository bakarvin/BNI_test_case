package com.example.bnitestcase.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bnitestcase.ui.screen.component.CustomDialog
import com.example.bnitestcase.entity.QrEntity
import com.example.bnitestcase.ui.screen.destinations.HomeRouteDestination
import com.example.bnitestcase.ui.screen.viewmodel.QrConfirmationViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun QrConfirmationRoute(
    navigator: DestinationsNavigator,
    payload: String,
    viewModel: QrConfirmationViewModel = hiltViewModel()
) {
    val showDialog =  remember { mutableStateOf(false) }
    val data = viewModel.map(payload)
    QrConfirmationScreen(
        payload = data,
        onConfirmClick = {
            showDialog.value = true
            viewModel.saveUpdateHistory(data.nominalTransaksi, data)
        },
        onCancelClick = {
            navigator.navigate(HomeRouteDestination)
        }
    )
    if (showDialog.value) {
        CustomDialog(
            payload = data,
            onConfirmClick = {
                navigator.navigate(HomeRouteDestination)
            })
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrConfirmationScreen(
    payload: QrEntity,
    onConfirmClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .padding(24.dp)
                .border(
                    border = BorderStroke(2.dp, Blue),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .padding(
                        horizontal = 12.dp,
                        vertical = 20.dp
                    )
            ) {
                Text(
                    text = "Konfirmasi Pembayaran",
                    fontSize = 22.sp,
                    fontWeight = Medium
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = SpaceBetween
                ) {
                    Text(
                        text = "ID Transaksi",
                        fontSize = 14.sp,
                        fontWeight = Medium
                    )
                    Text(
                        text = payload.idTransaksi,
                        fontSize = 16.sp,
                        fontWeight = Medium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = SpaceBetween
                ) {
                    Text(
                        text = "Nama Merchant",
                        fontSize = 14.sp,
                        fontWeight = Medium
                    )
                    Text(
                        text = payload.nameMerchant,
                        fontSize = 16.sp,
                        fontWeight = Medium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = SpaceBetween
                ) {
                    Text(
                        text = "Nominal Transaksi",
                        fontSize = 14.sp,
                        fontWeight = Medium
                    )
                    Text(
                        text = payload.nominalTransaksi,
                        fontSize = 16.sp,
                        fontWeight = Medium
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Card(
                    shape = RoundedCornerShape(size = 12.dp),
                    border = BorderStroke(1.dp, color = Blue),
                    colors = CardDefaults.cardColors(
                        containerColor = Blue,
                        contentColor = White
                    ),
                    onClick = { onConfirmClick() }
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 10.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Konfirmasi Pembayaran",
                            fontSize = 16.sp,
                            fontWeight = Medium
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    shape = RoundedCornerShape(size = 12.dp),
                    border = BorderStroke(1.dp, color = Blue),
                    colors = CardDefaults.cardColors(
                        containerColor = White,
                        contentColor = Blue
                    ),
                    onClick = { onCancelClick() }
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 10.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Batalkan Pembayaran",
                            fontSize = 16.sp,
                            fontWeight = Medium
                        )
                    }
                }
            }
        }
    }
}
