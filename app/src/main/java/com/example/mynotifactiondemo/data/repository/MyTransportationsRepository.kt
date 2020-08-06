package com.example.mynotifactiondemo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mynotifactiondemo.data.api.ApiClientInterface
import com.example.mynotifactiondemo.data.api.dto.MyTransportationAcceptRequestDto
import com.example.mynotifactiondemo.data.api.dto.MyTransportationRejectRequestDto
import com.example.mynotifactiondemo.data.api.dto.MyTransportationResponseDto
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseItemDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyTransportationsRepository @Inject constructor(
    private val apiClient: ApiClientInterface
) {
    companion object {
        const val PAGE_SIZE = 10
    }

    fun getMyTransportations(): Flow<PagingData<MyTransportationsResponseItemDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MyTransportationsPagingSource(apiClient) }
        ).flow
    }

    suspend fun getMyTransportation(id: String): MyTransportationResponseDto {
        return apiClient.getMyTransportation(id)
    }

    suspend fun rejectMyTransportation(id: String) {
        return apiClient.rejectMyTransportation(MyTransportationRejectRequestDto(documentId = id))
    }
    suspend fun acceptMyTransportation(id: String, code: String): MyTransportationResponseDto {
        val requestDto = MyTransportationAcceptRequestDto(
            documentId = id,
            code = code
        )
        return apiClient.acceptMyTransportation(requestDto)
    }
}