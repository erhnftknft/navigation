package com.erhn.ftknft.library.core


class NavigatorHolder {

    var navigator: Navigator? = null
        private set

    fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    fun removeNavigator() {
        this.navigator = null
    }
}