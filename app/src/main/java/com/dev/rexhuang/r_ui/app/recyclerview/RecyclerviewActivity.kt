package com.dev.rexhuang.r_ui.app.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.rexhuang.r_ui.databinding.ActivityRecyclerviewBinding
import com.dev.rexhuang.rui.item.RAdapter

class RecyclerviewActivity : AppCompatActivity() {
    private var binding: ActivityRecyclerviewBinding? = null
    var addIndex = 0
    var add_sp_index = 0
    var remove_sp_index = 0
    var add_arrayAdapter: ArrayAdapter<Int>? = null
    var remove_arrayAdapter: ArrayAdapter<Int>? = null
    var sp_items: MutableList<Int> = mutableListOf()
    var mAdapter: RAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        inflateRecyclerview()
        inflateSpinner()
        binding!!.addBtn.setOnClickListener {
            addItem()
            getSpItem()
        }

        binding!!.removeBtn.setOnClickListener {
            removeItem()
            getSpItem()
        }

    }

    /**
     * 按照位置增加item。
     * 例子中参数add_sp_index是下拉列表选择的位置（即插入item的位置）；
     * 参数item是需要插入的item，可通过集成RDataItem创建自定义Item
     * 参数notify为是否更新加入的数据
     */
    fun addItem() {
        val item = newItem("增加的Item: ${(addIndex++)}") ?: return
        mAdapter!!.addItemAt(add_sp_index, dataItem = item, notify = true)
    }

    /**
     * 按照位置删除item。
     * 例子中参数remove_sp_index是下拉列表选择的位置（即插入item的位置）；
     */
    fun removeItem() {
        mAdapter!!.removeItemAt(remove_sp_index)
    }

    private fun inflateSpinner() {
        getSpItem()
        binding!!.addSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                add_sp_index = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        add_arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            sp_items
        )

        add_arrayAdapter!!.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding!!.addSp.adapter = add_arrayAdapter


        binding!!.removeSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                remove_sp_index = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        remove_arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            sp_items
        )

        remove_arrayAdapter!!.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding!!.removeSp.adapter = remove_arrayAdapter
    }

    private fun inflateRecyclerview() {
        mAdapter = RAdapter(this)
        binding!!.rv.adapter = mAdapter
        binding!!.rv.layoutManager = LinearLayoutManager(this)
        val profileItem = TestDataItem("个人中心")
        profileItem.mItemOnClickListener = object : ItemOnClickListener {
            override fun onClick() {
                Toast.makeText(this@RecyclerviewActivity, "个人中心", Toast.LENGTH_SHORT).show()
            }
        }

        val vipItem = TestDataItem("会员权益")
        vipItem.mItemOnClickListener = object : ItemOnClickListener {
            override fun onClick() {
                Toast.makeText(this@RecyclerviewActivity, "会员权益", Toast.LENGTH_SHORT).show()

            }
        }

        val authenticationItem = TestDataItem("账户充值")
        authenticationItem.mItemOnClickListener = object : ItemOnClickListener {
            override fun onClick() {
                Toast.makeText(this@RecyclerviewActivity, "账户充值", Toast.LENGTH_SHORT).show()

            }
        }

        val degradeItem = TestDataItem("全局降级策略")
        degradeItem.mItemOnClickListener = object : ItemOnClickListener {
            override fun onClick() {
                Toast.makeText(this@RecyclerviewActivity, "全局降级策略", Toast.LENGTH_SHORT).show()
            }
        }

        mAdapter!!.addItemAt(dataItem = profileItem, notify = true)
        mAdapter!!.addItemAt(dataItem = vipItem, notify = true)
        mAdapter!!.addItemAt(dataItem = authenticationItem, notify = true)
        mAdapter!!.addItemAt(dataItem = degradeItem, notify = true)
    }

    private fun newItem(text: String): TestDataItem? {
        return TestDataItem(text)
    }

    private fun getSpItem() {
        sp_items.clear()
        for (i in 0..mAdapter!!.itemCount) {
            sp_items.add(i)
        }
        if (add_arrayAdapter != null) {
            add_arrayAdapter!!.notifyDataSetChanged()
        }

        if (remove_arrayAdapter != null) {
            remove_arrayAdapter!!.notifyDataSetChanged()
        }
    }
}