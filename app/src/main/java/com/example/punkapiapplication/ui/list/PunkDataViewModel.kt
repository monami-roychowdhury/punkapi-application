package com.example.punkapiapplication.ui.list

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.punkapiapplication.data.PunkDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PunkDataViewModel @Inject constructor(
    private val repository: PunkDataRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    val mutableLiveData = MutableLiveData<HashSet<Int>>()

    fun getItem(): HashSet<Int>? {
        return mutableLiveData.getValue()
    }

    fun setItem(item: HashSet<Int>?) {
        mutableLiveData.setValue(item!!)
    }


    private val currentPage = state.getLiveData(CURRENT_PAGE, DEFAULT_PAGE)
    val results = currentPage.switchMap { it ->
        repository.fetchApiData()
            .cachedIn(viewModelScope)
            .asLiveData()
    }


    companion object {
        private const val CURRENT_PAGE = "CURRENT_PAGE"
        private const val DEFAULT_PAGE = 1
    }

}