package com.rahullohra.fakeresponse.presentaiton.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rahullohra.fakeresponse.presentaiton.fragments.HomeFragment

class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    val fragmentList :ArrayList<Fragment> = arrayListOf()
    val tabTitles :ArrayList<String> = arrayListOf()

    init {
        fragmentList.add(HomeFragment.newInstance())
        tabTitles.add("Records")
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    override fun getCount() = 1

}
