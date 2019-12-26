package com.rahullohra.fakeresponse.presentaiton.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.rahullohra.fakeresponse.App
import com.rahullohra.fakeresponse.R
import com.rahullohra.fakeresponse.Router
import com.rahullohra.fakeresponse.data.di.component.DaggerHomeScreenComponent
import com.rahullohra.fakeresponse.presentaiton.adapters.PagerAdapter
import com.rahullohra.fakeresponse.presentaiton.livedata.Fail
import com.rahullohra.fakeresponse.presentaiton.livedata.Loading
import com.rahullohra.fakeresponse.presentaiton.livedata.Success
import com.rahullohra.fakeresponse.presentaiton.viewmodels.FakeResponseModel
import javax.inject.Inject

class FakeResponseFragment : BaseFragment() {

    companion object {
        fun newInstance() = FakeResponseFragment()
    }

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    lateinit var fab: FloatingActionButton

    private lateinit var pagerAdapter: PagerAdapter

    override fun getLayout() = R.layout.fake_response_fragment

    override fun initVars() {
        pagerAdapter = PagerAdapter(
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
    }

    override fun initViews(view: View) {
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
        fab = view.findViewById(R.id.fab)
    }

    override fun setupFragment() {

        viewPager.adapter = pagerAdapter

        fab.setOnClickListener {
            Router.routeToAddGql(context)
        }
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}