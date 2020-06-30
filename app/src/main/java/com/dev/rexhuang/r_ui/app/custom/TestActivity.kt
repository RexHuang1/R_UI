package com.dev.rexhuang.r_ui.app.custom

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dev.rexhuang.r_ui.R


class TestActivity : AppCompatActivity() {

    private val TAG :String = TestActivity::javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val tv1 = findViewById<TextView>(R.id.tv1)
        val tv2 = findViewById<TextView>(R.id.tv2)
        val tv3 = findViewById<TextView>(R.id.tv3)
        val tv4 = findViewById<TextView>(R.id.tv4)
        val tv5 = findViewById<TextView>(R.id.tv5)
        val tv6 = findViewById<TextView>(R.id.tv6)
        val tv7 = findViewById<TextView>(R.id.tv7)
        val tv8 = findViewById<TextView>(R.id.tv8)
        val tv9 = findViewById<TextView>(R.id.tv9)
        val tv10 = findViewById<TextView>(R.id.tv10)
        val arrayOfTextViews = arrayOf(tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10)
        findViewById<Button>(R.id.ltrb).setOnClickListener(View.OnClickListener {
            for (tv in arrayOfTextViews) {
                val s:StringBuilder = StringBuilder(tv.text.toString())
                Log.e(TAG, s.append(" left : ").append(tv.left)
                    .append(" top : ").append(tv.top)
                    .append(" right : ").append(tv.right)
                    .append(" bottom : ").append(tv.bottom).toString())
            }
        })

        findViewById<Button>(R.id.xy).setOnClickListener(View.OnClickListener {
            for (tv in arrayOfTextViews) {
                val s:StringBuilder = StringBuilder(tv.text.toString())
                Log.e(TAG, s.append(" x : ").append(tv.x)
                    .append(" y : ").append(tv.y).toString())
            }
        })

        findViewById<Button>(R.id.loc).setOnClickListener(View.OnClickListener {
            for (tv in arrayOfTextViews) {
                val s:StringBuilder = StringBuilder(tv.text.toString())
                val location = IntArray(2)
                tv.getLocationOnScreen(location)
                Log.e(TAG, s.append(" x : ").append(location[0])
                    .append(" y : ").append(location[1]).toString())
            }
        })

        findViewById<Button>(R.id.low).setOnClickListener(View.OnClickListener {
            for (tv in arrayOfTextViews) {
                val s:StringBuilder = StringBuilder(tv.text.toString())
                val location = IntArray(2)
                tv.getLocationInWindow(location)
                Log.e(TAG, s.append(" x : ").append(location[0])
                    .append(" y : ").append(location[1]).toString())
            }
        })

        findViewById<Button>(R.id.gvr).setOnClickListener(View.OnClickListener {
            for (tv in arrayOfTextViews) {
                val s:StringBuilder = StringBuilder(tv.text.toString())
                val rect = Rect()
                tv.getGlobalVisibleRect(rect)
                Log.e(TAG, s.append(" left : ").append(rect.left)
                    .append(" top : ").append(rect.top)
                    .append(" right : ").append(rect.right)
                    .append(" bottom : ").append(rect.bottom).toString())
            }
        })

        findViewById<Button>(R.id.lvr).setOnClickListener(View.OnClickListener {
            for (tv in arrayOfTextViews) {
                val s:StringBuilder = StringBuilder(tv.text.toString())
                val rect = Rect()
                tv.getLocalVisibleRect(rect)
                Log.e(TAG, s.append(" left : ").append(rect.left)
                    .append(" top : ").append(rect.top)
                    .append(" right : ").append(rect.right)
                    .append(" bottom : ").append(rect.bottom).toString())
            }
        })
    }
}
