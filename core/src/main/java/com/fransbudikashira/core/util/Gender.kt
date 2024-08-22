package com.fransbudikashira.core.util

sealed class Gender(val text: String) {
    object Male: Gender("Male")
    object Female: Gender("Female")
    object Unknown: Gender("unknown")
}
