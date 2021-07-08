package com.henryford.sticker.main.bean

import java.io.Serializable

class MainIndicatorBean :Serializable{
    var id:Long = 0L
    var name:String = ""

    constructor(id: Long, name: String) {
        this.id = id
        this.name = name
    }
}