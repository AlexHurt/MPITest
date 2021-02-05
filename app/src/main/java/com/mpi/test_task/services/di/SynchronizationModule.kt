package com.mpi.test_task.services.di

import com.mpi.test_task.services.models.Acceptance
import com.mpi.test_task.services.models.ConfirmedPosition
import com.mpi.test_task.services.server.IApiClient
import com.mpi.test_task.services.synchronization.AcceptanceSynchronizationModel
import com.mpi.test_task.services.synchronization.ISynchronizationManager
import com.mpi.test_task.services.synchronization.ISynchronizationModel
import com.mpi.test_task.services.synchronization.StandardSynchronizationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SynchronizationModule {

    @Provides
    @Singleton
    fun provideConfirmedPositionSynchronizationModel(apiClient: IApiClient): ISynchronizationModel<Acceptance> {
        return AcceptanceSynchronizationModel(apiClient)
    }

    @Provides
    @Singleton
    fun provideSynchronizationManager(confirmedPositionSynchronizationModel: ISynchronizationModel<Acceptance>): ISynchronizationManager {
        return StandardSynchronizationManager(confirmedPositionSynchronizationModel)
    }

}