package com.example.punkapiapplication.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.punkapiapplication.R
import com.example.punkapiapplication.data.PunkData
import com.example.punkapiapplication.databinding.ItemLayoutBinding

class PunkDataAdapter(
    private val listener: OnItemClickListener,
    private val tabNum: Int,
    private var updatedItemsSet: HashSet<Int>?
) :
    PagingDataAdapter<PunkData, PunkDataAdapter.PunkViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PunkViewHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PunkViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PunkViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PunkViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = getItem(position)
                        if (item != null) {
                            listener.onItemClick(item)
                        }
                    }
                }
                checkbox.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = getItem(position)
                        if (item != null) {
                            listener.onItemCheck(item, checkbox.isChecked, position)

                        }
                    }
                }
                checkbox2.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = getItem(position)
                        if (item != null) {
                            listener.onItemCheck(item, checkbox2.isChecked, position)
                        }
                    }
                }
            }
        }

        fun bind(punkData: PunkData) {
            binding.apply {
                if (tabNum == 1) {
                    firstTabLayout.visibility = View.VISIBLE
                    secondTabLayout.visibility = View.GONE
                    Glide.with(itemView)
                        .load(punkData.image_url)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .placeholder(android.R.color.darker_gray)
                        .error(R.color.black)
                        .into(imageView)
                    textView.text = punkData.name
                    if (updatedItemsSet != null) {
                        checkbox.isChecked = updatedItemsSet!!.contains(punkData.id)
                    } else {
                        checkbox.isChecked = false
                    }
                    // checkbox.isChecked = punkData.checked
                } else {
                    firstTabLayout.visibility = View.GONE
                    secondTabLayout.visibility = View.VISIBLE
                    Glide.with(itemView)
                        .load(punkData.image_url)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .placeholder(android.R.color.darker_gray)
                        .error(R.color.black)
                        .into(imageView2)
                    textView2.text = punkData.name
                    if (updatedItemsSet != null) {
                        checkbox2.isChecked = updatedItemsSet!!.contains(punkData.id)
                    } else {
                        checkbox2.isChecked = false
                    }
                }
            }

        }


    }

    fun update(set: HashSet<Int>?) {
        updatedItemsSet = set
        notifyDataSetChanged()

    }

    interface OnItemClickListener {
        fun onItemClick(punkData: PunkData)
        fun onItemCheck(punkData: PunkData, isChecked: Boolean, position: Int)
    }


    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<PunkData>() {

            override fun areItemsTheSame(oldItem: PunkData, newItem: PunkData) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PunkData, newItem: PunkData) =
                oldItem == newItem

        }
    }


}