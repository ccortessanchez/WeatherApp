package com.example.ccsa.weatherapp.domain.commands

public interface  Command<out T> {
    fun execute(): T
}