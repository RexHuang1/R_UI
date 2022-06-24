package com.dev.rexhuang.r_ui.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dev.rexhuang.r_ui.R
import com.dev.rexhuang.r_ui.app.banner.BannerActivity
import com.dev.rexhuang.r_ui.app.custom.CustomViewActivity
import com.dev.rexhuang.r_ui.app.custom.TestActivity
import com.dev.rexhuang.r_ui.app.recyclerview.RecyclerviewActivity
import com.dev.rexhuang.r_ui.app.refresh.RefreshActivity
import com.dev.rexhuang.r_ui.app.tab.TabBottomDemoActivity
import com.dev.rexhuang.r_ui.app.tab.TabTopDemoActivity
import com.dev.rexhuang.r_ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_tab_top -> {
                startActivity(Intent(this, TabTopDemoActivity::class.java))
            }
            R.id.tv_tab_bottom -> {
                startActivity(Intent(this, TabBottomDemoActivity::class.java))
            }
            R.id.tv_refresh -> {
                startActivity(Intent(this, RefreshActivity::class.java))
            }
            R.id.tv_recycler_view -> {
                startActivity(Intent(this, RecyclerviewActivity::class.java))
            }
            R.id.tv_banner -> {
                startActivity(Intent(this, BannerActivity::class.java))
            }
            R.id.tv_custom_view -> {
                startActivity(Intent(this, CustomViewActivity::class.java))
            }
            R.id.tv_test_view -> {
                startActivity(Intent(this, TestActivity::class.java))
            }
        }
    }
}
