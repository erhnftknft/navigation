package com.erhn.ftknft.library

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.erhn.ftknft.library.core.INavigator

abstract class BaseNavigator(protected val manager: FragmentManager, @IdRes val container: Int) :
    INavigator {


    override open fun replace(screenKey: String, data: Any?) {
        manager.beginTransaction()
            .replace(container, createFragment(screenKey, data), screenKey)
            .addTransactionSetting(Command.REPLACE)
            .commit()
    }

    protected open fun replaceTransaction(fragmentTransaction: FragmentTransaction): FragmentTransaction {
        return fragmentTransaction
    }

    override open fun forward(screenKey: String, data: Any?) {
        val forwardTransaction = manager.beginTransaction()
            .addToBackStack(screenKey)
            .replace(container, createFragment(screenKey, data), screenKey)
            .addTransactionSetting(Command.FORWARD)
            .commit()
    }

    protected open fun forwardTransaction(fragmentTransaction: FragmentTransaction): FragmentTransaction {
        return fragmentTransaction
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

    private enum class Command {
        FORWARD, REPLACE
    }

    private fun FragmentTransaction.addTransactionSetting(command: Command): FragmentTransaction {
        return when (command) {
            Command.FORWARD -> forwardTransaction(this)
            Command.REPLACE -> replaceTransaction(this)
        }
    }

}

