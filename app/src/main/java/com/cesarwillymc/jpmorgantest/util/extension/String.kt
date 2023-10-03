package com.cesarwillymc.jpmorgantest.util.extension

fun formatCityStateCountry(city: String, stateCode: String, countryCode: String): String {
    return if (stateCode.isNotBlank() && countryCode.isNotBlank()) {
        "$city, $stateCode, $countryCode"
    } else if (stateCode.isNotBlank()) {
        "$city, $stateCode"
    } else {
        city
    }
}
