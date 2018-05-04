package com.example.ccsa.weatherapp.domain.commands

import com.example.ccsa.weatherapp.domain.datasource.ForecastProvider
import org.junit.Test
import org.mockito.Mockito.*

class RequestDayForecastCommandTest {

    @Test fun testProviderIsCalled() {
        val forecastProvider = mock(ForecastProvider::class.java)
        val command = RequestDayForecastCommand(2, forecastProvider)

        command.execute()

        verify(forecastProvider).requestForecast(2)
    }
}