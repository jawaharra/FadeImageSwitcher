package com.github.kimkevin.fadeimageswitcher.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PageFragment extends Fragment {
  private static final String TAG = PageFragment.class.getSimpleName();

  private static final String POSITION_KEY = "position";

  private int position;

  public static PageFragment newInstance(int position) {
    PageFragment fragment = new PageFragment();
    Bundle args = new Bundle();
    args.putInt(POSITION_KEY, position);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    position = getArguments().getInt(POSITION_KEY, 0);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    LinearLayout rootView = new LinearLayout(getActivity());
    rootView.setLayoutParams(new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT)
    );
    rootView.setGravity(Gravity.CENTER);

    TextView textView = new TextView(getActivity());
    textView.setText("Fragment " + (position + 1));
    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
    textView.setTextColor(Color.WHITE);
    rootView.addView(textView);

    return rootView;
  }
}
