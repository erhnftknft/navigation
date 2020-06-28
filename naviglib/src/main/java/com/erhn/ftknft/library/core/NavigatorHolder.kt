package com.erhn.ftknft.library.core


class NavigatorHolder {

    var navigator: INavigator? = null
        private set

    fun setNavigator(navigator: INavigator) {
        this.navigator = navigator
    }

    fun removeNavigator() {
        this.navigator = null
    }
}