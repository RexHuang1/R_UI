package com.dev.rexhuang.r_ui.app.tab

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dev.rexhuang.r_ui.R
import com.dev.rexhuang.rui.tab.top.RTabTopInfo
import com.dev.rexhuang.rui.tab.top.RTabTopLayout
import java.util.*

/**
 **  created by RexHuang
 **  on 2020/6/12
 */
class TabTopDemoActivity : AppCompatActivity() {

    var tabsStr = arrayOf(
        "热门",
        "服装",
        "数码",
        "鞋子",
        "零食",
        "家电",
        "汽车",
        "百货",
        "家居",
        "装修",
        "运动"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_top)
        initTabTop()
    }

    @SuppressLint("NewApi")
    private fun initTabTop() {
        val tabTopLayout: RTabTopLayout = findViewById(R.id.tab_top_layout)
        val tabInfoList: MutableList<RTabTopInfo<*>> = ArrayList()
        val defaultColor = resources.getColor(R.color.tabBottomDefault)
        val tintColor = resources.getColor(R.color.tabBottomSelected)
        var i = 0;
        for (s in tabsStr) {
            val info: RTabTopInfo<*> = RTabTopInfo<Int>(s, defaultColor, tintColor)
            tabInfoList.add(info);
        }
        tabTopLayout.inflateInfo(tabInfoList)
//        Handler().postDelayed(Runnable {
//            infoList.removeAt(1)
//            hiTabBottomLayout.inflateInfo(infoList)
//            hiTabBottomLayout.defaultSelected(homeInfo)
//        },2000)
        tabTopLayout.addTabSelectedChangedListener { _, _, nextInfo ->
            Toast.makeText(this@TabTopDemoActivity, nextInfo.name, Toast.LENGTH_SHORT).show()
        }
        tabTopLayout.defaultSelected(tabInfoList[0])
    }

}