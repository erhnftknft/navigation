package com.erhn.ftknft.library

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.erhn.ftknft.library.core.Navigator
import com.erhn.ftknft.library.core.Screen

open class BaseNavigator(protected val manager: FragmentManager, @IdRes val container: Int) :
    Navigator {


    override open fun replace(screen: Screen) {
        manager.beginTransaction()
            .replace(container, screen.createFragment(), screen.screenTag)
            .addTransactionSetting(Command.REPLACE)
            .commit()
    }

    protected open fun replaceTransaction(replaceTransaction: FragmentTransaction): FragmentTransaction {
        return replaceTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    }

    override open fun forward(screen: Screen) {
        manager.beginTransaction()
            .addToBackStack(screen.screenTag)
            .replace(container, screen.createFragment(), screen.screenTag)
            .addTransactionSetting(Command.FORWARD)
            .commit()
    }

    protected open fun forwardTransaction(forwardTransaction: FragmentTransaction): FragmentTransaction {
        return forwardTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    }

    override open fun back() {
        manager.popBackStack()
    }

    override open fun backTo(screenTag: String?) {
        if (screenTag != null) {
            manager.popBackStack(screenTag, 0)
        } else {
            val count = manager.backStackEntryCount
            for (i in 0 until count) {
                manager.popBackStack()
            }
        }
    }


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

