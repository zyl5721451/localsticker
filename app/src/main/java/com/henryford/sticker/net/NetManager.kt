package com.henryford.sticker.net

import com.henryford.sticker.main.viewmodel.MainStickerApi

object NetManager {
    var mainStickerApi = RetrofitClient.getDefaultRetrofit().create(MainStickerApi::class.java)
}