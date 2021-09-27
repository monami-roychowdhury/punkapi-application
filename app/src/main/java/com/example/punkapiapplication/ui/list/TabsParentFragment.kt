package com.example.punkapiapplication.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.punkapiapplication.R
import com.example.punkapiapplication.data.PunkData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabsParentFragment : Fragment(R.layout.tab_fragment_parent_layout), ItemClickListener {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = view.findViewById<ViewPager2>(R.id.pager)
        viewPager.adapter = ViewpagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if(position == 0) {
                tab.text = "Tab 1"
            } else {
                tab.text = "Tab 2"
            }

        }.attach()
    }

    override fun onItemClicked(punkData: PunkData) : PunkData {
        val action = TabsParentFragmentDirections.actionTabsParentFragmentToDetailsFragment(punkData)
        findNavController().navigate(action)
        return punkData
    }


}