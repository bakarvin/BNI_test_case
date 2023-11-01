package com.example.bnitestcase.entity

data class Response (
    val type: String,
    val data: DataUnion
)

sealed class DataUnion() {
    class LineChart(val value: TransactionPeriod) : DataUnion()
    class DonutChart(val value: List<TransactionType>) : DataUnion()
}

data class TransactionType (
    val label: String,
    val percentage: String,
    val data: List<TransactionDetail>
)

data class TransactionDetail (
    val trxDate: String,
    val nominal: Long
)

data class TransactionPeriod (
    val month: List<Long>
)

//class ResponseChart : List<ResponseItem> {
//    data class ResponseItem(
//        @SerializedName("type")
//        val type: String = "",
//        @SerializedName("data")
//        val `data`: List<Data> = listOf()
//    ) {
//        data class Data(
//            @SerializedName("label")
//            val label: String = "",
//            @SerializedName("percentage")
//            val percentage: String = "",
//            @SerializedName("data")
//            val `data`: List<Data> = listOf()
//        ) {
//            data class Data(
//                @SerializedName("trx_date")
//                val trxDate: String = "",
//                @SerializedName("nominal")
//                val nominal: Int = 0
//            )
//        }
//    }
//}