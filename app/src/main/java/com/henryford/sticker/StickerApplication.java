/* * Copyright (c) WhatsApp Inc. and its affiliates. * All rights reserved. * * This source code is licensed under the BSD-style license found in the * LICENSE file in the root directory of this source tree. */package com.henryford.sticker;import android.app.Application;import com.alibaba.android.arouter.launcher.ARouter;import com.facebook.drawee.backends.pipeline.Fresco;import com.google.android.gms.ads.MobileAds;import com.henryford.sticker.util.AdManager;public class StickerApplication extends Application {    @Override    public void onCreate() {        super.onCreate();        Fresco.initialize(this);        AdManager.INSTANCE.init(this);        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效            ARouter.openLog();     // 打印日志            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)        }        ARouter.init(this); // 尽可能早，推荐在Application中初始化    }}