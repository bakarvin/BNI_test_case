package com.example.bnitestcase.entity

data class ListHistory(
    val data: List<QrEntity>
)

data class QrEntity(
    val bankSumber: String,
    val idTransaksi: String,
    val nameMerchant: String,
    val nominalTransaksi: String
)

fun mapToQrEntity(data: String) : QrEntity {
    val list = data.split(".").toList()
    return QrEntity(
        bankSumber = list[0],
        idTransaksi = list[1],
        nameMerchant = list[2],
        nominalTransaksi = list[3]
    )
}