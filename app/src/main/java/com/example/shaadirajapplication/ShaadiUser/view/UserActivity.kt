package com.example.shaadirajapplication.ShaadiUser.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shaadirajapplication.R
import com.example.shaadirajapplication.ShaadiUser.data.User.Result
import com.example.shaadirajapplication.ShaadiUser.data.User.User
import com.example.shaadirajapplication.ShaadiUser.viewmodel.UserViewModel
import com.example.shaadirajapplication.common.Utils.hideProgressBar
import com.example.shaadirajapplication.common.Utils.showProgressBar
import com.example.shaadirajapplication.common.ViewModelFactory
import com.example.shaadirajapplication.networking.Resource
import com.example.shaadirajapplication.networking.RetrofitBuilder
import com.tatadigital.qmin.myaccount.view.adapter.UserAdapter
import kotlinx.android.synthetic.main.activityt_users.*

class UserActivity : AppCompatActivity(), UserAdapter.ItemListener {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter
    lateinit var userList: ArrayList<Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activityt_users)
        setUpViewModel()
        initRecyclerViews()
        observeData()
    }

    private fun initRecyclerViews() {
        userList = ArrayList()
        recycler_view.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(userList,this)
        recycler_view.adapter = userAdapter
    }

    private fun setUpViewModel() {
        userViewModel = ViewModelProvider(
            this,
            ViewModelFactory((RetrofitBuilder.apiService))
        ).get(UserViewModel::class.java)

        userViewModel.getUser(10)
    }

    private fun observeData() {
        userViewModel.getUserLiveData()
            .observe(this, collectUserObserver)
    }

    private val collectUserObserver = Observer<Resource<User>> { response ->
        when (response.status) {
            Resource.Status.SUCCESS -> {
                hideProgressBar(this, progress_bar_loading)
                with(response?.data) {
                    this?.let { statusCode ->
                        updateuserList(this?.results)
                    }
                }
            }
            Resource.Status.LOADING -> {

                showProgressBar(this, progress_bar_loading)
            }
            Resource.Status.ERROR -> {
                hideProgressBar(this, progress_bar_loading)
            }
        }
    }

    private fun updateuserList(userResultList: List<Result>?) {
        userResultList?.let { userResultList ->
            userList.apply {
                clear()
                addAll(userResultList)
            }
            userAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgressBar(this, progress_bar_loading)
    }

    override fun onConnect() {
        Toast.makeText(
            this, getString(R.string.connect_clicked),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDecline(viewToAnimate: View?, position: Int) {
        userAdapter.removeUser(viewToAnimate!!, position)
    }


}