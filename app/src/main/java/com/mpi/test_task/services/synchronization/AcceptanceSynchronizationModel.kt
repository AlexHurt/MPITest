package com.mpi.test_task.services.synchronization

import com.mpi.test_task.services.models.Acceptance
import com.mpi.test_task.services.server.IApiClient
import java.util.*

class AcceptanceSynchronizationModel(private val apiClient: IApiClient) : ISynchronizationModel<Acceptance> {

    override suspend fun synchronize() {
        val response = apiClient.getConfirmedPositions()
        if (response == null) {
            isSuccess = false
        } else {
            data = response
            isSuccess = true
            lastSuccessSynchronizationTime = Date()
        }
    }

    override var data: Acceptance? = null
        private set
    override var lastSuccessSynchronizationTime: Date = Date(0)
        private set
    override var isSuccess: Boolean = false
        private set

}