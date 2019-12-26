package com.rahullohra.fakeresponse.presentaiton.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahullohra.fakeresponse.ResponseListData
import com.rahullohra.fakeresponse.data.di.modules.DispatcherModule
import com.rahullohra.fakeresponse.db.entities.Gql
import com.rahullohra.fakeresponse.domain.usecases.ShowGqlUseCase
import com.rahullohra.fakeresponse.presentaiton.livedata.Fail
import com.rahullohra.fakeresponse.presentaiton.livedata.LiveDataResult
import com.rahullohra.fakeresponse.presentaiton.livedata.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class FakeResponseModel @Inject constructor(
    @Named(DispatcherModule.WORKER)
    val workerDispatcher: CoroutineDispatcher,
    val showGqlUseCase: ShowGqlUseCase
) : ViewModel(), CoroutineScope {

    val liveData = MutableLiveData<LiveDataResult<List<ResponseListData>>>()

    override val coroutineContext: CoroutineContext
        get() = workerDispatcher + ceh

    private val ceh = CoroutineExceptionHandler { _, ex ->
        liveData.postValue(Fail(ex))
        ex.printStackTrace()
    }

    fun getGql() {
        launch {
            liveData.postValue(Success(showGqlUseCase.getAllQueries()))
        }
    }

}
