package com.erhn.ftknft.library

import com.erhn.ftknft.library.core.IRouter
import com.erhn.ftknft.library.core.NavigatorHolder
import com.erhn.ftknft.library.core.Screen


open class BaseRouter(stackName: String? = null) : IRouter {

    val holder = NavigatorHolder()
    val stack = Stack(stackName ?: javaClass.simpleName)
    private val listeners = HashMap<Int, ArrayList<IRouter.ResultListener>>()

    override fun replaceScreen(screen: Screen) {
        holder.navigator?.apply {
            replace(screen)
            stack.pop()
            stack.push(screen.screenTag)
        }
    }

    override fun navigateTo(screen: Screen) {
        holder.navigator?.apply {
            forward(screen)
            stack.push(screen.screenTag)
        }
    }

    override fun backTo(screenKey: String) {
        holder.navigator?.apply {
            backTo(screenKey)
            stack.popTo(screenKey)
        }

    }

    override fun back(count: Int) {
        repeat(count) {
            holder.navigator?.apply {
                back()
                stack.pop()
            }
        }
    }

    override fun exit() {
        holder.navigator?.apply {
            back()
            stack.pop()
        }

    }

    override fun newRootScreen(screen: Screen) {
        holder.navigator?.apply {
            backTo(null)
            stack.clear()
            replace(screen)
            stack.pop()
            stack.push(screen.screenTag)
        }
    }

    override fun addResultListener(code: Int, listener: IRouter.ResultListener) {
        listeners.getOrPut(code, { arrayListOf() }).add(listener)
    }

    override fun removeResultListener(code: Int, listener: IRouter.ResultListener) {
        listeners[code]?.remove(listener)
    }

    override fun send(messageCode: Int, data: Any?) {
        listeners[messageCode]?.onEach { it.receive(messageCode, data) }
    }
}