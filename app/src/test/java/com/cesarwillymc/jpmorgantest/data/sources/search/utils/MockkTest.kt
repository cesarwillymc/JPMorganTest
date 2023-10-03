package com.cesarwillymc.jpmorgantest.data.sources.search.utils

import io.mockk.MockKAnnotations
import org.junit.Before

open class MockkTest {
    @Before
    fun initMocks() {
        MockKAnnotations.init(this)
    }
}
