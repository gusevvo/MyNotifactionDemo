package com.example.mynotifactiondemo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotifactiondemo.data.api.dto.MyTransportationResponseDto
import com.example.mynotifactiondemo.data.repository.MyTransportationsRepository
import com.example.mynotifactiondemo.viewmodel.model.ViewModelResult
import kotlinx.coroutines.launch

class MyTransportationViewModel @ViewModelInject constructor(
    private val myTransportationsRepository: MyTransportationsRepository
) : ViewModel() {

    private val _myTransportation = MutableLiveData<ViewModelResult<MyTransportationResponseDto>>()
    val myTransportation: LiveData<ViewModelResult<MyTransportationResponseDto>>
        get() = _myTransportation

    init {
        _myTransportation.value = ViewModelResult.loading()
    }

    fun getMyTransportation(id: String) {
        viewModelScope.launch {
            _myTransportation.postValue(ViewModelResult.loading())
            try {
                val dto = myTransportationsRepository.getMyTransportation(id)
                _myTransportation.postValue(ViewModelResult.success(dto))
            } catch (throwable: Throwable) {
                _myTransportation.postValue(ViewModelResult.failure(throwable))
            }
        }
    }
}