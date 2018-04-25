package com.example.ccsa.weatherapp.domain.datasource

import com.example.ccsa.weatherapp.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}