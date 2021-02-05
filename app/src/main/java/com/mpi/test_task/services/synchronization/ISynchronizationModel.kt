package com.mpi.test_task.services.synchronization

import java.util.*

interface ISynchronizationModel<T> {
    suspend fun synchronize()
    val data: T?
    val lastSuccessSynchronizationTime: Date
    val isSuccess: Boolean
}