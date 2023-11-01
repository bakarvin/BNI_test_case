package com.example.bnitestcase.ui.screen

import android.Manifest
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.layout.ContentScale.Companion.Inside
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bnitestcase.R
import com.example.bnitestcase.ui.screen.destinations.QrScannerRouteDestination
import com.example.bnitestcase.ui.screen.viewmodel.HomeViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalPermissionsApi::class)
@Destination(start = true)
@Composable
fun HomeRoute(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) = with(viewModel) {
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA) {
        navigator.navigate(QrScannerRouteDestination)
    }
    HomeScreen(
        balance = balance,
        onQrClick = {
            when (cameraPermission.status) {
                is PermissionStatus.Granted -> {
                    navigator.navigate(QrScannerRouteDestination)
                }
                else -> {
                    cameraPermission.launchPermissionRequest()
                }
            }
        },
        onPortoClick = {
            loadHistory()
        },
        onRiwayatClick = {}
    )
}

@Composable
fun HomeScreen(
    balance: String,
    onQrClick: () -> Unit,
    onPortoClick: () -> Unit,
    onRiwayatClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            BalanceSection(saldoBalance = balance)
            Spacer(modifier = Modifier.height(50.dp))
            ContentSection(
                onQrClick = onQrClick,
                onPortoClick = onPortoClick,
                onRiwayatClick = onRiwayatClick
            )
        }
    }
}

@Composable
fun BalanceSection(
    saldoBalance: String
) {
    Column(
        verticalArrangement = spacedBy(6.dp)
    ) {
        Text(
            text = "Total saldo yang kamu miliki",
            fontSize = 20.sp,
            fontWeight = Medium
        )
        Text(
            text = "Rp $saldoBalance",
            fontSize = 20.sp,
            fontWeight = Medium,
            color = Blue
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentSection(
    onQrClick: () -> Unit,
    onPortoClick: () -> Unit,
    onRiwayatClick: () -> Unit
) {
    Column(
        verticalArrangement = spacedBy(12.dp)
    ) {
        Text(
            text = "Layanan Favorit",
            fontSize = 22.sp,
            fontWeight = Medium
        )
        Card(
            shape = RoundedCornerShape(size = 40.dp),
            border = BorderStroke(1.dp, color = Blue),
            colors = CardDefaults.cardColors(containerColor = White),
            onClick = { onQrClick() }
        ) {
            Row(
                modifier = Modifier.padding(all = 4.dp),
                verticalAlignment = CenterVertically,
                horizontalArrangement = spacedBy(2.dp)
            ) {
                Box(modifier = Modifier
                    .padding(4.dp)
                    .background(Blue, shape = CircleShape),
                    contentAlignment = Center
                ) {
                    Image(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(size = 50.dp),
                        painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                        contentDescription = "",
                        contentScale = Inside,
                        colorFilter = tint(color = White)
                    )
                }
                Spacer(modifier = Modifier.width(width = 8.dp)) // gap between image and text
                Text(
                    text = "QR",
                    fontSize = 16.sp,
                    fontWeight = Medium
                )
                Spacer(modifier = Modifier.width(width = 100.dp))
            }
        }
        Card(
            shape = RoundedCornerShape(size = 40.dp),
            border = BorderStroke(1.dp, color = Blue),
            colors = CardDefaults.cardColors(containerColor = White),
            onClick = { onPortoClick() }
        ) {
            Row(
                modifier = Modifier.padding(all = 4.dp),
                verticalAlignment = CenterVertically,
                horizontalArrangement = spacedBy(2.dp)
            ) {
                Box(modifier = Modifier
                    .padding(4.dp)
                    .background(Blue, shape = CircleShape),
                    contentAlignment = Center
                ) {
                    Image(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(size = 50.dp),
                        painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                        contentDescription = "",
                        contentScale = Inside,
                        colorFilter = tint(color = White)
                    )
                }
                Spacer(modifier = Modifier.width(width = 8.dp)) // gap between image and text
                Text(
                    text = "Portofolio",
                    fontSize = 16.sp,
                    fontWeight = Medium
                )
                Spacer(modifier = Modifier.width(width = 100.dp))
            }
        }
        Card(
            shape = RoundedCornerShape(size = 40.dp),
            border = BorderStroke(1.dp, color = Blue),
            colors = CardDefaults.cardColors(containerColor = White),
            onClick = { onRiwayatClick() }
        ) {
            Row(
                modifier = Modifier.padding(all = 4.dp),
                verticalAlignment = CenterVertically,
                horizontalArrangement = spacedBy(2.dp)
            ) {
                Box(modifier = Modifier
                    .padding(4.dp)
                    .background(Blue, shape = CircleShape),
                    contentAlignment = Center
                ) {
                    Image(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(size = 50.dp),
                        painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                        contentDescription = "",
                        contentScale = Inside,
                        colorFilter = tint(color = White)
                    )
                }
                Spacer(modifier = Modifier.width(width = 8.dp)) // gap between image and text
                Text(
                    text = "Riwayat Transaksi",
                    fontSize = 16.sp,
                    fontWeight = Medium
                )
                Spacer(modifier = Modifier.width(width = 100.dp))
            }
        }
    }
}
