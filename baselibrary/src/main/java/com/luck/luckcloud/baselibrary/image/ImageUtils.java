package com.luck.luckcloud.baselibrary.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.luck.luckcloud.baselibrary.R;
import com.luck.luckcloud.baselibrary.image.glide.GlideRoundTransform;

/**
 * 图片显示工具类
 * Created by fa on 2017/6/25.
 */

public class ImageUtils {
    private static final String TAG = ImageUtils.class.getSimpleName();

//    public static void showVideoImage(Context context, String imagePath, ImageView imageView) {
//
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//        Glide.with(context).setDefaultRequestOptions(
//
//                new RequestOptions().frame(1000000)//单位微秒
//                        .centerCrop()
//                        .error(R.color.color_black)
//                        .placeholder(R.color.color_black)).load(imagePath) .into(imageView);
//    }

    public static void showImage(Context context, Object imagePath, ImageView imageView) {
        showImage(context, imagePath, imageView, 0, 0, R.color.color_f7f7f7);
    }

    public static void showImage(Context context, Object imagePath, ImageView imageView, int placeHolder) {
        showImage(context, imagePath, imageView, 0, 0, placeHolder);
    }

    public static void showImage(Context context, Object imagePath, ImageView imageView, int imageWidth, int imageHeight) {
        showImage(context, imagePath, imageView, imageWidth, imageHeight, R.color.color_f7f7f7);
    }

    public static void showImage(Context context, Object imagePath, ImageView imageView, int imageWidth, int imageHeight, int placeHolder) {
        RequestBuilder builder = getBuilder(context, imagePath, placeHolder);
        if (imageWidth > 0 && imageHeight > 0) {
            builder.override(imageWidth, imageHeight);
        }
        builder.into(imageView);
    }

    public static void showImageCenterCrop(Context context, Object imagePath, ImageView imageView) {
        showImageCenterCrop(context, imagePath, imageView, R.color.color_f7f7f7);
    }

    public static void showImageCenterCrop(Context context, Object imagePath, ImageView imageView, int width, int height) {
        showImageCenterCrop(context, imagePath, imageView, R.color.color_f7f7f7, width, height);
    }

    public static void showImageCenterCrop(Context context, Object imagePath, ImageView imageView, int placeHolder) {
        showImageCenterCrop(context, imagePath, imageView, placeHolder, 0, 0);
    }

    public static void showImageCenterCrop(Context context, Object imagePath, ImageView imageView, int placeHolder, int width, int height) {
        RequestBuilder builder = getBuilder(context, imagePath, placeHolder);
        builder.centerCrop();
        if (width > 0 && height > 0) {
            builder.override(width, height);
        }
        builder.into(imageView);
    }

    public static void showImageFitCenter(Context context, Object imagePath, ImageView imageView) {
        showImageFitCenter(context, imagePath, imageView, R.color.color_f7f7f7);
    }

    public static void showImageFitCenter(Context context, Object imagePath, ImageView imageView, int placeHolder) {
        RequestBuilder builder = getBuilder(context, imagePath, placeHolder);
        builder.fitCenter();
        builder.into(imageView);
    }

    public static void showImageRound(Context context, Object imagePath, ImageView imageView, int roundDP) {
        showImageRound(context, imagePath, imageView, R.color.color_f7f7f7, roundDP);
    }

    public static void showImageRound(Context context, Object imagePath, ImageView imageView, int placeHolder, int roundDP) {

        RequestBuilder builder = getBuilder(context, imagePath, placeHolder);
        builder.transform(new CenterCrop(), new RoundedCorners(roundDP));
        builder.into(imageView);
    }

    public static void showImageCircle(Context context, Object imagePath, ImageView imageView) {
        showImageCircle(context, imagePath, imageView, R.color.color_f7f7f7);
    }

    public static void showImageCircle(Context context, Object imagePath, ImageView imageView, int placeHolder) {
        RequestBuilder builder = getBuilder(context, imagePath, placeHolder);
        builder.centerCrop();
        builder.transform(new CircleCrop());
        builder.into(imageView);
    }

    private static RequestBuilder<Drawable> getBuilder(Context context, Object imagePath, int placeHolder) {
        RequestBuilder<Drawable> load = Glide.with(context).load(imagePath);
        RequestBuilder<Drawable> builder = Glide.with(context).load(imagePath);
        if (placeHolder != 0) {
            builder.error(placeHolder).placeholder(placeHolder);
        }
        return builder;
    }


    public static void showImageCenterCropOverride(Context context, Object imagePath, ImageView imageView,
                                                   int width, int height, int placeHolder) {
        RequestBuilder builder = getBuilder(context, imagePath, placeHolder);
        builder.centerCrop();
        builder.transition(DrawableTransitionOptions.withCrossFade());
        if (width > 0 && height > 0) {
            builder.override(width, height);
        }
        builder.into(imageView);
    }

