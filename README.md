## FadeImageSwitcher

FadeImageSwitcher is to change background images with fade-in and fade-out for ViewPager

![](https://github.com/kimkevin/FadeImageSwitcher/blob/master/samples/demo.gif)

##Usage
1 Add FadeImageSwitcher to your package

2 Make ImageView array for passing `FadeImageSwitcher` as parameter

```java
  yourImageViews = new ImageView[bgRes.length];
  fadeImageSwitcher = new FadeImageSwitcher(this, yourImageViews);
```

3 Use `onPageScrolled` of `FadeImageSwitcher` method in your `onPageScrolled` of `ViewPager.OnPageChangeListener` 
```java
  yourViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      fadeImageSwitcher.onPageScrolled(position, positionOffsetPixels);
    }
  }
```

## License
Copyright (c) 2013 "KimKevin" Yongjun Kim

Licensed under the MIT license.
