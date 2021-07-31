package com.henryford.sticker.make.bean

import com.henryford.sticker.make.adapter.MakePackUnAddAdapter


class UnAddStickerBean {

    var path:String = ""
    var title:String = ""
    var type:Int = MakePackUnAddAdapter.TYPE_TITLE
    var selected:Boolean = false

    constructor(path: String, title: String, type: Int, selected: Boolean) {
        this.path = path
        this.title = title
        this.type = type
        this.selected = selected
    }
}