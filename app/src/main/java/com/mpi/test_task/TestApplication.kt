package com.mpi.test_task

import android.app.Application
import com.mpi.test_task.services.di.*

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }


    companion object {

        private var component: ApplicationComponent? = null

        public val ServicesComponent: ApplicationComponent
            get() {
                if (component == null) {
                    component = DaggerApplicationComponent.builder()
                        .apiClientModule(ApiClientModule())
                        .synchronizationModule(SynchronizationModule())
                        .viewModelsModule(ViewModelsModule())
                        .build()
                }
                return component!!
            }

    }

}