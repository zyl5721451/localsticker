package com.henryford.sticker.util

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object ReportManager {
    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics



    fun logEvent(name:String,bundle: Bundle){
        firebaseAnalytics.logEvent(name,bundle)
    }

}