package com.henryford.sticker.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henryford.sticker.main.bean.MainIndicatorBean

class MainStickerViewModel : ViewModel() {
    var indicatorDataList = MutableLiveData<ArrayList<MainIndicatorBean>>()

    fun loadIndicatorList(){
        var tempData = ArrayList<MainIndicatorBean>()
        tempData.add(MainIndicatorBean(0,"From"))
        tempData.add(MainIndicatorBean(1,"For You"))
        tempData.add(MainIndicatorBean(2,"HD"))
        tempData.add(MainIndicatorBean(3,"Quotidiano"))
        tempData.add(MainIndicatorBean(4,"✨\uD83D\uDE28\uD83C\uDF89"))
        tempData.add(MainIndicatorBean(5,"For You1"))
        tempData.add(MainIndicatorBean(6,"For You2"))
        tempData.add(MainIndicatorBean(7,"For You3"))
        tempData.add(MainIndicatorBean(8,"For You4"))
        indicatorDataList.value = tempData
    }
}