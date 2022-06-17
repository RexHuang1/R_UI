package com.dev.rexhuang.rui.slider

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev.rexhuang.rui.R
import com.dev.rexhuang.rui.item.RViewHolder
import kotlinx.android.synthetic.main.r_slider_menu_item.view.*

/**
 **  created by RexHuang
 **  on 2020/8/21
 */
class RSliderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var menuItemAttr: MenuItemAttr
    private val MENU_WIDTH = applyUnit(TypedValue.COMPLEX_UNIT_DIP, 100f)
    private val MENU_HEIGHT = applyUnit(TypedValue.COMPLEX_UNIT_DIP, 45f)
    private val MENU_TEXT_SIZE = applyUnit(TypedValue.COMPLEX_UNIT_SP, 14f)

    private val TEXT_COLOR_NORMAL = Color.parseColor("#666666")
    private val TEXT_COLOR_SELECT = Color.parseColor("#DD3127")

    private val BG_COLOR_NORMAL = Color.parseColor("#F7F8F9")
    private val BG_COLOR_SELECT = Color.parseColor("#FFFFFF")

    private val MENU_ITEM_LAYOUT_RES_ID = R.layout.r_slider_menu_item
    private val CONTENT_ITEM_LAYOUT_RES_ID = R.layout.r_slider_content_item

    val menuView = RecyclerView(context)
    val contentView = RecyclerView(context)

    init {
        menuItemAttr = parseMenuItemAttr(attrs)

        orientation = HORIZONTAL

        menuView.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        menuView.overScrollMode = View.OVER_SCROLL_NEVER
        menuView.itemAnimator = null

        contentView.layoutParams =
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        contentView.overScrollMode = View.OVER_SCROLL_NEVER
        contentView.itemAnimator = null

        addView(menuView)
        addView(contentView)
    }

    fun bindMenuView(
        layoutIdRes: Int = MENU_ITEM_LAYOUT_RES_ID,
        itemCount: Int,
        onBindView: (RViewHolder, Int) -> Unit,
        onItemClick: (RViewHolder, Int) -> Unit
    ) {
        menuView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        menuView.adapter = MenuAdapter(layoutIdRes, itemCount, onBindView, onItemClick)
    }

    fun bindContentView(
        layoutIdRes: Int = CONTENT_ITEM_LAYOUT_RES_ID,
        itemCount: Int,
        itemDecoration: RecyclerView.ItemDecoration?,
        layoutManager: RecyclerView.LayoutManager,
        onBindView: (RViewHolder, Int) -> Unit,
        onItemClick: (RViewHolder, Int) -> Unit
    ) {
        if (contentView.layoutManager == null) {
            contentView.layoutManager = layoutManager
            contentView.adapter = ContentAdapter(layoutIdRes)
            itemDecoration?.let {
                contentView.addItemDecoration(it)
            }
        }
        val contentAdapter = contentView.adapter as ContentAdapter
        contentAdapter.update(itemCount, onBindView, onItemClick)
        contentAdapter.notifyDataSetChanged()

        contentView.scrollToPosition(0)
    }

    inner class ContentAdapter(val layoutIdRes: Int) : RecyclerView.Adapter<RViewHolder>() {
        private lateinit var onItemClick: (RViewHolder, Int) -> Unit
        private lateinit var onBindView: (RViewHolder, Int) -> Unit
        private var count: Int = 0
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RViewHolder {
            val itemView = LayoutInflater.from(context).inflate(layoutIdRes, parent, false)
            val remainSpace = width - paddingLeft - paddingRight - menuItemAttr.width
            val layoutManager = (parent as RecyclerView).layoutManager
            var spanCount = 0
            if (layoutManager is GridLayoutManager) {
                spanCount = layoutManager.spanCount
            } else if (layoutManager is StaggeredGridLayoutManager) {
                spanCount = layoutManager.spanCount
            }
            if (spanCount > 0) {
                val itemWidth = remainSpace / spanCount
                //创建content itemview  ，设置它的layoutparams 的原因，是防止图片未加载出来之前，列表滑动时 上下闪动的效果

                itemView.layoutParams = RecyclerView.LayoutParams(itemWidth, itemWidth)
            }
            return RViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return count
        }

        override fun onBindViewHolder(holder: RViewHolder, position: Int) {
            onBindView(holder, position)
            holder.itemView.setOnClickListener {
                onItemClick(holder, position)
            }
        }

        fun update(
            itemCount: Int,
            onBindView: (RViewHolder, Int) -> Unit,
            onItemClick: (RViewHolder, Int) -> Unit
        ) {
            this.count = itemCount
            this.onBindView = onBindView
            this.onItemClick = onItemClick
        }

    }

    inner class MenuAdapter(
        val layoutIdRes: Int,
        val count: Int,
        val onBindView: (RViewHolder, Int) -> Unit,
        val onItemClick: (RViewHolder, Int) -> Unit
    ) : RecyclerView.Adapter<RViewHolder>() {
        private var currentSelectIndex: Int = 0
        private var lastSelectIndex: Int = 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RViewHolder {
            val itemView = LayoutInflater.from(context).inflate(layoutIdRes, parent, false)
            val params = RecyclerView.LayoutParams(menuItemAttr.width, menuItemAttr.height)
            itemView.layoutParams = params
            itemView.setBackgroundColor(menuItemAttr.normalBackgroundColor)
            itemView.findViewById<TextView>(R.id.menu_item_title)
                ?.setTextColor(menuItemAttr.textColor)
            itemView.findViewById<ImageView>(R.id.menu_item_indicator)
                ?.setImageDrawable(menuItemAttr.indicator)
            return RViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return count
        }

        override fun onBindViewHolder(holder: RViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                currentSelectIndex = position
                notifyItemChanged(position)
                notifyItemChanged(lastSelectIndex)
            }

            if (currentSelectIndex == position) {
                onItemClick(holder, position)
                lastSelectIndex = currentSelectIndex
            }
            applyItemAttr(position, holder)
            onBindView(holder, position)
        }

        private fun applyItemAttr(position: Int, holder: RViewHolder) {
            val selected = position == currentSelectIndex
            val titleView = holder.itemView.menu_item_title
            val indicatorView = holder.itemView.menu_item_indicator

            indicatorView?.visibility = if (selected) View.VISIBLE else View.GONE
            titleView?.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                if (selected) menuItemAttr.selectTextSize.toFloat() else menuItemAttr.textSize.toFloat()
            )
            holder.itemView.setBackgroundColor(if (selected) menuItemAttr.selectBackgroundColor else menuItemAttr.normalBackgroundColor)
            titleView?.isSelected = selected
        }

    }

    private fun parseMenuItemAttr(attrs: AttributeSet?): MenuItemAttr {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RSliderView)
        val menuItemWidth =
            typedArray.getDimensionPixelOffset(R.styleable.RSliderView_menuItemWidth, MENU_WIDTH)
        val menuItemHeight =
            typedArray.getDimensionPixelOffset(R.styleable.RSliderView_menuItemHeight, MENU_HEIGHT)
        val menuItemTextSize =
            typedArray.getDimensionPixelSize(
                R.styleable.RSliderView_menuItemTextSize,
                MENU_TEXT_SIZE
            )
        val menuItemSelectTextSize =
            typedArray.getDimensionPixelSize(
                R.styleable.RSliderView_menuItemSelectTextSize,
                MENU_TEXT_SIZE
            )
        val menuItemTextColor =
            typedArray.getColorStateList(R.styleable.RSliderView_menuItemTextColor)
                ?: generateColorStateList()
        val menuItemIndicator =
            typedArray.getDrawable(R.styleable.RSliderView_menuItemIndicator)
                ?: ContextCompat.getDrawable(context, R.drawable.shape_r_slider_indicator)
        val menuItemBackgroundColor =
            typedArray.getColor(R.styleable.RSliderView_menuItemBackgroundColor, BG_COLOR_NORMAL)
        val menuItemSelectBackgroundColor =
            typedArray.getColor(R.styleable.RSliderView_menuItemBackgroundColor, BG_COLOR_SELECT)
        typedArray.recycle()

        return MenuItemAttr(
            menuItemWidth,
            menuItemHeight,
            menuItemTextColor,
            menuItemSelectBackgroundColor,
            menuItemBackgroundColor,
            menuItemTextSize,
            menuItemSelectTextSize,
            menuItemIndicator
        )

    }

    private fun generateColorStateList(): ColorStateList {
        val states = Array(2) { IntArray(2) }
        val colors = IntArray(2)

        colors[0] = TEXT_COLOR_SELECT
        colors[1] = TEXT_COLOR_NORMAL

        states[0] = IntArray(1) { android.R.attr.state_selected }
        states[1] = IntArray(1)

        return ColorStateList(states, colors)
    }

    data class MenuItemAttr(
        val width: Int,
        val height: Int,
        val textColor: ColorStateList,
        val selectBackgroundColor: Int,
        val normalBackgroundColor: Int,
        val textSize: Int,
        val selectTextSize: Int,
        val indicator: Drawable?
    )

    private fun applyUnit(unit: Int, value: Float): Int {
        return TypedValue.applyDimension(unit, value, context.resources.displayMetrics).toInt()
    }
}