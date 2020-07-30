package com.example.mynotifactiondemo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsRequestDto
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsRequestFiltersDto
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseDto
import com.example.mynotifactiondemo.data.repository.MyTransportationsRepository
import com.example.mynotifactiondemo.viewmodel.model.ViewModelResult
import kotlinx.coroutines.launch

class MyTransportationsViewModel @ViewModelInject constructor(
    private val myTransportationsRepository: MyTransportationsRepository
) : ViewModel() {

    private val _myTransportations = MutableLiveData<ViewModelResult<MyTransportationsResponseDto>>()
    val myTransportations: LiveData<ViewModelResult<MyTransportationsResponseDto>>
        get() = _myTransportations

    init {
        fetchMyTransportations()
    }

    private fun fetchMyTransportations() {
        viewModelScope.launch {
            _myTransportations.postValue(ViewModelResult.loading())
            try {
                val requestDto = MyTransportationsRequestDto(
                    from = 1,
                    to = 30,
                    filters = MyTransportationsRequestFiltersDto(
                        isActualOnly = true,
                        statuses = listOf(1, 2, 3),
                        search = ""
                    )
                )
                myTransportationsRepository.getMyTransportations(requestDto).let {
                    _myTransportations.postValue(ViewModelResult.success(it))

                }
            } catch (throwable: Throwable) {
                _myTransportations.postValue(ViewModelResult.failure(throwable))
            }
        }
    }
}