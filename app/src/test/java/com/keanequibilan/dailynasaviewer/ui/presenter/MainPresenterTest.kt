package com.keanequibilan.dailynasaviewer.ui.presenter

import com.keanequibilan.dailynasaviewer.model.Apod
import com.keanequibilan.dailynasaviewer.ui.view.MainView
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response

private const val FAKE_ERROR_CODE = 416
private const val FAKE_SUCCESS_CODE_200 = 200
private const val FAKE_SUCCESS_CODE_202 = 202
private const val FAKE_APOD_URL = "http://fake-url"
private const val FAKE_APOD_TITLE = "FAKE TITLE"

@DisplayName("MainPresenter")
internal class MainPresenterTest {

    lateinit var mainPresenter: MainPresenter

    @Mock
    lateinit var mockApod: Apod

    @Mock
    lateinit var mockResponse: Response<Apod>

    @Mock
    lateinit var mockView: MainView

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)

        mainPresenter = MainPresenter(mockView)
    }

    @Nested
    @DisplayName("when onLoadApod is invoked")
    inner class OnLoadApod {
        @DisplayName("should show an error if response body is null")
        @Test
        fun nullResponseBody() {
            `when`(mockResponse.code())
                .thenReturn(FAKE_ERROR_CODE)

            mainPresenter.onLoadApod(mockResponse)

            verify(mockView).showError(FAKE_ERROR_CODE)
        }

        @DisplayName("should show an error if response is not 200 or 202")
        @Test
        fun notVerySuccessful() {
            `when`(mockResponse.body())
                .thenReturn(mockApod)
            `when`(mockResponse.code())
                .thenReturn(FAKE_ERROR_CODE)

            mainPresenter.onLoadApod(mockResponse)

            verify(mockView).showError(FAKE_ERROR_CODE)
        }

        @DisplayName("should not show an error if the response is 200")
        @Test
        fun verySuccessful200() {
            `when`(mockResponse.body())
                .thenReturn(mockApod)
            `when`(mockResponse.code())
                .thenReturn(FAKE_SUCCESS_CODE_200)

            mainPresenter.onLoadApod(mockResponse)

            verify(mockView, never()).showError(anyInt())
        }

        @DisplayName("should not show an error if the response is 202")
        @Test
        fun verySuccessful202() {
            `when`(mockResponse.body())
                .thenReturn(mockApod)
            `when`(mockResponse.code())
                .thenReturn(FAKE_SUCCESS_CODE_202)

            mainPresenter.onLoadApod(mockResponse)

            verify(mockView, never()).showError(anyInt())
        }

        @DisplayName("should load the image from the APOD if the response is very successful")
        @Test
        fun loadImage() {
            `when`(mockResponse.body())
                .thenReturn(mockApod)
            `when`(mockResponse.code())
                .thenReturn(FAKE_SUCCESS_CODE_200)
            `when`(mockApod.url)
                .thenReturn(FAKE_APOD_URL)

            mainPresenter.onLoadApod(mockResponse)

            verify(mockView).loadImage(FAKE_APOD_URL)
        }

        @DisplayName("should load the title from the APOD if the response is very successful")
        @Test
        fun loadTitle() {
            `when`(mockResponse.body())
                .thenReturn(mockApod)
            `when`(mockResponse.code())
                .thenReturn(FAKE_SUCCESS_CODE_200)
            `when`(mockApod.title)
                .thenReturn(FAKE_APOD_TITLE)

            mainPresenter.onLoadApod(mockResponse)

            verify(mockView).loadTitle(FAKE_APOD_TITLE)
        }
    }
}