package com.henryford.sticker.mine

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.allen.commlib.arouter.ARouterPage
import com.henryford.sticker.BaseActivity
import com.henryford.sticker.R
import com.henryford.sticker.mine.adapter.MinePackAdapter
import com.henryford.sticker.mine.adapter.MinePackDetailAdapter
import com.henryford.sticker.mine.adapter.MineStickerAdapter
import com.henryford.sticker.mine.bean.MinePackBean
import com.henryford.sticker.mine.viewmodel.MineViewModel
import com.henryford.sticker.mine.widget.PackDetailHeader


@Route(path = ARouterPage.PACK_DETAIL_ACTIVITY)
class PackDetailActivity : BaseActivity() {
    companion object{
        const val KEY_PACK = "key_pack"
    }
    lateinit var toolbar: Toolbar
    lateinit var recycleView:RecyclerView
    lateinit var header:PackDetailHeader
    private lateinit var minePackDetailAdapter:  MinePackDetailAdapter
    private lateinit var mineViewModel: MineViewModel

    @Autowired(name = KEY_PACK)
    @JvmField
    var packBean: MinePackBean.InnerMinePackBean? = null

    override fun setListener() {

    }

    override fun initData() {
        initToolBar()
        header.updateUI(packBean)
        minePackDetailAdapter.addData(packBean?.iconList)
    }

    private fun initToolBar() {
        toolbar.title = packBean?.name
        toolbar.navigationIcon = resources.getDrawable(R.drawable.thin_back)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_pack_detail,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_pack_delete->{

            }
            R.id.menu_pack_edite->{

            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun initView() {
        toolbar = findViewById(R.id.toolbar)
        recycleView = findViewById(R.id.recyclerview)
        header = findViewById(R.id.header)
        setSupportActionBar(toolbar)
        minePackDetailAdapter = MinePackDetailAdapter()
        recycleView?.layoutManager = GridLayoutManager(this,3)
        recycleView?.adapter = minePackDetailAdapter

    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_pack_detail
    }

}