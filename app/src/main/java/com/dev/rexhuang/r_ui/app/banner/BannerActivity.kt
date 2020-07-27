package com.dev.rexhuang.r_ui.app.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dev.rexhuang.r_ui.R
import com.dev.rexhuang.rui.banner.RBanner
import com.dev.rexhuang.rui.banner.core.RBannerMo
import com.dev.rexhuang.rui.banner.indicator.IRIndicator
import com.dev.rexhuang.rui.banner.indicator.RCircleIndicator
import com.dev.rexhuang.rui.banner.indicator.RNumberIndicator

class BannerActivity : AppCompatActivity() {

    private var urls = arrayOf(
        "https://www.devio.org/img/beauty_camera/beauty_camera1.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera3.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera4.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera5.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera2.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera6.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera7.jpg",
        "https://www.devio.org/img/beauty_camera/beauty_camera8.jpeg"
    )
    private var rIndicator: IRIndicator<*>? = null
    private var autoPlay: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        initView(null,false)
        findViewById<Switch>(R.id.auto_play).setOnCheckedChangeListener { _, isChecked ->
            autoPlay = isChecked
            initView(rIndicator, autoPlay)
        }
        findViewById<View>(R.id.tv_switch).setOnClickListener {
            //            mHiBanner.setAutoPlay(false)
            if (rIndicator is RCircleIndicator) {
                initView(RNumberIndicator(this), autoPlay)
            } else {
                initView(RCircleIndicator(this), autoPlay)
            }

        }
    }

    private fun initView(indicator: IRIndicator<*>?, autoPlay: Boolean) {
        this.rIndicator = indicator
        val mRBanner = findViewById<RBanner>(R.id.banner)
        val moList: MutableList<RBannerMo> = ArrayList()
        for (i in 0..7) {
            val mo = BannerMo()
            mo.url = urls[i % urls.size]
            moList.add(mo)
        }
        mRBanner.setRIndicator(indicator)
        mRBanner.setAutoPlay(autoPlay)
        mRBanner.setIntervalTime(2000)
//        mRBanner.setScrollDuration(10000)
        // 自定义布局
        mRBanner.setBannerData(R.layout.banner_item_layout, moList)
        mRBanner.setBindAdapter { viewHolder, mo, position ->
            val imageView: ImageView = viewHolder.findViewById(R.id.iv_image)
            Glide.with(this@BannerActivity).load(mo.url).into(imageView)
            val titleView: TextView = viewHolder.findViewById(R.id.tv_title)
            titleView.text = mo.url
            Log.d("----position:", position.toString() + "url:" + mo.url)
        }
    }
}
