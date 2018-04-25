package com.example.ccsa.weatherapp.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

public fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)