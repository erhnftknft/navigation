package com.erhn.ftknft.library

import com.erhn.ftknft.library.core.IRouter
import com.erhn.ftknft.library.core.NavigatorHolder


class BaseRouter(private val stackName: String = "BaseStack") : IRouter {

    val holder = NavigatorHolder()
    val stack = Stack(stackName)
    private val listeners = HashMap<Int, ArrayList<IRouter.ResultListener>>()

    override fun replaceScreen(screenKey: String, data: Any?) {
        holder.navigator?.apply {
            replace(screenKey, data)
            stack.pop()
            stack.push(screenKey)
        }
    }

    override fun navigateTo(screenKey: String, data: Any?) {
        holder.navigator?.apply {
            forward(screenKey, data)
            stack.push(screenKey)
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

    override fun newRootScreen(screenKey: String, data: Any?) {
        holder.navigator?.apply {
            backTo(null)
            stack.clear()
            replace(screenKey, data)
            stack.pop()
            stack.push(screenKey)
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