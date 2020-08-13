package com.erhn.ftknft.library.core

interface INavigator {
    fun replace(screen: Screen)
    fun forward(screen: Screen)
    fun back()
    fun backTo(screenTag: String?)
}