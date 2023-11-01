package com.example.bnitestcase.ui.screen

import android.view.ViewGroup
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.FocusMeteringAction
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceOrientedMeteringPointFactory
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bnitestcase.ui.screen.destinations.QrConfirmationRouteDestination
import com.example.bnitestcase.util.QrAnalyzer
import com.example.bnitestcase.ui.screen.viewmodel.QrScannerViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun QrScannerRoute(
    navigator: DestinationsNavigator,
    viewModel: QrScannerViewModel = hiltViewModel()
) {
    QrScannerScreen(onSuccess = {
        navigator.navigate(QrConfirmationRouteDestination(it))
    })
}

@Composable
fun QrScannerScreen(
    onSuccess: (String) -> Unit
) {
    val imageAnalyzer = remember {
        QrAnalyzer { qrPayload ->
            onSuccess(qrPayload)
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(imageAnalysis = imageAnalyzer.analyzer)
    }
}

@Composable
fun CameraPreview(
    imageAnalysis: ImageAnalysis? = null
) {
    val context = LocalContext.current
    val lifeCyleOwner = LocalLifecycleOwner.current

    val preview = remember { Preview.Builder().build() }
    val cameraController = remember { LifecycleCameraController(context) }
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val cameraProvider = remember { cameraProviderFuture.get() }
    var camera: Camera? = null

    LaunchedEffect(cameraProvider) {
        cameraProviderFuture.addListener({
            cameraController.bindToLifecycle(lifeCyleOwner)
            cameraProvider.unbindAll()
            camera = cameraProvider.bindToLifecycle(
                lifeCyleOwner,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                imageAnalysis
            )
        }, ContextCompat.getMainExecutor(context))
    }

    DisposableEffect(Unit) { onDispose { cameraProvider?.unbindAll() } }

    AndroidView(
        factory = {
            PreviewView(context).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                preview.setSurfaceProvider(surfaceProvider)
            }
        },
        modifier = Modifier
            .pointerInput(camera) {
                detectTapGestures {
                    cameraController.cameraControl?.startFocusAndMetering(
                        FocusMeteringAction.Builder(
                            SurfaceOrientedMeteringPointFactory(
                                size.width.toFloat(),
                                size.height.toFloat()
                            ).createPoint(it.x, it.y),
                            FocusMeteringAction.FLAG_AF
                        ).disableAutoCancel().build()
                    )
                }
            }
    )
}