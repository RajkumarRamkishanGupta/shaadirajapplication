package com.example.shaadirajapplication

import android.app.Application
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ShaadiApplication  : Application() {

    init {
        instance = this
    }



    companion object {
        private var instance: ShaadiApplication? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

    }

    override fun onCreate() {
        super.onCreate()
    }

}