package com.erhn.ftknft.library.core

interface INavigator {
    fun replace(screenKey: String, data: Any? = null)
    fun forward(screenKey: String, data: Any? = null)
    fun back()
    fun backTo(screenKey: String?)
}