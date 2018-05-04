package com.example.ccsa.weatherapp.ui.activities

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.example.ccsa.weatherapp.R
import com.example.ccsa.weatherapp.extensions.ctx
import com.example.ccsa.weatherapp.extensions.slideEnter
import com.example.ccsa.weatherapp.extensions.slideExit
import com.example.ccsa.weatherapp.ui.App
import org.jetbrains.anko.toast
import org.jetbrains.anko.startActivity

interface ToolbarManager {
    val toolbar: Toolbar

    var toolbarTitle: String
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = toolbar.title.toString()
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        set(value) {
            toolbar.title = value
        }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> toolbar.ctx.startActivity<SettingsActivity>()
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply {
        progress = 1f
    }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }
}