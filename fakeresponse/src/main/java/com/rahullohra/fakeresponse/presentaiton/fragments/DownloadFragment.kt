package com.rahullohra.fakeresponse.presentaiton.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rahullohra.fakeresponse.App
import com.rahullohra.fakeresponse.R
import com.rahullohra.fakeresponse.data.di.component.DaggerDownloadFragmentComponent
import com.rahullohra.fakeresponse.presentaiton.activities.FakeResponseActivity
import com.rahullohra.fakeresponse.presentaiton.livedata.Fail
import com.rahullohra.fakeresponse.presentaiton.livedata.Loading
import com.rahullohra.fakeresponse.presentaiton.livedata.Success
import com.rahullohra.fakeresponse.presentaiton.viewmodels.DownloadFragmentVM
import com.rahullohra.fakeresponse.presentaiton.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.gql_fragment_download.*
import javax.inject.Inject

class DownloadFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var viewModel: DownloadFragmentVM? = null

    companion object {
        fun newInstance(): DownloadFragment {
            return DownloadFragment()
        }
    }

    override fun getLayout() = R.layout.gql_fragment_download

    override fun setupFragment() {
        injectComponents()
        setListeners()
        getData()
    }

    fun setListeners() {
        viewModel?.liveData?.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loading -> {
                    tvStatus.text = "Downloading.."
                    btnRetry.isEnabled = false
                }
                is Success -> {
                    tvStatus.text = "Download Success"
                    btnRetry.isEnabled = false
                    handleOnDownloadSuccess()
                }
                is Fail -> {
                    btnRetry.isEnabled = true
                    tvStatus.text = "Download Fail: ${it.ex.message}"
                }
            }
        })
    }

    fun handleOnDownloadSuccess(){
        if(context is FakeResponseActivity){
            (context as FakeResponseActivity).onSqlFilesArePresent()
        }
    }

    fun getData() {
        viewModel?.downloadSqliteFiles()
    }

    fun injectComponents() {
        if (viewModel == null) {
            val appComponent = (context?.applicationContext as App).appComponent
            DaggerDownloadFragmentComponent.builder()
                .appComponent(appComponent)
                .build().inject(this)
            viewModel =
                ViewModelProviders.of(this, viewModelFactory)[DownloadFragmentVM::class.java]
        }
    }


}
