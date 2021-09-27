package com.example.punkapiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.findNavController()

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

//        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
//        val viewPager = findViewById<ViewPager2>(R.id.pager)
//        viewPager.adapter = ViewpagerAdapter(supportFragmentManager, lifecycle)
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            if(position == 0) {
//                tab.text = "Tab 1"
//            } else {
//                tab.text = "Tab 2"
//            }
//
//        }.attach()




    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}