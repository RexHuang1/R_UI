package com.dev.rexhuang.r_ui.tab

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dev.rexhuang.r_ui.R
import com.dev.rexhuang.rui.tab.bottom.RTabBottomInfo
import com.dev.rexhuang.rui.tab.bottom.RTabBottomLayout
import com.dev.rexhuang.rui.tab.util.RDisplayUtil
import java.util.*

/**
 **  created by RexHuang
 **  on 2020/6/12
 */
class TabBottomDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_bottom)
        initTabBottom()
    }

    @SuppressLint("NewApi")
    private fun initTabBottom() {
        val tabBottomLayout: RTabBottomLayout = findViewById(R.id.rtablayout)
        tabBottomLayout.setTabAlpha(0.85f)
        tabBottomLayout.setTabBackground(getColor(R.color.colorAccent))
        val bottomInfoList: MutableList<RTabBottomInfo<*>> = ArrayList()
        //删除mipmap-anydpi-v26 xml @https://hymane.itscoder.com/bitmap-factory-decode-return-null/
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//            HiTabInfo info = new HiTabInfo("tab" + i, bitmap, bitmap);
        val homeInfo = RTabBottomInfo(
            "首页",
            "fonts/iconfont.ttf",
            getString(R.string.if_home),
            null,
            getColor(R.color.tabBottomDefault),
            getColor(R.color.tabBottomSelected)
        )
        val infoRecommend = RTabBottomInfo(
            "收藏",
            "fonts/iconfont.ttf",
            getString(R.string.if_favorite),
            null,
            getColor(R.color.tabBottomDefault),
            getColor(R.color.tabBottomSelected)
        )

//        val infoCategory = HiTabInfo(
//            "分类",
//            "fonts/iconfont.ttf",
//            getString(R.string.if_category),
//            null,
//            getColor(R.color.tabBottomDefault),
//            getColor(R.color.tabBottomSelected)
//        )
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.fire, null)

        val infoCategory = RTabBottomInfo(
            "分类",
            bitmap,
            bitmap,
            getColor(R.color.tabBottomDefault),
            getColor(R.color.tabBottomSelected)
        )
        val infoChat = RTabBottomInfo(
            "推荐",
            "fonts/iconfont.ttf",
            getString(R.string.if_recommend),
            null,
            getColor(R.color.tabBottomDefault),
            getColor(R.color.tabBottomSelected)
        )
        val infoProfile = RTabBottomInfo(
            "我的",
            "fonts/iconfont.ttf",
            getString(R.string.if_profile),
            null,
            getColor(R.color.tabBottomDefault),
            getColor(R.color.tabBottomSelected)
        )
        bottomInfoList.add(homeInfo)
        bottomInfoList.add(infoRecommend)
        bottomInfoList.add(infoCategory)
        bottomInfoList.add(infoChat)
        bottomInfoList.add(infoProfile)
        tabBottomLayout.inflateInfo(bottomInfoList)
//        Handler().postDelayed(Runnable {
//            infoList.removeAt(1)
//            hiTabBottomLayout.inflateInfo(infoList)
//            hiTabBottomLayout.defaultSelected(homeInfo)
//        },2000)
        tabBottomLayout.addTabSelectedChangedListener { _, _, nextInfo ->
            Toast.makeText(this@TabBottomDemoActivity, nextInfo.name, Toast.LENGTH_SHORT).show()
        }
        tabBottomLayout.defaultSelected(homeInfo)
        //        改变某个tab的高度
        val tabBottom = tabBottomLayout.findTab(bottomInfoList[2])
        tabBottom?.apply { resetHeight(RDisplayUtil.dp2px(66f, resources)) }
    }

}