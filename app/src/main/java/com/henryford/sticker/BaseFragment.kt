package com.henryford.sticker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    var rootView:ViewGroup? = null
    private var firstLoad:Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(rootView == null){
            rootView = inflater.inflate(getRootViewResource(), container, false) as ViewGroup
            initView()
            initData()
            setListener()
        }
        return rootView
    }

    override fun onResume() {
        super.onResume()
        if(firstLoad){
            firstLoadData()
            firstLoad = false
        }
    }

    protected abstract fun firstLoadData()

    protected abstract fun getRootViewResource(): Int

    protected abstract fun initView()

    protected abstract fun initData()

    protected abstract fun setListener()
}