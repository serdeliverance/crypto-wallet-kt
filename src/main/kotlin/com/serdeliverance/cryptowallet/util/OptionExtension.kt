package com.serdeliverance.cryptowallet.util

import java.util.Optional

object OptionExtension {
    val <T> Optional<T>.value: T?
        get() = orElse(null)
}