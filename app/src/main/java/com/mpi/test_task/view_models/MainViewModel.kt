package com.mpi.test_task.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mpi.test_task.TestApplication
import com.mpi.test_task.services.models.Acceptance
import com.mpi.test_task.services.models.ConfirmedPosition
import com.mpi.test_task.services.synchronization.AcceptanceSynchronizationModel
import com.mpi.test_task.services.synchronization.ISynchronizationManager
import com.mpi.test_task.services.synchronization.ISynchronizationModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var synchronizationManager: ISynchronizationManager

    init {
        TestApplication.ServicesComponent.inject(this)
    }

    val isBusy: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val data: MutableLiveData<Acceptance> = MutableLiveData()

    val currentElementIndex: MutableLiveData<Int> = MutableLiveData()
    val currentElement: MutableLiveData<ConfirmedPosition> = MutableLiveData()

    @Suppress("UNCHECKED_CAST")
    fun loadData() {
        isBusy.value = true
        GlobalScope.launch {
            synchronizationManager.synchronizeAll()
            isBusy.postValue(false)
            val syncModel = synchronizationManager.getSynchronizationModel((AcceptanceSynchronizationModel::class.java))
            if (syncModel == null) {
                isSuccess.postValue(false)
                data.postValue(null)
            } else {
                isSuccess.postValue(syncModel.isSuccess)
                data.postValue((syncModel as? ISynchronizationModel<Acceptance>)?.data)
            }
        }
    }

    fun setCurrentElement(index: Int) {
        if (data.value == null || index < 0 || index >= data.value!!.items.size) {
            currentElementIndex.value = -1
            currentElement.value = null
        } else {
            currentElementIndex.value = index
            currentElement.value = data.value!!.items[index]
        }
    }

}