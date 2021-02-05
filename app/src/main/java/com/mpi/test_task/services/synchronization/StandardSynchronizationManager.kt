package com.mpi.test_task.services.synchronization

import com.mpi.test_task.services.models.Acceptance
import com.mpi.test_task.services.models.ConfirmedPosition
import java.util.*

class StandardSynchronizationManager(private val confirmedPositionSynchronizationModel: ISynchronizationModel<Acceptance>) : ISynchronizationManager {

    override suspend fun synchronizeAll() {
        confirmedPositionSynchronizationModel.synchronize()
        isSuccess = confirmedPositionSynchronizationModel.isSuccess
        if (confirmedPositionSynchronizationModel.isSuccess) {
            lastSuccessSynchronizationTime = Date()
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> getSynchronizationModel(modelType: Class<T>): ISynchronizationModel<*>? {
        return when (modelType) {
            confirmedPositionSynchronizationModel.javaClass -> confirmedPositionSynchronizationModel
            else -> null
        }
    }

    override var lastSuccessSynchronizationTime: Date = Date(0)
        private set
    override var isSuccess: Boolean = false
        private set
}