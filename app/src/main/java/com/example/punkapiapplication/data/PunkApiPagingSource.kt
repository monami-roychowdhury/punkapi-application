package com.example.punkapiapplication.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.punkapiapplication.api.PunkApi
import retrofit2.HttpException

private const val STARTING_PAGE = 1

class PunkApiPagingSource(
    private val punkApi: PunkApi
) : PagingSource<Int, PunkData>() {


    override fun getRefreshKey(state: PagingState<Int, PunkData>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PunkData> {
        val position = params.key ?: STARTING_PAGE
        return try {
            val response = punkApi.fetchData(position, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1

            )
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}