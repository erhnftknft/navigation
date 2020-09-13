package com.erhn.ftknft.library

import com.erhn.ftknft.library.core.Router
import com.erhn.ftknft.library.core.NavigatorHolder
import com.erhn.ftknft.library.core.Screen


open class BaseRouter(stackName: String? = null) : Router {

    val holder = NavigatorHolder()
    val stack = Stack(stackName ?: javaClass.simpleName)

    override fun replaceScreen(screen: Screen) {
        holder.navigator?.let { n ->
            n.replace(screen)
            stack.pop()
            stack.push(screen.screenTag)
        }
    }

    override fun navigateTo(screen: Screen) {
        holder.navigator?.let { n ->
            n.forward(screen)
            stack.push(screen.screenTag)
        }
    }

    override fun backTo(screenKey: String) {
        holder.navigator?.let { n ->
            n.backTo(screenKey)
            stack.popTo(screenKey)
        }
    }

    override fun backToAndReplace(backToScreenKey: String, newScreen: Screen) {
        backTo(backToScreenKey)
        replaceScreen(newScreen)
    }

    override fun back(count: Int) {
        repeat(count) {
            holder.navigator?.let { n ->
                n.back()
                stack.pop()
            }
        }
    }

    override fun exit() {
        holder.navigator?.let { n ->
            n.back()
            stack.pop()
        }

    }

    override fun newRootScreen(screen: Screen) {
        holder.navigator?.let { n ->
            n.backTo(null)
            stack.clear()
            n.replace(screen)
            stack.pop()
            stack.push(screen.screenTag)
        }
    }

    override fun newChain(vararg screens: Screen) {
        holder.navigator?.let { n ->
            for (screen in screens) {
                navigateTo(screen)
            }
        }
    }

    override fun newRootChain(vararg screens: Screen) {
        holder.navigator?.let { n ->
            n.backTo(null)
            stack.clear()
            for (screen in screens) {
                if (screens.indexOf(screen) == 0) {
                    replaceScreen(screen)
                } else {
                    navigateTo(screen)
                }
            }
        }
    }
}