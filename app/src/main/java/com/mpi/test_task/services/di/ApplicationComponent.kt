package com.mpi.test_task.services.di

import com.mpi.test_task.view.activity.DetailsActivity
import com.mpi.test_task.view.activity.MainActivity
import com.mpi.test_task.view_models.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiClientModule::class, SynchronizationModule::class, ViewModelsModule::class])
interface ApplicationComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(mainActivity: MainActivity)
    fun inject(detailsActivity: DetailsActivity)
}