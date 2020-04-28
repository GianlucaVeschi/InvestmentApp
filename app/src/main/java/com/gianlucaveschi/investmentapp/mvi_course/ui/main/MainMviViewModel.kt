package com.gianlucaveschi.investmentapp.mvi_course.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gianlucaveschi.investmentapp.mvi_course.ui.main.state.MainStateEvent
import com.gianlucaveschi.investmentapp.mvi_course.ui.main.state.MainViewState
import com.gianlucaveschi.investmentapp.mvi_course.util.AbsentLiveData

class MainMviViewModel : ViewModel() {

    //The underscore reflects the google naming convention
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()
    private val _stateEvent : MutableLiveData<MainStateEvent> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState  //In Kotlin a custom getter can be defined to access a property

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent){stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }


    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState>{
        when(stateEvent){

            is MainStateEvent.GetBlogPostsEvent -> {
                return AbsentLiveData.create()
            }

            is MainStateEvent.GetUserEvent -> {
                return AbsentLiveData.create()
            }

            is MainStateEvent.None ->{
                return AbsentLiveData.create()
            }
        }
    }
}