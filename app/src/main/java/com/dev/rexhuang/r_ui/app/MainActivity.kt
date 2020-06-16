package com.dev.rexhuang.r_ui.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dev.rexhuang.r_ui.R
import com.dev.rexhuang.r_ui.app.custom.CustomViewActivity
import com.dev.rexhuang.r_ui.app.tab.TabBottomDemoActivity
import com.dev.rexhuang.r_ui.app.tab.TabTopDemoActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tv_tab_bottom ->{
                startActivity(Intent(this, TabBottomDemoActivity::class.java));
            }
            R.id.tv_tab_top ->{
                startActivity(Intent(this, TabTopDemoActivity::class.java));
            }
            R.id.tv_custom_view ->{
                startActivity(Intent(this, CustomViewActivity::class.java));
            }
        }
    }
}
