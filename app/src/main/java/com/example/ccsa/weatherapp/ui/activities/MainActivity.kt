package com.example.ccsa.weatherapp.ui.activities

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.example.ccsa.weatherapp.R
import com.example.ccsa.weatherapp.domain.commands.RequestForecastCommand
import com.example.ccsa.weatherapp.extensions.DelegatesExt
import com.example.ccsa.weatherapp.ui.adapters.ForecastListAdapter
import com.example.ccsa.weatherapp.domain.model.ForecastList
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MainActivity : AppCompatActivity(), ToolbarManager {

    private val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecast_list.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecast_list)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = async(UI) {
        val result = bg { RequestForecastCommand(zipCode).execute() }
        updateUI(result.await())
    }

    private fun updateUI(weekForecast: ForecastList) {
        val adapter = ForecastListAdapter(weekForecast) {
            startActivity<DetailActivity>(DetailActivity.ID to it.id,
                    DetailActivity.CITY_NAME to weekForecast.city)
        }
        forecast_list.adapter = adapter
        toolbarTitle = "${weekForecast.city} (${weekForecast.country})"
    }
}


