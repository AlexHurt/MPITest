package com.mpi.test_task.services.models

data class Acceptance(
    val acceptNumber: String,
    val items: List<ConfirmedPosition>
)