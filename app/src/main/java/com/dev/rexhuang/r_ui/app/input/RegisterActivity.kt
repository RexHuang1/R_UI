package com.dev.rexhuang.r_ui.app.input

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dev.rexhuang.r_ui.R
import com.dev.rexhuang.rlib.util.RResUtil
import com.dev.rexhuang.rlib.util.RStatusBar
import kotlinx.android.synthetic.main.activity_login.*

class RegisterActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        RStatusBar.setStatusBar(this, true, translucent = false)
        nav_bar.setNavListener(View.OnClickListener { onBackPressed() })
        nav_bar.setTitle(RResUtil.getString(R.string.title_register))
    }
}