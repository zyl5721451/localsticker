package com.henryford.sticker.report

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.allen.commlib.arouter.ARouterPage
import com.henryford.sticker.BaseActivity
import com.henryford.sticker.R
import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.fragment_mine.*

@Route(path = ARouterPage.REPORT_ACTIVITY)
class ReportActivity : BaseActivity() {
    lateinit var rg:RadioGroup
    lateinit var rab1:RadioButton
    lateinit var rab2:RadioButton
    lateinit var rab3:RadioButton
    lateinit var rab4:RadioButton
    lateinit var rab5:RadioButton
    lateinit var btnSubmit:Button
    lateinit var editText: EditText
    override fun initToolbar() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun setListener() {
        btnSubmit.setOnClickListener {

        }
        rg.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rb_1 ->{
                    editText.visibility = View.GONE
                }
                R.id.rb_2 ->{
                    editText.visibility = View.GONE
                }
                R.id.rb_3 ->{
                    editText.visibility = View.GONE
                }
                R.id.rb_4 ->{
                    editText.visibility = View.GONE
                }
                R.id.rb_5 ->{
                    editText.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun initData() {
    }



    override fun initView() {
        rg = findViewById(R.id.rg_why)
        rab1 = findViewById(R.id.rb_1)
        rab2 = findViewById(R.id.rb_2)
        rab3 = findViewById(R.id.rb_3)
        rab4 = findViewById(R.id.rb_4)
        rab5 = findViewById(R.id.rb_5)
        btnSubmit = findViewById(R.id.btn_report)
        editText = findViewById(R.id.edittext)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_report
    }
}