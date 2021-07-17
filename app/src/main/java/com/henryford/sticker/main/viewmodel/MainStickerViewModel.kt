package com.henryford.sticker.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henryford.sticker.bean.PageInfoBean
import com.henryford.sticker.main.bean.MainIndicatorBean
import com.henryford.sticker.main.bean.MainStickerBean
import com.henryford.sticker.main.bean.TagBean

class MainStickerViewModel : ViewModel() {
    var indicatorDataList = MutableLiveData<ArrayList<MainIndicatorBean>>()

    var tagList = MutableLiveData<TagBean>()
    var stickerList = MutableLiveData<MainStickerBean>()


    fun loadIndicatorList(){
        var tempData = ArrayList<MainIndicatorBean>()
        tempData.add(MainIndicatorBean(0,"From",1))
        tempData.add(MainIndicatorBean(1,"For You",2))
        tempData.add(MainIndicatorBean(2,"HD",3))
        tempData.add(MainIndicatorBean(3,"Quotidiano",3))
        tempData.add(MainIndicatorBean(4,"✨\uD83D\uDE28\uD83C\uDF89",3))
        tempData.add(MainIndicatorBean(5,"For You1",3))
        tempData.add(MainIndicatorBean(6,"For You2",3))
        tempData.add(MainIndicatorBean(7,"For You3",3))
        tempData.add(MainIndicatorBean(8,"For You4",3))
        indicatorDataList.value = tempData
    }

    fun loadTagBeanList(){
        var tempTag = TagBean()
        var tempList = ArrayList<TagBean.InnerTagBean>()
        tempList.add(TagBean.InnerTagBean(0,"#bom descanso"))
        tempList.add(TagBean.InnerTagBean(1,"#Tchau"))
        tempList.add(TagBean.InnerTagBean(2,"#Obrigado!"))
        tempList.add(TagBean.InnerTagBean(3,"#bom descanso"))
        tempList.add(TagBean.InnerTagBean(4,"#adeus"))
        tempList.add(TagBean.InnerTagBean(5,"#forca"))
        tempList.add(TagBean.InnerTagBean(6,"#De nada!"))
        tempList.add(TagBean.InnerTagBean(7,"#bom trabalho"))
        tempList.add(TagBean.InnerTagBean(8,"#serio"))
        tempList.add(TagBean.InnerTagBean(9,"#Ate amanha"))
        tempTag.tagList = tempList
        tagList.value = tempTag
    }
    fun loadForYouIndicatorList(){
        var tempData = ArrayList<MainIndicatorBean>()
        tempData.add(MainIndicatorBean(0,"New",3))
        tempData.add(MainIndicatorBean(1,"DIY",3))
        tempData.add(MainIndicatorBean(2,"Daily Top",3))
        tempData.add(MainIndicatorBean(3,"Trending",3))
        indicatorDataList.value = tempData
    }

    fun loadStickerList(){
        var mainStickerBean = MainStickerBean()
        var pageInfo = PageInfoBean()
        pageInfo.hasMore = false
        pageInfo.score = 0.0
        pageInfo.totalCount = 50

        var list = ArrayList<MainStickerBean.InnerMainStickerBean>()
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        list.add(MainStickerBean.InnerMainStickerBean(0,100,200,""))
        mainStickerBean.mainStickerList = list
        mainStickerBean.pageInfo = pageInfo
        stickerList.value = mainStickerBean
    }
}