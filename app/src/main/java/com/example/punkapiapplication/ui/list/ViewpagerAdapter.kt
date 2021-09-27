package com.example.punkapiapplication.ui.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.punkapiapplication.ui.list.Tab1Fragment
import com.example.punkapiapplication.ui.list.Tab2Fragment

class ViewpagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return Tab1Fragment()
        } else {
            return Tab2Fragment()
        }
    }
}