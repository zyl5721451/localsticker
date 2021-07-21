package com.henryford.sticker.mine.bean

class MineHeaderBean {
    var userId:String = ""
    var userIcon:String = ""
    var userName:String = ""
    var packNum:Int = 0
    var stickerNum:Int = 0

    constructor(userId: String,userIcon: String, userName: String, packNum: Int, stickerNum: Int) {
        this.userId = userId
        this.userIcon = userIcon
        this.userName = userName
        this.packNum = packNum
        this.stickerNum = stickerNum
    }
}