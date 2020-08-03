package com.example.mynotifactiondemo.data.repository

import androidx.paging.PagingSource
import com.example.mynotifactiondemo.data.api.ApiClientInterface
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsRequestDto
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsRequestFiltersDto
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseDto
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseItemDto
import retrofit2.HttpException
import java.io.IOException

class MyTransportationsPagingSource(
    private val apiClient: ApiClientInterface
) : PagingSource<Int, MyTransportationsResponseItemDto>() {

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyTransportationsResponseItemDto> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val requestDto = MyTransportationsRequestDto(
            from = (position - 1) * params.loadSize + 1,
            to = position * params.loadSize,
            filters = MyTransportationsRequestFiltersDto(
                isActualOnly = true,
                statuses = listOf(1, 2, 3),
                search = ""
            )
        )

        return try {
            val response = apiClient.getMyTransportations(requestDto)
            LoadResult.Page(
                data = response.items.toList(),
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.items.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}