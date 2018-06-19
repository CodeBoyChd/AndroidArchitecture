package com.sample.room.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sample.room.utility.NetworkUtil
import dagger.android.AndroidInjection

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // android injection
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
    }

    fun isNetworkConnected(): Boolean = NetworkUtil.isNetworkConnected(this)
}