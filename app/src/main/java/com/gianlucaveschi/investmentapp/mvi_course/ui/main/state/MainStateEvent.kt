package com.gianlucaveschi.investmentapp.mvi_course.ui.main.state

import com.gianlucaveschi.investmentapp.mvi_course.model.BlogPost
import com.gianlucaveschi.investmentapp.mvi_course.model.User

/**
 * Wrapper Class that wraps around all types of models.
 *
 * A sealed class allows representing constrained hierarchies in which an object can only be
 * of one of the given types. That is, we have a class with a specific number of subclasses and
 * it will allow objects from a sealed class to keep state.
 * */
sealed class MainStateEvent {

    class GetBlogPostsEvent: MainStateEvent()

    class GetUserEvent(
        val userId: String
    ): MainStateEvent()

    class None: MainStateEvent()

}