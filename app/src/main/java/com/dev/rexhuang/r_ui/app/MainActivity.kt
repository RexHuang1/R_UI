package com.dev.rexhuang.r_ui.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dev.rexhuang.r_ui.R
import com.dev.rexhuang.r_ui.app.tab.TabBottomDemoActivity

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
        }
    }
}
