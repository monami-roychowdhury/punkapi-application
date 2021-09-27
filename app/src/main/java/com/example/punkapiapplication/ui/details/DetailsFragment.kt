package com.example.punkapiapplication.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.punkapiapplication.R
import com.example.punkapiapplication.databinding.DetailsLayoutBinding
import com.example.punkapiapplication.ui.list.PunkDataViewModel

class DetailsFragment: Fragment(R.layout.details_layout) {
    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by activityViewModels<PunkDataViewModel>()
    private var set: HashSet<Int>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DetailsLayoutBinding.bind(view)
        set = viewModel.getItem()

        viewModel.mutableLiveData.observe(viewLifecycleOwner) {
            set = it
        }
        binding.apply {
            val punkData = args.punkData
            Glide.with(binding.root)
                .load(punkData.image_url)
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(android.R.color.darker_gray)
                .error(R.color.black)
                .into(imageView)
            textView.text = punkData.name
            if (set != null) {
                checkbox.isChecked = set!!.contains(punkData.id)
            } else {
                checkbox.isChecked = false
            }
            checkbox.setOnClickListener {

                if (set != null) {
                    if (checkbox.isChecked) {
                        set!!.add(punkData.id)
                    } else {
                        set!!.remove(punkData.id)
                    }
                    viewModel.setItem(set)
                } else {
                    if (checkbox.isChecked) {
                        val newSet = HashSet<Int>()
                        newSet.add(punkData.id)
                        viewModel.setItem(newSet)
                    }
                }
            }

        }
    }

}