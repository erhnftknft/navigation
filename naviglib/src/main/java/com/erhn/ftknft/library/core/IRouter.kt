package com.erhn.ftknft.library.core

interface IRouter {
    fun replaceScreen(screen: Screen)
    fun navigateTo(screen: Screen)
    fun backTo(screenKey: String)
    fun back(count: Int)
    fun exit()
    fun newRootScreen(screen: Screen)
    fun send(messageCode: Int, data: Any?)
    fun addResultListener(code: Int, listener: ResultListener)
    fun removeResultListener(code: Int, listener: ResultListener)

    interface ResultListener {
        fun receive(code: Int, data: Any?)
    }
}