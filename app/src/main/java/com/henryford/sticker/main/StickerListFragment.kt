package com.henryford.sticker.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.henryford.sticker.BaseFragment
import com.henryford.sticker.R
import com.henryford.sticker.main.bean.MainIndicatorBean
import com.henryford.sticker.util.LogUtil
import kotlinx.android.synthetic.main.fragment_sticker_list.*

class StickerListFragment : BaseFragment() {
    val TAG = StickerListFragment::class.java.simpleName
    var mainIndicatorBean: MainIndicatorBean? = null
    var tvText:TextView? = null
    override fun getRootViewResource(): Int {
        return R.layout.fragment_sticker_list
    }
    companion object{
        val ARGUMENT_KEY = "argument_key"
        fun newInstance(mainIndicatorBean: MainIndicatorBean):StickerListFragment{
            var fragment = StickerListFragment()
            var bundle = Bundle()
            bundle.putSerializable(ARGUMENT_KEY,mainIndicatorBean)
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun initView() {
        tvText = rootView.findViewById(R.id.tv_test)
    }

    override fun initData() {
        mainIndicatorBean = arguments?.getSerializable(ARGUMENT_KEY) as MainIndicatorBean?
        tvText?.text = mainIndicatorBean?.name
        LogUtil.d(TAG,"initData"+mainIndicatorBean?.name)
    }

    override fun setListener() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtil.d(TAG,"onCreateView"+savedInstanceState)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        LogUtil.d(TAG,"onDestroy"+mainIndicatorBean?.name)
        super.onDestroy()
    }

    override fun onDestroyView() {
        LogUtil.d(TAG,"onDestroyView"+mainIndicatorBean?.name)
        super.onDestroyView()
    }

    override fun onResume() {
        LogUtil.d(TAG,"onResume"+mainIndicatorBean?.name)
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.d(TAG,"onCreate"+mainIndicatorBean?.name)
        super.onCreate(savedInstanceState)
    }
}