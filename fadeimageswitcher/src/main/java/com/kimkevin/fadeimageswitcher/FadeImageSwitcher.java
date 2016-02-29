package com.kimkevin.fadeimageswitcher;

/**
 *
 * FadeImageSwitcher which displays numerous background images
 * with fade-in and fade-out in {@link android.support.v4.view.ViewPager}
 *
 * Created by KimKevin.
 *
 */

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class FadeImageSwitcher {
    private static final String TAG = FadeImageSwitcher.class.getSimpleName();

    private Context mContext;
    private ImageView[] mImageViews;
    private int[] mImgRes;
    private int mCurPos;

    /**
     * Create a {@link FadeImageSwitcher} with ImageView Array and Image Resources
     *
     * @param context
     * @param imageViews ImageViews that are added to container for background
     * @param imgRes Image resources for background
     */
    public FadeImageSwitcher(Context context, ImageView[] imageViews, int[] imgRes) {
        this.mContext = context;
        this.mImageViews = imageViews;
        this.mImgRes = imgRes;

        mCurPos = 0;

        showImage(mCurPos);
    }

    /**
     * Show background image
     * @param pos position in {@link android.support.v4.view.ViewPager}
     */
    public void showImage(int pos) {
        for (int i = 0, li = mImageViews.length; i < li; i++) {
            mImageViews[i].setBackgroundResource(mImgRes[i]);
            if (i != pos) {
                mImageViews[i].setVisibility(View.INVISIBLE);
            } else {
                mImageViews[i].setVisibility(View.VISIBLE);
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
            // Show Prev Image
            mImageViews[pos].setVisibility(View.VISIBLE);
            mImageViews[mCurPos].setAlpha(alpha);
        } else {
            // Show Next Image
            mImageViews[pos + 1].setVisibility(View.VISIBLE);
            mImageViews[pos + 1].setAlpha(alpha);
        }
    }

    private int getScreenWidth() {
        Display dis = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        return dis.getWidth();
    }
}


