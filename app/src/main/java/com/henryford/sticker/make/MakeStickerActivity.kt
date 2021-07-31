package com.henryford.sticker.make

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.allen.commlib.arouter.ARouterPage
import com.henryford.sticker.BaseActivity
import com.henryford.sticker.R
import com.henryford.sticker.adapter.BaseAdapter
import com.henryford.sticker.make.adapter.MakePackAddedAdapter
import com.henryford.sticker.make.adapter.MakePackUnAddAdapter
import com.henryford.sticker.make.bean.UnAddStickerBean
import com.henryford.sticker.make.viewmodel.MakePackViewModel
import kotlin.time.milliseconds

@Route(path = ARouterPage.MAKE_ACTIVITY)
class MakeStickerActivity : BaseActivity() {
    private lateinit var addedAdapter: MakePackAddedAdapter
    private lateinit var makePackViewModel: MakePackViewModel
    lateinit var toolBar:Toolbar
    lateinit var etTitle:EditText
    lateinit var tvAddTips:TextView
    lateinit var addedRecyclerView:RecyclerView
    lateinit var tvAddNum:TextView
    lateinit var tvScan:TextView
    lateinit var progress:ProgressBar
    lateinit var unAddRecyclerView: RecyclerView
    lateinit var btnMake:Button
    lateinit var packAdapter:MakePackUnAddAdapter
    override fun initToolbar() {
        toolBar.setNavigationOnClickListener {
            finish()
        }
    }


    override fun setListener() {
        btnMake.setOnClickListener {

        }
        tvScan.setOnClickListener {
            tvScan.visibility = View.GONE
            progress.visibility = View.VISIBLE
        }
        makePackViewModel.makePackStickers.observe(this, Observer {
            packAdapter.addData(it)
        })

        packAdapter.onItemClickListener = object :BaseAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                var sticker = packAdapter.mDatas[position]
                updateAddedAdapter(sticker)
                sticker.selected = !sticker.selected
                packAdapter.notifyItemChanged(position)
            }

        }

    }

    private fun updateAddedAdapter(sticker: UnAddStickerBean) {
        if (sticker.selected) {
            addedAdapter.mDatas.remove(sticker)
            addedAdapter.notifyItemRemoved(addedAdapter.mDatas.indexOf(sticker))
        } else {
            addedAdapter.addData(sticker)
            addedAdapter.notifyItemInserted(addedAdapter.mDatas.indexOf(sticker))
        }
        if (addedAdapter.mDatas.isEmpty()) {
            tvAddNum.visibility = View.GONE
            tvAddTips.visibility = View.VISIBLE
            btnMake.text = getString(R.string.btn_make_pack)
        } else {
            tvAddNum.visibility = View.VISIBLE
            tvAddTips.visibility = View.GONE
            tvAddNum.text = addedAdapter.mDatas.size.toString() + "/30"
            btnMake.text = String.format(getString(R.string.btn_make_pack_num),addedAdapter.mDatas.size)
            if(addedAdapter.mDatas.size>=3){
                btnMake.setBackgroundResource(R.drawable.bg_make_green)
            }else {
                btnMake.setBackgroundResource(R.drawable.bg_make_gray)
            }
        }
    }

    override fun initData() {
        makePackViewModel = ViewModelProvider(this).get(MakePackViewModel::class.java)
        makePackViewModel.getPackStickers()
    }

    override fun initView() {
        toolBar = findViewById(R.id.toolbar)
        etTitle = findViewById(R.id.et_title)
        tvAddTips = findViewById(R.id.tv_add_tips)
        addedRecyclerView  = findViewById(R.id.added_recycle)
        tvAddNum = findViewById(R.id.tv_add_num)
        tvScan  = findViewById(R.id.tv_scan)
        progress = findViewById(R.id.progress)
        unAddRecyclerView = findViewById(R.id.unad_recyclerview)
        btnMake = findViewById(R.id.btn_make)

        initAddedRecyclerView()

        initPackRecycleView()


    }

    private fun initAddedRecyclerView() {
        addedAdapter = MakePackAddedAdapter()

        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        addedRecyclerView.layoutManager = linearLayoutManager
        addedRecyclerView.adapter = addedAdapter
    }

    private fun initPackRecycleView() {

        packAdapter = MakePackUnAddAdapter()

        var gridLayoutManager = GridLayoutManager(this, 4)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (packAdapter.getItemViewType(position) == MakePackUnAddAdapter.TYPE_TITLE) return 4 else return 1
            }

        }
        unAddRecyclerView.layoutManager = gridLayoutManager
        unAddRecyclerView.adapter = packAdapter
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_make
    }
}