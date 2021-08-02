package com.henryford.sticker.user

import com.henryford.sticker.bean.UserBean

object UserModel {
    var userBean:UserBean = UserBean()
    fun userId():String{
        return userBean.userId
    }

    fun isLogin():Boolean{
        return userBean.userId != UserBean.USER_ID_UNLOGIN
    }
}