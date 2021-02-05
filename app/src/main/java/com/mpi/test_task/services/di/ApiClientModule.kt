package com.mpi.test_task.services.di

import com.mpi.test_task.services.server.MockApiClient
import com.mpi.test_task.services.server.IApiClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiClientModule {

    @Provides
    @Singleton
    public fun provideApiClient(): IApiClient {
        return MockApiClient()
    }

}