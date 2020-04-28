package com.gianlucaveschi.investmentapp.mvi_course.ui.main.state

import com.gianlucaveschi.investmentapp.mvi_course.model.BlogPost
import com.gianlucaveschi.investmentapp.mvi_course.model.User

//Wrapper Class that wraps around all types of models
data class MainViewState (
    var blogPost: List<BlogPost>? = null,
    var user: User? = null
)