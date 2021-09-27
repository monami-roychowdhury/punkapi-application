package com.example.punkapiapplication.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.punkapiapplication.R
import com.example.punkapiapplication.data.PunkData
import com.example.punkapiapplication.databinding.TabFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Tab1Fragment : Fragment(), PunkDataAdapter.OnItemClickListener {

    private val viewModel by activityViewModels<PunkDataViewModel>()
    private var _binding: TabFragmentLayoutBinding? = null
    private val binding get() = _binding!!
    private var updatePosition: Int = 0
    private lateinit var itemClickListener: ItemClickListener


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = TabFragmentLayoutBinding.bind(view)
        val adapter = PunkDataAdapter(this, 1, viewModel.getItem())
        binding.apply {
            recyclerView.hasFixedSize()
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter
        }





        viewModel.results.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        viewModel.mutableLiveData.observe(viewLifecycleOwner) {
            adapter.update(it);
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (parentFragment is ItemClickListener)
            itemClickListener = parentFragment as ItemClickListener

    }


    override fun onItemClick(punkData: PunkData) {
        itemClickListener.onItemClicked(punkData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemCheck(punkData: PunkData, isChecked: Boolean, position: Int) {
        updatePosition = position
        val set = viewModel.getItem()
        if (set != null) {
            if (isChecked) {
                set.add(punkData.id)
            } else {
                set.remove(punkData.id)
            }
            viewModel.setItem(set)
        } else {
            if (isChecked) {
                val newSet = HashSet<Int>()
                newSet.add(punkData.id)
                viewModel.setItem(newSet)
            }
        }


    }


}