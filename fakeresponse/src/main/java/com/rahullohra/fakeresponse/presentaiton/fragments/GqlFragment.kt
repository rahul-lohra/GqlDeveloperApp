package com.rahullohra.fakeresponse.presentaiton.fragments

import android.os.Bundle
import android.view.View
import android.widget.ViewFlipper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahullohra.fakeresponse.App
import com.rahullohra.fakeresponse.R
import com.rahullohra.fakeresponse.ResponseListData
import com.rahullohra.fakeresponse.data.di.component.DaggerHomeScreenComponent
import com.rahullohra.fakeresponse.presentaiton.adapters.GqlRvAdapter
import com.rahullohra.fakeresponse.presentaiton.livedata.Fail
import com.rahullohra.fakeresponse.presentaiton.livedata.Loading
import com.rahullohra.fakeresponse.presentaiton.livedata.Success
import com.rahullohra.fakeresponse.presentaiton.viewmodels.FakeResponseModel
import com.rahullohra.fakeresponse.presentaiton.viewmodels.ViewModelFactory
import javax.inject.Inject

class GqlFragment : BaseFragment() {
    val STATE_EMPTY = 0
    val STATE_DATA = 1

    companion object{
        fun newInstance():GqlFragment{
            return GqlFragment()
        }
    }

    lateinit var rv: RecyclerView
    lateinit var viewFlipper: ViewFlipper

    lateinit var adapter: GqlRvAdapter
    lateinit var dataList: ArrayList<ResponseListData>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var viewModel: FakeResponseModel?=null

    override fun getLayout() = R.layout.fragment_gql

    override fun initVars() {
        dataList = ArrayList()
        adapter = GqlRvAdapter(dataList)
    }

    override fun initViews(view: View) {
        viewFlipper = view.findViewById(R.id.viewFlipper)
        rv = view.findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(context)

        rv.adapter = adapter
    }

    override fun setupFragment() {
        injectComponents()
        setListeners()
        getData()
    }

    fun setListeners() {
        viewModel!!.liveData.observe(viewLifecycleOwner, Observer { it ->
            when (it) {
                is Success -> {
                    if (it.data.isNotEmpty()) {
                        dataList.clear()
                        dataList.addAll(it.data)
                        adapter.notifyDataSetChanged()
                        showDataUi()
                    } else {
                        showEmptyUi()
                    }

                }
                is Fail -> {
                }
                is Loading -> {
                }
            }
        })
    }

    fun showEmptyUi() {
        viewFlipper.displayedChild = STATE_EMPTY
    }

    fun showDataUi() {
        viewFlipper.displayedChild = STATE_DATA
    }

    private fun getData() {
        viewModel?.getGql()
    }

    private fun injectComponents(){
        if(viewModel == null){
            val appComponent = (context?.applicationContext as App).appComponent
            DaggerHomeScreenComponent.builder()
                .appComponent(appComponent)
                .build().inject(this)
            viewModel = ViewModelProviders.of(this, viewModelFactory)[FakeResponseModel::class.java]
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectComponents()
    }
}