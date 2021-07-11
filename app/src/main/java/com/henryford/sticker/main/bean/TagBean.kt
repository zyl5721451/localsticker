package com.henryford.sticker.main.bean

class TagBean {
    var tagList:ArrayList<InnerTagBean> = ArrayList()

    class InnerTagBean{
        var id:Long = 0L
        var name:String = ""

        constructor(id: Long, name: String) {
            this.id = id
            this.name = name
        }
    }
}