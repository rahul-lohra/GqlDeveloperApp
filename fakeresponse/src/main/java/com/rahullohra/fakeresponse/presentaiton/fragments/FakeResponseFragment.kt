package com.rahullohra.fakeresponse.presentaiton.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.rahullohra.fakeresponse.R
import com.rahullohra.fakeresponse.Router
import com.rahullohra.fakeresponse.data.diProvider.fragments.FakeResponseFragmentProvider
import com.rahullohra.fakeresponse.presentaiton.activities.FakeResponseActivity
import com.rahullohra.fakeresponse.presentaiton.adapters.PagerAdapter
import com.rahullohra.fakeresponse.presentaiton.livedata.Success
import com.rahullohra.fakeresponse.presentaiton.viewmodels.FakeResponseVM

class FakeResponseFragment : BaseFragment() {

    companion object {
        fun newInstance() = FakeResponseFragment()
    }

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    lateinit var fab: FloatingActionButton

    private lateinit var pagerAdapter: PagerAdapter
    lateinit var viewModel: FakeResponseVM


    override fun getLayout() = R.layout.fake_response_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        tabLayout.visibility = View.GONE
    }

    override fun setupFragment() {
        inijectComponents()
        setListeners()
        viewPager.adapter = pagerAdapter
    }

    fun setListeners() {
        fab.setOnClickListener {
            Router.routeToAddGql(context)
        }

        viewModel.clearSqlRecords.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> {
                    handleAllRecordsDeleted()
                }
            }
        })

        viewModel.resetData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> {
                    handleResetData()
                }
            }
        })
    }

    fun handleAllRecordsDeleted() {
        if (context is FakeResponseActivity) {
            pagerAdapter.fragmentList.forEach {
                if (it is GqlFragment) {
                    it.showEmptyUi()
                }
            }

        }
    }

    fun handleResetData() {
        if (context is FakeResponseActivity) {
            Toast.makeText(
                context,
                "All data cleared, Restart app to use this library again",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun inijectComponents() {
        FakeResponseFragmentProvider().inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.gql_settings_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.gql_menu_clear_records -> {
                viewModel.deleteAllGqlRecords()
            }
            R.id.gql_menu_reset -> {
                viewModel.resetLibrary()
            }
        }
        return true
    }
}