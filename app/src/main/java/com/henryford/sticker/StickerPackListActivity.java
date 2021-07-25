/* * Copyright (c) WhatsApp Inc. and its affiliates. * All rights reserved. * * This source code is licensed under the BSD-style license found in the * LICENSE file in the root directory of this source tree. */package com.henryford.sticker;import android.animation.AnimatorSet;import android.animation.ObjectAnimator;import android.app.ActivityOptions;import android.content.Intent;import android.os.AsyncTask;import android.os.Build;import android.os.Bundle;import android.os.Handler;import android.view.Gravity;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.widget.FrameLayout;import android.widget.ImageSwitcher;import android.widget.ImageView;import android.widget.LinearLayout;import android.widget.TextSwitcher;import android.widget.TextView;import android.widget.Toast;import android.widget.ViewSwitcher;import androidx.annotation.StyleRes;import androidx.cardview.widget.CardView;import androidx.recyclerview.widget.DividerItemDecoration;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import com.henryford.sticker.cards.SliderAdapter;import com.henryford.sticker.util.BannerAd;import com.henryford.sticker.util.DecodeBitmapTask;import com.henryford.sticker.util.EventKey;import com.henryford.sticker.util.ReportManager;import com.ramotion.cardslider.CardSliderLayoutManager;import com.ramotion.cardslider.CardSnapHelper;import java.lang.ref.WeakReference;import java.util.ArrayList;import java.util.Arrays;import java.util.List;public class StickerPackListActivity extends AddStickerPackActivity {    public static final String EXTRA_STICKER_PACK_LIST_DATA = "sticker_pack_list";    private static final int STICKER_PREVIEW_DISPLAY_LIMIT = 5;    private LinearLayoutManager packLayoutManager;    private RecyclerView packRecyclerView;    private StickerPackListAdapter allStickerPacksListAdapter;    private WhiteListCheckAsyncTask whiteListCheckAsyncTask;    private ArrayList<StickerPack> stickerPackList;    private final static String[] countries = {"Wolverine","Punstar","Deadpool","Criminal Raccoon","Love","Ariana Grande","BBy Never Tell","Elon Musk","Harley Quinn","Homer Simpson","PUBG","Rage Comics","Scream","Spiderman","Tuziki","Young Couple"};    private final static String[] temperatures = {"#1", "#2", "#3", "#4","#5","#6","#7","#8","#9","#10","#11","#12","#13","#14","#15","#16"};    private CardSliderLayoutManager layoutManger;    private TextSwitcher temperatureSwitcher;    private TextView country1TextView;    private TextView country2TextView;    private int countryOffset1;    private int countryOffset2;    private long countryAnimDuration;    private int currentPosition;    private LinearLayout cardmenu;    private DecodeBitmapTask decodeMapBitmapTask;    static int y;    View bottomSheet;    private BannerAd bannerAd;    private FrameLayout bannerAdContainer;    public StickerPackListActivity() {    }    @Override    protected void setListener() {    }    @Override    protected void initData() {        initCountryText();        initSwitchers();        packRecyclerView = findViewById(R.id.sticker_pack_list);        bannerAdContainer = findViewById(R.id.banner_ad_container);        stickerPackList = getIntent().getParcelableArrayListExtra(EXTRA_STICKER_PACK_LIST_DATA);        cardmenu=findViewById(R.id.cards);        showStickerPackList(stickerPackList);        bottomSheet=findViewById(R.id.recyclerlist);        final Handler handler = new Handler();        handler.postDelayed(() -> {        }, 100);        bannerAd = new BannerAd(bannerAdContainer);        bannerAd.loadBannerAd(BannerAd.HOME_BANNER_AD_ID);    }    @Override    protected void initView() {    }    @Override    protected int getLayoutResId() {        return R.layout.activity_sticker_pack_list;    }    @Override    protected void onResume() {        super.onResume();        whiteListCheckAsyncTask = new WhiteListCheckAsyncTask(this);        whiteListCheckAsyncTask.execute(stickerPackList.toArray(new StickerPack[stickerPackList.size()]));        bannerAd.resume();    }    @Override    protected void onDestroy() {        super.onDestroy();        bannerAd.destroy();    }    @Override    protected void onPause() {        super.onPause();        if (whiteListCheckAsyncTask != null && !whiteListCheckAsyncTask.isCancelled()) {            whiteListCheckAsyncTask.cancel(true);        }        if (isFinishing() && decodeMapBitmapTask != null) {            decodeMapBitmapTask.cancel(true);        }        bannerAd.pause();    }    private void initSwitchers() {        temperatureSwitcher = findViewById(R.id.ts_temperature);        temperatureSwitcher.setFactory(new TextViewFactory(R.style.TemperatureTextView, true));        temperatureSwitcher.setCurrentText(temperatures[0]);    }    private void initCountryText() {        countryAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);        countryOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);        countryOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);        country1TextView = findViewById(R.id.tv_country_1);        country2TextView=findViewById(R.id.tv_country_2);        country1TextView.setX(countryOffset1);        country2TextView.setX(countryOffset2);        country1TextView.setText(countries[0]);        country2TextView.setAlpha(1f);        country1TextView.setAlpha(1f);        country1TextView.setTextColor(getResources().getColor(R.color.gyellow));    }    private void setCountryText(String text, boolean left2right) {        final TextView invisibleText;        final TextView visibleText;        if (country1TextView.getAlpha() > country2TextView.getAlpha()) {            visibleText = country1TextView;            invisibleText = country2TextView;        } else {            visibleText = country2TextView;            invisibleText = country1TextView;        }        final int vOffset;        if (left2right) {            invisibleText.setX(1);            vOffset = countryOffset2;        } else {            invisibleText.setX(countryOffset2);            vOffset = 0;        }        invisibleText.setText(text);        final ObjectAnimator iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f);        final ObjectAnimator vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 1f);        final ObjectAnimator iX = ObjectAnimator.ofFloat(invisibleText, "x", countryOffset1);        final ObjectAnimator vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset);        final AnimatorSet animSet = new AnimatorSet();        animSet.playTogether(iAlpha, vAlpha, iX, vX);        animSet.setDuration(countryAnimDuration);        animSet.start();    }    private void onActiveCardChange() {        final int pos = layoutManger.getActiveCardPosition();        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {            return;        }        onActiveCardChange(pos);    }    private void onActiveCardChange(int pos) {        int animH[] = new int[] {R.anim.slide_in_right, R.anim.slide_out_left};        int animV[] = new int[] {R.anim.slide_in_top, R.anim.slide_out_bottom};        final boolean left2right = pos < currentPosition;        if (left2right) {            animH[0] = R.anim.slide_in_left;            animH[1] = R.anim.slide_out_right;            animV[0] = R.anim.slide_in_bottom;            animV[1] = R.anim.slide_out_top;        }        setCountryText(countries[pos % countries.length], left2right);        temperatureSwitcher.setInAnimation(StickerPackListActivity.this, animH[0]);        temperatureSwitcher.setOutAnimation(StickerPackListActivity.this, animH[1]);        temperatureSwitcher.setText(temperatures[pos % temperatures.length]);        //update final slide        final FrameLayout tempback=findViewById(R.id.frameLayout);        currentPosition = pos;    }    private class TextViewFactory implements  ViewSwitcher.ViewFactory {        @StyleRes final int styleId;        final boolean center;        TextViewFactory(@StyleRes int styleId, boolean center) {            this.styleId = styleId;            this.center = center;        }        @SuppressWarnings("deprecation")        @Override        public View makeView() {            final TextView textView = new TextView(StickerPackListActivity.this);            if (center) {                textView.setGravity(Gravity.CENTER);            }            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {                textView.setTextAppearance(StickerPackListActivity.this, styleId);            } else {                textView.setTextAppearance(styleId);            }            return textView;        }    }    private class ImageViewFactory implements ViewSwitcher.ViewFactory {        @Override        public View makeView() {            final ImageView imageView = new ImageView(StickerPackListActivity.this);            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);            final FrameLayout.LayoutParams lp = new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);            imageView.setLayoutParams(lp);            return imageView;        }    }    private void showStickerPackList(List<StickerPack> stickerPackList) {        allStickerPacksListAdapter = new StickerPackListAdapter(stickerPackList, onAddButtonClickedListener);        packRecyclerView.setAdapter(allStickerPacksListAdapter);        packLayoutManager = new LinearLayoutManager(this);        packLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(                packRecyclerView.getContext(),                packLayoutManager.getOrientation()        );        packRecyclerView.addItemDecoration(dividerItemDecoration);        packRecyclerView.setLayoutManager(packLayoutManager);        packRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(this::recalculateColumnCount);    }    private final StickerPackListAdapter.OnAddButtonClickedListener onAddButtonClickedListener = new StickerPackListAdapter.OnAddButtonClickedListener() {        @Override        public void onAddButtonClicked(StickerPack stickerPack) {            addStickerPackToWhatsApp(stickerPack.identifier, stickerPack.name);            ReportManager.INSTANCE.logClickEvent(stickerPack.identifier,EventKey.HOME_SEND_TO_WHATS_APP);        }    };    private void recalculateColumnCount() {        final int previewSize = getResources().getDimensionPixelSize(R.dimen.sticker_pack_list_item_preview_image_size);        int firstVisibleItemPosition = packLayoutManager.findFirstVisibleItemPosition();        StickerPackListItemViewHolder viewHolder = (StickerPackListItemViewHolder) packRecyclerView.findViewHolderForAdapterPosition(firstVisibleItemPosition);        if (viewHolder != null) {            final int max = Math.max(viewHolder.imageRowView.getMeasuredWidth() / previewSize, 1);            int numColumns = Math.min(STICKER_PREVIEW_DISPLAY_LIMIT, max);            allStickerPacksListAdapter.setMaxNumberOfStickersInARow(numColumns);        }    }    static class WhiteListCheckAsyncTask extends AsyncTask<StickerPack, Void, List<StickerPack>> {        private final WeakReference<StickerPackListActivity> stickerPackListActivityWeakReference;        WhiteListCheckAsyncTask(StickerPackListActivity stickerPackListActivity) {            this.stickerPackListActivityWeakReference = new WeakReference<>(stickerPackListActivity);        }        @Override        protected final List<StickerPack> doInBackground(StickerPack... stickerPackArray) {            final StickerPackListActivity stickerPackListActivity = stickerPackListActivityWeakReference.get();            if (stickerPackListActivity == null) {                return Arrays.asList(stickerPackArray);            }            for (StickerPack stickerPack : stickerPackArray) {                stickerPack.setIsWhitelisted(WhitelistCheck.isWhitelisted(stickerPackListActivity, stickerPack.identifier));            }            return Arrays.asList(stickerPackArray);        }        @Override        protected void onPostExecute(List<StickerPack> stickerPackList) {            final StickerPackListActivity stickerPackListActivity = stickerPackListActivityWeakReference.get();            if (stickerPackListActivity != null) {                stickerPackListActivity.allStickerPacksListAdapter.setStickerPackList(stickerPackList);                stickerPackListActivity.allStickerPacksListAdapter.notifyDataSetChanged();            }        }    }    @Override    public boolean onCreateOptionsMenu(Menu menu) {        // Inflate the menu; this adds items to the action bar if it is present.        getMenuInflater().inflate(R.menu.toolbar, menu);        return true;    }    @Override    public boolean onOptionsItemSelected(MenuItem item) {        if (item.getItemId() == R.id.action_info ) {            launchInfoActivity();            return true;        }        if (item.getItemId() == R.id.action_share ) {            try {                Intent i = new Intent(Intent.ACTION_SEND);                i.setType("text/plain");                i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));                String sAux = "\nDownload "+getResources().getString(R.string.app_name) +"for 100% ad free awesome WhatsApp stickers.\n\n";                sAux = sAux + "https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName()+"\n\n";                i.putExtra(Intent.EXTRA_TEXT, sAux);                startActivity(Intent.createChooser(i, "choose one"));            } catch(Exception e) {                //e.toString();            }            return true;        }        return super.onOptionsItemSelected(item);    }    private void launchInfoActivity() {        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);        startActivity(intent);    }    boolean doubleBackToExitPressedOnce = false;    @Override    public void onBackPressed() {        if (doubleBackToExitPressedOnce) {            super.onBackPressed();            return;        }        this.doubleBackToExitPressedOnce = true;        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);    }}