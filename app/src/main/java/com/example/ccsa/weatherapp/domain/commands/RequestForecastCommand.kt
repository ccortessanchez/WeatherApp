package com.example.ccsa.weatherapp.domain.commands

import com.example.ccsa.weatherapp.data.ForecastRequest
import com.example.ccsa.weatherapp.domain.mappers.ForecastDataMapper
import com.example.ccsa.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: String): Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}