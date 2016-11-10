package com.github.kimkevin.fadeimageswicher;

/**
 *
 * FadeImageSwitcher which displays numerous background images
 * with fade-in and fade-out in {@link android.support.v4.view.ViewPager}
 *
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;

public class FadeImageSwitcher {
  private static final String TAG = FadeImageSwitcher.class.getSimpleName();

  private Context mContext;
  private ImageView[] mImageViews;
  private int mCurPos;

  /**
   * Create a {@link FadeImageSwitcher} with ImageView Array and Image Resources
   * @param context Context from Application
   * @param imageViews ImageViews of background that are added to container layout
   */
  public FadeImageSwitcher(Context context, ArrayList<ImageView> imageViews) {
    this(context, imageViews.toArray(new ImageView[imageViews.size()]));
  }

  public FadeImageSwitcher(Context context, ImageView[] imageViews) {
    this.mContext = context;
    this.mImageViews = imageViews;

    mCurPos = 0;

    mImageViews[0].setVisibility(View.VISIBLE);

    for (int i = 1, li = mImageViews.length; i < li; i++) {
      mImageViews[i].setVisibility(View.INVISIBLE);
    }
  }

  /**
   *
   * Dispatch position and positionOffSetPixcels from onPageScrolled of {@link ViewPager.OnPageChangeListener}
   * to imageviews.
   *
   * @param position Current position in ViewPager
   * @param positionOffsetPixels Offset pixels from prev position to next position
   */
  public void onPageScrolled(int position, int positionOffsetPixels) {
    float alpha = (float) positionOffsetPixels / getScreenWidth();

    /**
     * positionOffSetPixels is zero(0) when it is fully Idle
     */
    if (positionOffsetPixels == 0) {
      mCurPos = position;

      if (mCurPos > 0) {
        invisibleImage(mCurPos - 1);
      }

      if (mCurPos < mImageViews.length - 1) {
        invisibleImage(mCurPos + 1);
      }
    } else {
      if (mCurPos > position) {
        // Set visible and alpha to previous ImageView
        final int prevPos = position;
        mImageViews[prevPos].setVisibility(View.VISIBLE);
        mImageViews[mCurPos].setAlpha(alpha);
      } else {
        // Set visible and alpha to next ImageView
        final int nextPos = position + 1;
        mImageViews[nextPos].setVisibility(View.VISIBLE);
        mImageViews[nextPos].setAlpha(alpha);
      }
    }
  }

  private void invisibleImage(int position) {
    mImageViews[position].setVisibility(View.INVISIBLE);
    mImageViews[position].setAlpha(1.0f);
  }

  private int getScreenWidth() {
    Display dis = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    return dis.getWidth();
  }
}


