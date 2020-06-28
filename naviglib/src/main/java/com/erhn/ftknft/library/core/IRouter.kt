package com.erhn.ftknft.library.core

interface IRouter {
    fun replaceScreen(screenKey: String, data: Any? = null)
    fun navigateTo(screenKey: String, data: Any? = null)
    fun backTo(screenKey: String)
    fun back(count: Int)
    fun exit()
    fun newRootScreen(screenKey: String, data: Any? = null)
    fun send(messageCode: Int, data: Any?)
    fun addResultListener(code: Int, listener: ResultListener)
    fun removeResultListener(code: Int, listener: ResultListener)

    interface ResultListener {
        fun receive(code: Int, data: Any?)
    }
}