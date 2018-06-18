package com.sample.room.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.sample.room.R
import com.sample.room.factory.MainActivityFactory
import com.sample.room.repository.database.entity.EventEntity
import com.sample.room.viewModel.MainActivityViewModel
import dagger.android.AndroidInjection
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var mainActivityFactory: MainActivityFactory

    @BindView(R.id.etName)
    lateinit var nameET: EditText

    @BindView(R.id.etDescription)
    lateinit var descET: EditText

    @BindView(R.id.btnSubmit)
    lateinit var btnSubmit: Button

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        // android injection
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        // set up the layout
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        mainActivityViewModel = ViewModelProviders.of(this, mainActivityFactory).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getEvents().observe(this,
                Observer { t -> Timber.d("Something changed..WOW!!!!") })

        // get all the events and display
        mainActivityViewModel.getAllEvents()
        Timber.d("Get all the events SIZE: %s", mainActivityViewModel.getEvents().value?.size)
    }

    @OnClick(R.id.btnSubmit)
    fun onViewClicked(view: View) {

        when (view.id) {
            R.id.btnSubmit -> {
                Timber.d("Submit button for event generated")
                val eventEntity = EventEntity(0, nameET.text.toString(), descET.text.toString(), Date())
                mainActivityViewModel.addEvent(eventEntity)
            }
        }
    }
}

