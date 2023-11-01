package com.example.bnitestcase.ui.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.bnitestcase.entity.QrEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(
    payload: QrEntity,
    onConfirmClick: (Boolean) -> Unit
) {
    Dialog(
        onDismissRequest = {}
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = White
        ) {
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .padding(
                        horizontal = 12.dp,
                        vertical = 20.dp
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "",
                    tint = Green,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Pembayaran Sukses",
                    fontSize = 22.sp,
                    fontWeight = Medium
                )
                Spacer(modifier = Modifier.height(30.dp))
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
                    colors = cardColors(
                        containerColor = Blue,
                        contentColor = White
                    ),
                    onClick = { onConfirmClick(false) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 20.dp,
                                vertical = 10.dp
                            ),
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Center
                    ) {
                        Text(
                            text = "Kembali ke Home",
                            fontSize = 16.sp,
                            fontWeight = Medium
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}