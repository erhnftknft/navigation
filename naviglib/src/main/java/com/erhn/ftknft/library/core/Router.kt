package com.erhn.ftknft.library.core

interface Router {
    fun replaceScreen(screen: Screen)
    fun navigateTo(screen: Screen)
    fun backTo(screenKey: String)
    fun back(count: Int)
    fun exit()
    fun newRootScreen(screen: Screen)
    fun newChain(vararg screens: Screen)
    fun newRootChain(vararg screens: Screen)
}