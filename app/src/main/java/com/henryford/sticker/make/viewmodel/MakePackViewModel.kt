package com.henryford.sticker.make.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henryford.sticker.bean.PageInfoBean
import com.henryford.sticker.main.bean.MainIndicatorBean
import com.henryford.sticker.make.adapter.MakePackUnAddAdapter
import com.henryford.sticker.make.bean.UnAddStickerBean
import com.henryford.sticker.mine.bean.MineStickerBean

class MakePackViewModel : ViewModel() {
    var makePackStickers = MutableLiveData<ArrayList<UnAddStickerBean>>()
    fun getPackStickers(){

        var list = ArrayList<UnAddStickerBean>()
        var innerStickerBean = UnAddStickerBean("","New Stickers",MakePackUnAddAdapter.TYPE_TITLE,false)
        list.add(innerStickerBean)
        innerStickerBean = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201601%2F03%2F20160103122852_4VnvW.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461263&t=a695a163f65330b1412b33bfe386d25f","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean)
        innerStickerBean = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.111cn.net%2Fattachment%2Fart%2F164738%2Fa82090822e.jpg&refer=http%3A%2F%2Fimg.111cn.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461254&t=fa947565b7a485b03e410a35f69709d7","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean)
        innerStickerBean = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwx1.sinaimg.cn%2Flarge%2F0066wpcPly1fmqoez2959j30hs0hsdha.jpg&refer=http%3A%2F%2Fwx1.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461256&t=456c64889e18f97b26f7cd1bcb24f07c","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean)
        innerStickerBean = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F19%2F20181119151406_wngbm.jpg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461259&t=de5aa39e1242b93c99a103840862a911","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean)
        innerStickerBean = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F06%2F20170806145505_3zLTd.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461261&t=5ff4cd63f0f3b2bb2a9d5469b3848fcf","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean)

        innerStickerBean = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201601%2F03%2F20160103122852_4VnvW.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461263&t=a695a163f65330b1412b33bfe386d25f","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean)
        innerStickerBean = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.111cn.net%2Fattachment%2Fart%2F164738%2Fa82090822e.jpg&refer=http%3A%2F%2Fimg.111cn.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461254&t=fa947565b7a485b03e410a35f69709d7","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean)
        innerStickerBean = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwx1.sinaimg.cn%2Flarge%2F0066wpcPly1fmqoez2959j30hs0hsdha.jpg&refer=http%3A%2F%2Fwx1.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461256&t=456c64889e18f97b26f7cd1bcb24f07c","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean)
        innerStickerBean = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F19%2F20181119151406_wngbm.jpg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461259&t=de5aa39e1242b93c99a103840862a911","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean)
        innerStickerBean = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F06%2F20170806145505_3zLTd.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461261&t=5ff4cd63f0f3b2bb2a9d5469b3848fcf","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean)


        var innerStickerBean2 = UnAddStickerBean("","Packed Stickers",MakePackUnAddAdapter.TYPE_TITLE,false)
        list.add(innerStickerBean2)
        innerStickerBean2 = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201601%2F03%2F20160103122852_4VnvW.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461263&t=a695a163f65330b1412b33bfe386d25f","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean2)
        innerStickerBean2 = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.111cn.net%2Fattachment%2Fart%2F164738%2Fa82090822e.jpg&refer=http%3A%2F%2Fimg.111cn.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461254&t=fa947565b7a485b03e410a35f69709d7","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean2)
        innerStickerBean2 = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwx1.sinaimg.cn%2Flarge%2F0066wpcPly1fmqoez2959j30hs0hsdha.jpg&refer=http%3A%2F%2Fwx1.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461256&t=456c64889e18f97b26f7cd1bcb24f07c","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean2)
        innerStickerBean2 = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F19%2F20181119151406_wngbm.jpg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461259&t=de5aa39e1242b93c99a103840862a911","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean2)
        innerStickerBean2 = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F06%2F20170806145505_3zLTd.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461261&t=5ff4cd63f0f3b2bb2a9d5469b3848fcf","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean2)

        innerStickerBean2 = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201601%2F03%2F20160103122852_4VnvW.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461263&t=a695a163f65330b1412b33bfe386d25f","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean2)
        innerStickerBean2 = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.111cn.net%2Fattachment%2Fart%2F164738%2Fa82090822e.jpg&refer=http%3A%2F%2Fimg.111cn.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461254&t=fa947565b7a485b03e410a35f69709d7","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean2)
        innerStickerBean2 = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwx1.sinaimg.cn%2Flarge%2F0066wpcPly1fmqoez2959j30hs0hsdha.jpg&refer=http%3A%2F%2Fwx1.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461256&t=456c64889e18f97b26f7cd1bcb24f07c","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean2)
        innerStickerBean2 = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F19%2F20181119151406_wngbm.jpg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461259&t=de5aa39e1242b93c99a103840862a911","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean2)
        innerStickerBean2 = UnAddStickerBean("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F06%2F20170806145505_3zLTd.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629461261&t=5ff4cd63f0f3b2bb2a9d5469b3848fcf","",MakePackUnAddAdapter.TYPE_ITEM,false)
        list.add(innerStickerBean2)
        makePackStickers.value = list

    }
}