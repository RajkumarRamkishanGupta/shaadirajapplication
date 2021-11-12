package com.example.shaadirajapplication.testrules

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

/**
 * Class with all basic test rule
 */
open class BaseTestRule {
    /**
     * To make suspended call to run in blocking part so that it will run in only one instance of Coroutines
     */
    @get:Rule
    var coroutineTestRule = CoroutinesTestRule()

    /**
     *
     * InstantTaskExecutorRule to make call of suspend function otherwise we will get the failed to invoke
     * suspend method error and Test case will not run
     */
    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()
}