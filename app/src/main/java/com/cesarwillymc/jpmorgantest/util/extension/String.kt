package com.cesarwillymc.jpmorgantest.util.extension

import com.cesarwillymc.jpmorgantest.util.constants.DEFAULT_FORMAT
import com.cesarwillymc.jpmorgantest.util.constants.FORMAT_IMAGE

fun String?.formatImageUrl() = FORMAT_IMAGE.format(
    this ?: DEFAULT_FORMAT
)
