package com.henryford.sticker.bean

class BaseResponse <T>{
    var status = 0
    var message = ""
    var data: T? = null
}