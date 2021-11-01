package com.example.shaadirajapplication.common

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivity =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity == null) {
            return false
        } else {
            val info = connectivity.allNetworkInfo
            for (networkInfo in info) {
                if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        return false
    }

    fun hideProgressBar(activity: Activity?, progressBar: ProgressBar?) {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        if (progressBar != null) {
            progressBar.visibility = View.GONE
        }
    }

    fun showProgressBar(activity: Activity?, progressBar: ProgressBar?) {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        if (progressBar != null) {
            progressBar.visibility = View.VISIBLE
        }
    }


    fun getFormatedDate(
        date: String
    ): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val date: Date =
            dateFormat.parse(date) //You will get date object relative to server/client timezone wherever it is parsed

        val formatter: DateFormat =
            SimpleDateFormat("yyyy-MM-dd") //If you need time just put specific format for time like 'HH:mm:ss'

        return formatter.format(date)

    }


}