    public static void showGifCenterCrop(Context context, Object imagePath, ImageView imageView, int placeHolder) {
        Glide.with(context).load(imagePath)
                .centerCrop()
                .error(placeHolder)
                .skipMemoryCache(true)// 禁止内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 设置不缓存
                .placeholder(placeHolder)
                .into(imageView);
    }

    /**
     * 给Layout设置背景
     */
    public static void showLayoutBg(final Context context, Object imagePath, final View layout) {
//        showLayoutBg(context, imagePath, layout, R.color.color_f7f7f7);
    }

    public static void showLayoutRoundBg(final Context context, Object imagePath, final View layout, int roundDP) {
        showLayoutRoundBg(context, imagePath, layout, R.color.color_f7f7f7, roundDP);
    }

    /**
     * 给Layout设置背景
     */
   /* public static void showLayoutBg(final Context context, Object imagePath, final View layout, int placeHolder) {
        Glide.with(context)
                .load(imagePath)
                .error(placeHolder)
                .placeholder(placeHolder)
                .into(new ViewTarget<View, GlideDrawable>(layout) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }*/
    public static void showLayoutRoundBg(final Context context, Object imagePath, final View layout, int placeHolder, int rounddp) {
        Glide.with(context)
                .load(imagePath)
                .transform(new CenterCrop(), new RoundedCorners(rounddp))
                .error(placeHolder)
                .placeholder(placeHolder)
                .into(new ViewTarget<View, Drawable>(layout) {
                    @Override
                    public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                        this.view.setBackground(drawable.getCurrent());
                    }
                });
    }

    /**
     * 给Layout设置背景
     */
    public static void showLayoutBg(final Context context, Object imagePath, final ViewGroup layout, Drawable placeHolder) {
        Glide.with(context)
                .load(imagePath)
                .error(placeHolder)
                .placeholder(placeHolder)
                .into(new ViewTarget<View, Drawable>(layout) {
                    @Override
                    public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                        this.view.setBackground(drawable.getCurrent());
                    }
                });
    }

    /**
     * 加载Bitmap
     */
    public static void getBitmap(final Context context, Object imagePath, final LoadBitmapCallback callback) {
        Glide.with(context).asBitmap()
                .load(imagePath)
                .into(new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
                            return;
                        }
                        if (callback != null) {
                            callback.onLoadReady(bitmap, transition);
                        }
                    }
                });
    }

    public static void LoadImageWithCallback(Context context, final ImageView imageView, Object obj, final LoadDrawableCallback callback) {
        Glide.with(context)
                .load(obj)
                .listener(new RequestListener<Drawable>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Drawable> target, boolean b) {
                        if (callback != null) {
                            callback.onLoadException(e);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable drawable, Object o, Target<Drawable> target, DataSource dataSource, boolean b) {
                        if (callback != null) {
                            callback.onLoadReady(drawable);
                        }
                        return false;
                    }
                }).into(imageView);

    }

    public static void getImageDrawable(Context context, Object path, final LoadDrawableCallback callback) {
        Glide.with(context)
                .load(path)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                        if (callback != null) {
                            callback.onLoadReady(drawable);
                        }
                    }
                });
    }

    /**
     * 获取Bitmap的回调
     */
    public interface LoadBitmapCallback {
        void onLoadReady(Bitmap bitmap, Transition<? super Bitmap> glideAnimation);
    }

    /**
     * 获取Bitmap的回调
     */
    public interface LoadDrawableCallback {
        void onLoadReady(Drawable drawable);

        void onLoadException(Exception e);
    }

    /**
     * 给Layout设置背景(可以设置背景图片宽高)
     * 这种方式会按照设置的宽高去截取图片，再去加载到控件背景上，有时会导致图片显示不全
     */
    public static void showLayoutBgWithSize(final Context context, Object imagePath, final ViewGroup layout, int width, int height) {
        showLayoutBgWithSize(context, imagePath, layout, width, height, R.color.color_f7f7f7);
    }

    /**
     * 给Layout设置背景(可以设置背景图片宽高)
     * 这种方式会按照设置的宽高去截取图片，再去加载到控件背景上，有时会导致图片显示不全
     */
    public static void showLayoutBgWithSize(final Context context, Object imagePath, final ViewGroup layout, int width, int height, final int placeHolder) {
        Glide.with(context)
                .asBitmap()
                .load(imagePath)
                .into(new SimpleTarget<Bitmap>(width, height) {
                          //设置宽高
                          @Override
                          public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                              Drawable drawable;
                              if (bitmap != null) {
                                  drawable = new BitmapDrawable(context.getResources(), bitmap);

                              } else {
                                  drawable = ContextCompat.getDrawable(context, placeHolder);
                              }
                              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                  layout.setBackground(drawable);   //设置背景
                              } else {
                                  layout.setBackgroundDrawable(drawable);   //设置背景
                              }
                          }
                      }
                );
    }
}
