package com.kimkevin.fadeimageswitcher;

/**
 * Some descriptions here
 * <p/>
 * <p/>
 * Created by KimKevin.
 *
 * @since 0.1
 */

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FadeImageSwitcher {
    private static final String TAG = FadeImageSwitcher.class.getSimpleName();

    private Context mContext;

    private ImageView[] mBgImgs;
    private int[] mImgRes;
    private int mCurPos;

    public FadeImageSwitcher(Context context, FrameLayout container, int[] imgRes) {
        this.mContext = context;
        this.mImgRes = imgRes;

        mBgImgs = new ImageView[imgRes.length];
        for (int i = 0, li = mBgImgs.length; i < li; i++) {
            mBgImgs[i] = createImageView();
            mBgImgs[i].setBackgroundResource(imgRes[i]);
            container.addView(mBgImgs[i]);
        }

        mCurPos = 0;

        showImage(mCurPos);
    }

    public void showImage(int pos) {
        for (int i = 0, li = mBgImgs.length; i < li; i++) {
            mBgImgs[i].setBackgroundResource(mImgRes[i]);
            if (i != pos) {
                mBgImgs[i].setVisibility(View.INVISIBLE);
            } else {
                mBgImgs[i].setVisibility(View.VISIBLE);
            }
        }
    }

    public void showImage(int pos, int positionOffsetPixels) {
        float alpha = (float) positionOffsetPixels / getScreenWidth();

        if (positionOffsetPixels == 0) {
            mCurPos = pos;
            showImage(mCurPos);
            return;
        }

        if (mCurPos > pos) {
            /**
             * Show Prev Image
             */
            mBgImgs[pos].setVisibility(View.VISIBLE);
            mBgImgs[mCurPos].setAlpha(alpha);
        } else {
            /**
             * Show Next Image
             */
            mBgImgs[pos + 1].setVisibility(View.VISIBLE);
            mBgImgs[pos + 1].setAlpha(alpha);
        }
    }

    private ImageView createImageView() {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        );
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    private int getScreenWidth() {
        Display dis = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        return dis.getWidth();
    }
}


