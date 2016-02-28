package com.kimkevin.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import com.kimkevin.fadeimageswitcher.FadeImageSwitcher;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FrameLayout guideBgImg = (FrameLayout) findViewById(R.id.bg_pan);
        mFadeImageSwitcher = new FadeImageSwitcher(this, guideBgImg, bgRes);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled : " + position + " , " + positionOffsetPixels);
                mFadeImageSwitcher.showImage(position, positionOffsetPixels);
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
}
