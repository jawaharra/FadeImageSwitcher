package com.github.kimkevin.fadeimageswitcher.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.kimkevin.fadeimageswicher.FadeImageSwitcher;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = MainActivity.class.getSimpleName();

  private final int[] bgRes = {
      R.drawable.bg1,
      R.drawable.bg2,
      R.drawable.bg3,
      R.drawable.bg4,
  };

  private FadeImageSwitcher mFadeImageSwitcher;
  private ViewPager mViewPager;
  private ImageView[] mBgImgs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final FrameLayout container = (FrameLayout) findViewById(R.id.bg_pan);

    mBgImgs = new ImageView[bgRes.length];
    for (int i = 0, li = mBgImgs.length; i < li; i++) {
      mBgImgs[i] = createImageView();
      mBgImgs[i].setImageResource(bgRes[i]);
      container.addView(mBgImgs[i]);
    }

    mFadeImageSwitcher = new FadeImageSwitcher(this, mBgImgs);

    mViewPager = (ViewPager) findViewById(R.id.view_pager);
    mViewPager.setOffscreenPageLimit(3);
    mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mFadeImageSwitcher.onPageScrolled(position, positionOffsetPixels);
      }

      @Override
      public void onPageSelected(int position) {
      }

      @Override
      public void onPageScrollStateChanged(int state) {
      }
    });

    ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    mViewPager.setAdapter(mAdapter);
  }

  private ImageView createImageView() {
    ImageView imageView = new ImageView(this);
    imageView.setLayoutParams(new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT)
    );
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    return imageView;
  }


  private class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public final int GUIDE_COUNT = bgRes.length;

    public ViewPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public int getCount() {
      return GUIDE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
      return PageFragment.newInstance(position);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }
}


