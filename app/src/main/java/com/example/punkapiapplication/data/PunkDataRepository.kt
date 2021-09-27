package com.example.punkapiapplication.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.punkapiapplication.api.PunkApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PunkDataRepository @Inject constructor(private val punkApi: PunkApi) {

    fun fetchApiData() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PunkApiPagingSource(punkApi) }
        ).flow

}