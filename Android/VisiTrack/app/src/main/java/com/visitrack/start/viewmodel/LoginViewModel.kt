package com.visitrack.start.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.User
import com.visitrack.core.domain.usecase.UseCase

class LoginViewModel(private val useCase: UseCase) : ViewModel() {

    fun getLogin() : LiveData<Resource<User>> =
        useCase.login().asLiveData()

}