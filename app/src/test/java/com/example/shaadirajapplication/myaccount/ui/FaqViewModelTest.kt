package com.example.shaadirajapplication.myaccount.ui

import androidx.lifecycle.Observer
import com.example.shaadirajapplication.ShaadiUser.data.User.User
import com.example.shaadirajapplication.ShaadiUser.viewmodel.UserViewModel
import com.example.shaadirajapplication.networking.Resource
import com.example.shaadirajapplication.repository.UserRepository
import com.example.shaadirajapplication.testrules.BaseTestRule
import com.example.shaadirajapplication.utils.FakeException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class FaqViewModelTest : BaseTestRule() {

    @Mock
    lateinit var faqRepository: UserRepository
    lateinit var viewModel: UserViewModel
    private val error = Resource.error(FakeException("Error while fetching test api"), null)


    /** ALL OBSERVERS */
    @Mock
    lateinit var faqQuestionAnswerObserver: Observer<Resource<User>>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = UserViewModel(
            faqRepository
        )
        viewModel.getUserLiveData().observeForever(faqQuestionAnswerObserver)
        Dispatchers.setMain(coroutineTestRule.testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * Testing that the view model and the mocked repo is not null
     */
    @Test
    fun testViewModelAndRepoNotNull() = runBlockingTest {
        Assert.assertNotNull(viewModel)
        Assert.assertNotNull(faqRepository)
    }

    /**
     * Testing for live data and it's observers
     */
    @Test
    fun testMyAccountLiveDataNotNullAndHasObservers() = runBlockingTest {
        Assert.assertNotNull(viewModel.getUserLiveData().hasObservers())
    }


    /**
     * @fun FaqData
     * @type SUCCESS
     */
    @Test
    fun testFaqDataFetchSuccessSequence() = runBlockingTest {
        val faqResponseWrapper = Mockito.mock(User::class.java)
        val mockResponseData = Resource.success(faqResponseWrapper)
        Mockito.`when`(
            faqRepository.getResult(10)
        ).thenReturn(mockResponseData)
        viewModel.getUser(10)
        Mockito.verify(faqQuestionAnswerObserver).onChanged(Resource.loading(null))
        Mockito.verify(faqQuestionAnswerObserver).onChanged(mockResponseData)
    }


    /**
     * @fun FaqData
     * @type FAILURE
     */
    @Test
    fun testFaqDataFetchFailureSequence() = runBlockingTest {
        Mockito.`when`(
            faqRepository.getResult(10)
        ).thenReturn(error)
        viewModel.getUser(10)
        Mockito.verify(faqQuestionAnswerObserver).onChanged(Resource.loading(null))
        Mockito.verify(faqQuestionAnswerObserver).onChanged(error)
    }
}