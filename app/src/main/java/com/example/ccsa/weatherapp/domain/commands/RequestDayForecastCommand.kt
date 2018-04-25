package com.example.ccsa.weatherapp.domain.commands

import com.example.ccsa.weatherapp.domain.datasource.ForecastProvider
import com.example.ccsa.weatherapp.domain.model.Forecast

class RequestDayForecastCommand(
        val id: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()) : Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}