package com.mpi.test_task.services.synchronization

import java.util.*

interface ISynchronizationManager {
    suspend fun synchronizeAll()
    fun <T> getSynchronizationModel(modelType: Class<T>): ISynchronizationModel<*>?
    val lastSuccessSynchronizationTime: Date
    val isSuccess: Boolean
}