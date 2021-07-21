package com.henryford.sticker.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henryford.sticker.bean.PageInfoBean
import com.henryford.sticker.main.bean.MainStickerBean
import com.henryford.sticker.mine.bean.MineHeaderBean
import com.henryford.sticker.mine.bean.MinePackBean
import com.henryford.sticker.mine.bean.MineStickerBean
import com.henryford.sticker.mine.widget.MineHeader

class MineViewModel : ViewModel() {
    var mineHeader = MutableLiveData<MineHeaderBean>()
    var packList = MutableLiveData<MinePackBean>()
    var stickerList = MutableLiveData<MineStickerBean>()
    fun getMineData(){
        var mineHeaderBean = MineHeaderBean("1"
            ,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F010cb65e205811a80120a895cf85b3.jpg%401280w_1l_2o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629207320&t=7be548d3098f152a953921800faa4503"
            ,"YiLongZhang",2,6)
        mineHeader.value = mineHeaderBean
    }

    fun getPackList(){
        var packBean = MinePackBean()
        var pageInfo = PageInfoBean()
        pageInfo.hasMore = false
        pageInfo.score = 0.0
        pageInfo.totalCount = 50

        var list = ArrayList<MinePackBean.InnerMinePackBean>()
        var iconList = ArrayList<String>()
        iconList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201601%2F03%2F20160103122852_4VnvW.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461263&t=a695a163f65330b1412b33bfe386d25f")
        iconList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.111cn.net%2Fattachment%2Fart%2F164738%2Fa82090822e.jpg&refer=http%3A%2F%2Fimg.111cn.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461254&t=fa947565b7a485b03e410a35f69709d7")
        iconList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwx1.sinaimg.cn%2Flarge%2F0066wpcPly1fmqoez2959j30hs0hsdha.jpg&refer=http%3A%2F%2Fwx1.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461256&t=456c64889e18f97b26f7cd1bcb24f07c")
        iconList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F19%2F20181119151406_wngbm.jpg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461259&t=de5aa39e1242b93c99a103840862a911")
        iconList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F06%2F20170806145505_3zLTd.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461261&t=5ff4cd63f0f3b2bb2a9d5469b3848fcf")
        var innerPackBean = MinePackBean.InnerMinePackBean(122,"SmartPack","张小龙",299,0,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202106%2F24%2F20210624115425_9a473.thumb.1000_0.gif&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461197&t=1925082e495a14e19dc7760c30468502",iconList)
        list.add(innerPackBean)
        list.add(innerPackBean)
        list.add(innerPackBean)

        packBean.minePackList = list
        packBean.pageInfo = pageInfo
        packList.value = packBean

    }

    fun getStickerList(){
        var packBean = MineStickerBean()
        var pageInfo = PageInfoBean()
        pageInfo.hasMore = false
        pageInfo.score = 0.0
        pageInfo.totalCount = 50

        var list = ArrayList<MineStickerBean.InnerMineStickerBean>()
        var innerStickerBean = MineStickerBean.InnerMineStickerBean(122,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202106%2F24%2F20210624115425_9a473.thumb.1000_0.gif&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461197&t=1925082e495a14e19dc7760c30468502")
        list.add(innerStickerBean)
        innerStickerBean = MineStickerBean.InnerMineStickerBean(122,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201601%2F03%2F20160103122852_4VnvW.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461263&t=a695a163f65330b1412b33bfe386d25f")
        list.add(innerStickerBean)
        innerStickerBean = MineStickerBean.InnerMineStickerBean(122,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.111cn.net%2Fattachment%2Fart%2F164738%2Fa82090822e.jpg&refer=http%3A%2F%2Fimg.111cn.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461254&t=fa947565b7a485b03e410a35f69709d7")
        list.add(innerStickerBean)
        innerStickerBean = MineStickerBean.InnerMineStickerBean(122,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwx1.sinaimg.cn%2Flarge%2F0066wpcPly1fmqoez2959j30hs0hsdha.jpg&refer=http%3A%2F%2Fwx1.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461256&t=456c64889e18f97b26f7cd1bcb24f07c")
        list.add(innerStickerBean)
        innerStickerBean = MineStickerBean.InnerMineStickerBean(122,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F19%2F20181119151406_wngbm.jpg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461259&t=de5aa39e1242b93c99a103840862a911")
        list.add(innerStickerBean)
        innerStickerBean = MineStickerBean.InnerMineStickerBean(122,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F06%2F20170806145505_3zLTd.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461261&t=5ff4cd63f0f3b2bb2a9d5469b3848fcf")
        list.add(innerStickerBean)

        innerStickerBean = MineStickerBean.InnerMineStickerBean(122,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201601%2F03%2F20160103122852_4VnvW.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461263&t=a695a163f65330b1412b33bfe386d25f")
        list.add(innerStickerBean)
        innerStickerBean = MineStickerBean.InnerMineStickerBean(122,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.111cn.net%2Fattachment%2Fart%2F164738%2Fa82090822e.jpg&refer=http%3A%2F%2Fimg.111cn.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461254&t=fa947565b7a485b03e410a35f69709d7")
        list.add(innerStickerBean)
        innerStickerBean = MineStickerBean.InnerMineStickerBean(122,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwx1.sinaimg.cn%2Flarge%2F0066wpcPly1fmqoez2959j30hs0hsdha.jpg&refer=http%3A%2F%2Fwx1.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461256&t=456c64889e18f97b26f7cd1bcb24f07c")
        list.add(innerStickerBean)
        innerStickerBean = MineStickerBean.InnerMineStickerBean(122,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F19%2F20181119151406_wngbm.jpg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461259&t=de5aa39e1242b93c99a103840862a911")
        list.add(innerStickerBean)
        innerStickerBean = MineStickerBean.InnerMineStickerBean(122,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F06%2F20170806145505_3zLTd.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461261&t=5ff4cd63f0f3b2bb2a9d5469b3848fcf")
        list.add(innerStickerBean)
        packBean.mineStickerkList = list
        packBean.pageInfo = pageInfo
        stickerList.value = packBean

    }
}