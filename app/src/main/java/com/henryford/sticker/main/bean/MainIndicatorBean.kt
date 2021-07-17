package com.henryford.sticker.main.bean

import java.io.Serializable

class MainIndicatorBean :Serializable{

    var id:Long = 0L
    var name:String = ""
    var type:Int = 0


    constructor(id: Long, name: String,type:Int) {
        this.id = id
        this.name = name
        this.type = type
    }
}