package com.example.mynotifactiondemo.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseItemDto
import com.example.mynotifactiondemo.data.repository.MyTransportationsRepository
import kotlinx.coroutines.flow.Flow

class MyTransportationsViewModel @ViewModelInject constructor(
    private val myTransportationsRepository: MyTransportationsRepository
) : ViewModel() {

    val myTransportations: Flow<PagingData<MyTransportationsResponseItemDto>> =
        myTransportationsRepository.getMyTransportations().cachedIn(viewModelScope)
}