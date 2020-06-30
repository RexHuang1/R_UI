package com.dev.rexhuang.r_ui.app.custom

import android.animation.Keyframe
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.dev.rexhuang.r_ui.R


class CustomViewActivity : AppCompatActivity() {

    private var linearLayoutContainer: LinearLayout? = null
    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
//        val frame = findViewById<FrameLayout>(R.id.framelayout);
//        val tv = findViewById<TextView>(R.id.custom_tv)
//        val scaleAnimation = ScaleAnimation(0.5f, 1f, 0.5f, 1f)
//        scaleAnimation.duration = 10000
//        scaleAnimation.fillAfter = true
//        scaleAnimation.interpolator = AnticipateOvershootInterpolator()
//        frame.startAnimation(scaleAnimation)

//        /**
//         * 左右震动效果
//         */
//        val frame0 = Keyframe.ofFloat(0f, 0f)
//        val frame1 = Keyframe.ofFloat(0.1f, -20f)
//        val frame2 = Keyframe.ofFloat(0.2f, 20f)
//        val frame3 = Keyframe.ofFloat(0.3f, -20f)
//        val frame4 = Keyframe.ofFloat(0.4f, 20f)
//        val frame5 = Keyframe.ofFloat(0.5f, -20f)
//        val frame6 = Keyframe.ofFloat(0.6f, 20f)
//        val frame7 = Keyframe.ofFloat(0.7f, -20f)
//        val frame8 = Keyframe.ofFloat(0.8f, 20f)
//        val frame9 = Keyframe.ofFloat(0.9f, -20f)
//        val frame10 = Keyframe.ofFloat(1f, 0f)
//        val frameHolder1 = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10)
//
//        /**
//         * scaleX放大1.1倍
//         */
//        val scaleXframe0 = Keyframe.ofFloat(0f, 1f)
//        val scaleXframe1 = Keyframe.ofFloat(0.1f, 1.1f)
//        val scaleXframe9 = Keyframe.ofFloat(0.9f, 1.1f)
//        val scaleXframe10 = Keyframe.ofFloat(1f, 1f)
//        val frameHolder2 = PropertyValuesHolder.ofKeyframe(
//            "ScaleX",
//            scaleXframe0,
//            scaleXframe1,
//            scaleXframe9,
//            scaleXframe10
//        )
//
//        /**
//         * scaleY放大1.1倍
//         */
//        val scaleYframe0 = Keyframe.ofFloat(0f, 1f)
//        val scaleYframe1 = Keyframe.ofFloat(0.1f, 1.1f)
//        val scaleYframe9 = Keyframe.ofFloat(0.9f, 1.1f)
//        val scaleYframe10 = Keyframe.ofFloat(1f, 1f)
//        val frameHolder3 = PropertyValuesHolder.ofKeyframe(
//            "ScaleY",
//            scaleYframe0,
//            scaleYframe1,
//            scaleYframe9,
//            scaleYframe10
//        )
//
//        val rotationHolder =
//            PropertyValuesHolder.ofFloat("Rotation", 60f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f)
//        val alphaHolder = PropertyValuesHolder.ofFloat("alpha", 0.1f, 1f, 0.1f, 1f)
//        val animator = ObjectAnimator.ofPropertyValuesHolder(frame,frameHolder1,frameHolder2,frameHolder3)
//        animator.duration = 1000
//        animator.start()

//        val charA : Char='A'
//        val charZ : Char='Z'
//        val charHolder  = PropertyValuesHolder.ofObject("CharText", CharaEvaluator(),'A',
//            'Z')
//        val charAnimator = ObjectAnimator.ofPropertyValuesHolder(tv, charHolder)
//        charAnimator.duration = 10000
//        animator.interpolator = AccelerateInterpolator()
//        charAnimator.start()


//        linearLayoutContainer = findViewById(R.id.linearlayoutcontainer)
//        val transition = LayoutTransition()
//        val outLeft = PropertyValuesHolder.ofInt("left", 0, 0)
//        val outTop = PropertyValuesHolder.ofInt("top", 0, 0)
//
//        val frame0 = Keyframe.ofFloat(0f, 0f)
//        val frame1 = Keyframe.ofFloat(0.1f, -20f)
//        val frame2 = Keyframe.ofFloat(0.2f, 20f)
//        val frame3 = Keyframe.ofFloat(0.3f, -20f)
//        val frame4 = Keyframe.ofFloat(0.4f, 20f)
//        val frame5 = Keyframe.ofFloat(0.5f, -20f)
//        val frame6 = Keyframe.ofFloat(0.6f, 20f)
//        val frame7 = Keyframe.ofFloat(0.7f, -20f)
//        val frame8 = Keyframe.ofFloat(0.8f, 20f)
//        val frame9 = Keyframe.ofFloat(0.9f, -20f)
//        val frame10 = Keyframe.ofFloat(1f, 0f)
//        val mPropertyValuesHolder = PropertyValuesHolder.ofKeyframe(
//            "rotation",
//            frame0,
//            frame1,
//            frame2,
//            frame3,
//            frame4,
//            frame5,
//            frame6,
//            frame7,
//            frame8,
//            frame9,
//            frame10
//        )
//        val mObjectAnimatorChangeDisAppearing =
//            ObjectAnimator.ofPropertyValuesHolder(this, outLeft, outTop, mPropertyValuesHolder)
//        transition.setAnimator(
//            LayoutTransition.CHANGE_APPEARING,
//            mObjectAnimatorChangeDisAppearing
//        )
//        transition.setAnimator(
//            LayoutTransition.CHANGE_DISAPPEARING,
//            mObjectAnimatorChangeDisAppearing
//        )
//        transition.setAnimator(
//            LayoutTransition.CHANGING,
//            mObjectAnimatorChangeDisAppearing
//        )
//        transition.setAnimator(
//            LayoutTransition.APPEARING,
//            mObjectAnimatorChangeDisAppearing
//        )
//        transition.setAnimator(
//            LayoutTransition.DISAPPEARING,
//            mObjectAnimatorChangeDisAppearing
//        )
//
//        transition.addTransitionListener(object : LayoutTransition.TransitionListener {
//            override fun startTransition(
//                transition: LayoutTransition?,
//                container: ViewGroup,
//                view: View,
//                transitionType: Int
//            ) {
//                Log.d(
//                    "qijian",
//                    "start:" + "transitionType:" + transitionType + "count:" + container.childCount + "view:" + view.javaClass.name
//                )
//            }
//
//            override fun endTransition(
//                transition: LayoutTransition?,
//                container: ViewGroup,
//                view: View,
//                transitionType: Int
//            ) {
//                Log.d(
//                    "qijian",
//                    "end:" + "transitionType:" + transitionType + "count:" + container.childCount + "view:" + view.javaClass.name
//                )
//            }
//        })
//
//        linearLayoutContainer!!.layoutTransition = transition
//
//
//        findViewById<Button>(R.id.add_btn).setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                addButtonView()
//            }
//        })
//        findViewById<Button>(R.id.remove_btn).setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                removeButtonView()
//            }
//        })
    }
//
//    private fun addButtonView() {
//        i++
//        val button = Button(this)
//        button.text = "button$i"
//        val params = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.WRAP_CONTENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        button.layoutParams = params
//        linearLayoutContainer!!.addView(button, 0)
//    }
//
//    private fun removeButtonView() {
//        if (i > 0) {
//            linearLayoutContainer!!.removeViewAt(0)
//        }
//        i--
//    }
}

//class CharaEvaluator : TypeEvaluator<Char> {
//    override fun evaluate(
//        fraction: Float,
//        startValue: Char?,
//        endValue: Char?
//    ): Char {
//        val startInt : Int = (startValue as Char).toInt()
//        val endInt : Int = (endValue as Char).toInt()
//        val curInt: Int = ((startInt + fraction * (endInt - startInt)).toInt())
//        var result: Char = curInt.toChar()
//        return result
//    }
//
//}
