package com.allen.commlib.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.allen.commlib.glide.progress.OnProgressListener;
import com.allen.commlib.glide.progress.ProgressManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class GlideUtils {

    /***
     *
     * @param view view视图
     * @param url 图片url，本地路径会center crop处理
     *
     */
    public static void loadImage(ImageView view, String url) {
        try {
            Glide.with(view.getContext())
                    .load(url)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(view);
        } catch (Exception e) {

        }
    }

    /***
     *
     * @param view view视图
     * @param url 图片url，本地路径会center crop处理
     *
     */
    public static void loadImageFitCenter(ImageView view, String url) {
        try {
            Glide.with(view.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .fitCenter()
                    .into(view);

        } catch (Exception e) {
        }
    }

    /***
     *
     * @param view view视图
     * @param url 图片url，本地路径 center crop处理  支持gif
     *
     */
    public static void loadImageWithListener(ImageView view, String url, RequestListener requestListener) {
        try {
            Glide.with(view.getContext())
                    .asGif()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .listener(requestListener)
                    .into(view);

        } catch (Exception e) {
        }
    }



    /***
     *
     * @param view view视图
     * @param url 图片url，本地路径 center crop处理  支持gif
     *
     */
    public static void loadImageWithProgress(ImageView view, String url, OnProgressListener onProgressListener) {
        try {
            Glide.with(view.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .into(view);
            ProgressManager.addListener(url,onProgressListener);
        } catch (Exception e) {
        }
    }




















    /***
     *
     * @param view view视图
     * @param url 图片url，本地路径
     * @param width 重载宽
     * @param height 重载高
     */
    public static void loadImage(ImageView view, String url, int width, int height) {
        try {
            Glide.with(view.getContext())
                    .load(url)
                    .centerCrop()
                    .override(width,height)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(view);
        } catch (Exception e) {

        }

    }




    public static void downloadOnlyWithProgress(Context context, String url, CustomTarget<File> target, OnProgressListener onProgressListener) {
        CustomTarget<File> target1 = new CustomTarget<File>() {
            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                target.onResourceReady(resource, transition);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                target.onLoadFailed(errorDrawable);
                ProgressManager.removeListener(url);
            }

            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
                target.onLoadStarted(placeholder);
            }
        };

        try {
            ProgressManager.addListener(url,onProgressListener);
            Glide.with(context).downloadOnly().load(url).into(target1);
        } catch (Exception e) {

        }
    }

    public static void downloadOnly(Context context, String url, CustomTarget<File> target) {
        try {
            Glide.with(context).downloadOnly().load(url).into(target);
        } catch (Exception e) {

        }
    }







    public static void loadCircleImage(ImageView imageView, String url) {

        try {
            Glide.with(imageView.getContext())
                    .load(url)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView);
        } catch (Exception e) {

        }
    }

    public static void loadBlurImage(ImageView imageView, String url) {
        try {
            Glide.with(imageView.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .transform(new BlurTransformation(50))
                    .into(imageView);
        } catch (Exception e) {

        }
    }



    public static void pauseAllTasks(Context context) {
        try {
            Glide.with(context).pauseAllRequests();
        } catch (Exception e) {

        }
    }



    public static void resumeAllTasks(Context context) {
        try {
            Glide.with(context).resumeRequests();
        } catch (Exception e) {

        }
    }




    public static void loadRoundImageFitCenter(ImageView view, String url, int corner) {
        try {
            Glide.with(view.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .transform(new RoundedCornersTransformation(5,5))
                    .into(view);
        } catch (Exception e) {

        }
    }

    public static void loadRoundImage(ImageView view, String url, int corner) {
        try {
            Glide.with(view.getContext())
                    .load(url)
                    .transform(new RoundedCornersTransformation(5,5))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(view);
        } catch (Exception e) {

        }
    }



}
