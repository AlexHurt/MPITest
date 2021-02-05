package com.mpi.test_task.services.server

import com.mpi.test_task.services.models.Acceptance

interface IApiClient {

    suspend fun getConfirmedPositions() : Acceptance?

}