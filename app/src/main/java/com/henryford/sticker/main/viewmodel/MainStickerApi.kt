package com.henryford.sticker.main.viewmodel

import com.henryford.sticker.bean.BaseResponse
import com.henryford.sticker.main.bean.MainStickerBean
import com.henryford.sticker.main.bean.TagBean
import com.henryford.sticker.net.RetrofitClient
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

interface MainStickerApi {
    @GET(RetrofitClient.BASE_HOST + "/v1/main/stickertag/list")
    fun getTagList(@HeaderMap headers: Map<String, Any>, @QueryMap params: Map<String, Any>): Observable<BaseResponse<TagBean>>

    @GET(RetrofitClient.BASE_HOST + "/v1/main/stickertag/list")
    fun getStickerList(@HeaderMap headers: Map<String, Any>, @QueryMap params: Map<String, Any>): Observable<BaseResponse<MainStickerBean>>

}