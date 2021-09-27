package com.example.punkapiapplication.ui.list

import com.example.punkapiapplication.data.PunkData
import dagger.Binds
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ItemClickListener {

    @Binds
    public fun onItemClicked(punkData: PunkData) : PunkData
}