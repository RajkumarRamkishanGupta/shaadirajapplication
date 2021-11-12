package com.tatadigital.qmin.myaccount.repository

import com.example.shaadirajapplication.networking.ApiService
import com.example.shaadirajapplication.testrules.BaseTestRule
import com.example.shaadirajapplication.utils.ToolsTest
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.io.IOException
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class FaqRepositoryImplTest : BaseTestRule() {

    private var mockWebServer = MockWebServer()
    lateinit var api: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        api = ToolsTest.getRetrofitBuilderTesting(mockWebServer).create(ApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testFaqAPISuccess() = runBlocking {

        var temp = this.javaClass.classLoader!!.getResource("./myaccount/user.json").readText()
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(temp)
        mockWebServer.enqueue(mockResponse)
        val faqResponse = api.getResult(10)
        Assert.assertNotNull(faqResponse)
        Assert.assertNotNull(faqResponse.results.isNullOrEmpty())
    }

}