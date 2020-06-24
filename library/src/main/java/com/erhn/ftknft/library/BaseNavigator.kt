package com.erhn.ftknft.library

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.erhn.ftknft.library.core.INavigator

abstract class BaseNavigator(protected val manager: FragmentManager, @IdRes val container: Int) :
    INavigator {


    override open fun replace(screenKey: String, data: Any?) {
        manager.beginTransaction()
            .replace(container, createFragment(screenKey, data), screenKey)
            .commit()
    }

    override open fun forward(screenKey: String, data: Any?) {
        manager.beginTransaction()
            .addToBackStack(screenKey)
            .replace(container, createFragment(screenKey, data), screenKey)
            .commit()
    }

    override open fun back() {
        manager.popBackStack()
    }

    override open fun backTo(screenKey: String?) {
        if (screenKey != null) {
            manager.popBackStack(screenKey, 0)
        } else {
            val count = manager.backStackEntryCount
            for (i in 0 until count) {
                manager.popBackStack()
            }
        }
    }


    abstract fun createFragment(screenKey: String, data: Any?): Fragment
}