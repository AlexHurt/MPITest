package com.mpi.test_task.services.models

import java.util.*

data class ConfirmedPosition(
    val marketPlaceName: String,
    val storageLevelName: String,
    val quantity: Int,
    val unit: String,
    val numberPending: Int,
    val numberReal: Int,
    val numberAccepted: Int,
    val productName: String,
    val productDescription: String,
    val productImageData: String,
    val serialNumber: String,
    val acceptedDate: Date
)
