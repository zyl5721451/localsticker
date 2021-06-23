package com.henryford.sticker.util

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object ReportManager {
    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics



    fun logClickEvent(id:String, name:String){
        val bundle = Bundle()
        bundle.putString("id", id)
        firebaseAnalytics.logEvent(name,bundle)
    }

}