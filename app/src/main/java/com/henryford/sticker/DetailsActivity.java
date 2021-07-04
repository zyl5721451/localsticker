package com.henryford.sticker;import android.animation.ObjectAnimator;import android.graphics.Bitmap;import android.os.Build;import android.os.Bundle;import android.transition.Transition;import android.util.DisplayMetrics;import android.widget.FrameLayout;import android.widget.ImageView;import androidx.cardview.widget.CardView;import com.henryford.sticker.util.DecodeBitmapTask;public class DetailsActivity extends AddStickerPackActivity implements DecodeBitmapTask.Listener {    static final String BUNDLE_IMAGE_ID = "BUNDLE_IMAGE_ID";    private ImageView imageView;    private DecodeBitmapTask decodeBitmapTask;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_details);        final int smallResId = getIntent().getIntExtra(BUNDLE_IMAGE_ID, -1);        if (smallResId == -1) {            finish();            return;        }	    FrameLayout addbutton=findViewById(R.id.add_to_whatsapp_button);        imageView = findViewById(R.id.image);        imageView.setImageResource(smallResId);        addbutton.setOnClickListener(v -> finish());        imageView.setOnClickListener(view -> DetailsActivity.super.onBackPressed());    }    @Override    protected void onPause() {        super.onPause();        if (isFinishing() && decodeBitmapTask != null) {            decodeBitmapTask.cancel(true);        }    }    private void addCardCorners() {        final CardView cardView = findViewById(R.id.card);        cardView.setRadius(25f);    }    private void removeCardCorners() {        final CardView cardView = findViewById(R.id.card);        ObjectAnimator.ofFloat(cardView, "radius", 0f).setDuration(50).start();    }    @Override    public void onPostExecuted(Bitmap bitmap) {        imageView.setImageBitmap(bitmap);    }}