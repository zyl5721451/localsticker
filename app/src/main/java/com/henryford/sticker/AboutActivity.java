package com.henryford.sticker;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import java.util.concurrent.ExecutionException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class AboutActivity extends AppCompatActivity {
    private static final String TAG = AboutActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        FrameLayout twitter=findViewById(R.id.tweet);
        FrameLayout play=findViewById(R.id.googleplay);
        addAdIdInfo();
        twitter.setOnClickListener(v -> openWeb("https://twitter.com/CompanyAbsolute"));
        play.setOnClickListener(v ->
            openStore());
        debugEntrence();
    }

    private void addAdIdInfo() {
        TextView tvAdID = findViewById(R.id.tv_ad_id);
        try {
            if(BuildConfig.DEBUG){
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                        AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(AboutActivity.this);
                        String advertisingId = info.getId();
                        emitter.onNext(advertisingId);
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Throwable {
                                tvAdID.setVisibility(View.VISIBLE);
                                tvAdID.setText(s);
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void debugEntrence() {
        Button btnDebug = findViewById(R.id.btn_debug);
        if(BuildConfig.DEBUG){
            btnDebug.setVisibility(View.VISIBLE);
        }
        btnDebug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutActivity.this, TestAdActivity.class));
            }
        });
    }

    private void openWeb(String website) {
        Uri uri = Uri.parse(website);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    private void openStore(){
        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
        }
    }

}
