package com.dev.rexhuang.r_ui.app.refresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.dev.rexhuang.r_ui.R
import com.dev.rexhuang.rui.refresh.RRefresh
import com.dev.rexhuang.rui.refresh.RRefreshLayout
import com.dev.rexhuang.rui.refresh.RTextOverView

class RefreshActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh)
        val refreshLayout = findViewById<RRefreshLayout>(R.id.refresh_layout)
        val xOverView = RTextOverView(this)
        refreshLayout.setRefreshOverView(xOverView)
        refreshLayout.setRefreshListener(object :RRefresh.RRefreshListener{
            override fun enableRefresh(): Boolean {
                return true
            }

            override fun onRefresh() {
                Handler().postDelayed({refreshLayout.refreshFinished()},1000)
            }

        })


    }

    override fun onClick(v: View?) {
    }
}
