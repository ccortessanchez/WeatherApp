package com.example.ccsa.weatherapp.ui.activities

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.example.ccsa.weatherapp.ui.adapters.ForecastListAdapter
import com.example.ccsa.weatherapp.R
import com.example.ccsa.weatherapp.domain.commands.RequestForecastCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.find
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecast_list.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecast_list)

        doAsync {
            val result = RequestForecastCommand(94043).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.ID to it.id,
                            DetailActivity.CITY_NAME to result.city)
                }
                forecast_list.adapter = adapter
                toolbarTitle = "${result.city} (${result.country})"
            }
        }
    }
}


