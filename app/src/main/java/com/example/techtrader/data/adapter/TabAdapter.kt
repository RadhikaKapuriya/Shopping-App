package com.example.techtrader.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.techtrader.view.FragmentLaptop
import com.example.techtrader.view.FragmentMobile
import com.example.techtrader.view.HomeActivity


class TabAdapter(homeActivity: HomeActivity, supportFragmentManager: FragmentManager, tabCount: Int)
    : FragmentPagerAdapter(supportFragmentManager) {
    val tabcount: Int
    init {
        tabcount = tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentMobile()
            }
            1 -> {
                FragmentLaptop()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return tabcount;
    }

}
