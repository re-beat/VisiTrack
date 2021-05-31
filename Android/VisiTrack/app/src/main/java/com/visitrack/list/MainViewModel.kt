package com.visitrack.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Statistics
import com.visitrack.core.domain.model.Violation
import com.visitrack.core.domain.usecase.UseCase

class MainViewModel (private val useCase: UseCase): ViewModel() {

    fun getViolationStatistic() : LiveData<Resource<Statistics>> =
        useCase.getStatistics().asLiveData()

    fun getListNotification() : LiveData<Resource<List<Violation>>> =
        useCase.getNotificationList().asLiveData()

    fun getListCamera() : LiveData<Resource<List<Camera>>> =
        useCase.getCameraList().asLiveData()
}