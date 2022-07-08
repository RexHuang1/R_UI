package com.dev.rexhuang.r_ui.app.input

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dev.rexhuang.r_ui.R
import com.dev.rexhuang.rlib.util.RResUtil
import com.dev.rexhuang.rlib.util.RStatusBar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        RStatusBar.setStatusBar(this, true, translucent = false)
        nav_bar.setNavListener(View.OnClickListener { onBackPressed() })
        nav_bar.addRightTextButton(RResUtil.getString(R.string.action_submit), R.integer.id_action_login)
            .setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        nav_bar.setTitle(RResUtil.getString(R.string.title_login))
    }

}