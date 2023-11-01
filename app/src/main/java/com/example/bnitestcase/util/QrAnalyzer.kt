package com.example.bnitestcase.util

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning.getClient
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

class QrAnalyzer(
    private val codeScanned: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val cameraExecutor = Executors.newSingleThreadExecutor()
    private val scanner = getClient(BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_QR_CODE).build())
    val analyzer = ImageAnalysis.Builder()
        .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
        .build().apply { setAnalyzer(cameraExecutor, this@QrAnalyzer) }

    @ExperimentalGetImage
    override fun analyze(image: ImageProxy) {
        image.image?.let {
            scanner.process(InputImage.fromMediaImage(it, image.imageInfo.rotationDegrees))
                .addOnSuccessListener { barcodes ->
                    barcodes.forEach { barcode ->
                        codeScanned(barcode.rawValue.orEmpty())
                    }
                }
                .addOnFailureListener { exception ->
                    codeScanned(exception.localizedMessage.orEmpty())
                }
                .addOnCompleteListener {
                    image.image?.close()
                    image.close()
                }
        } ?: image.close()
    }
}