## FadeImageSwitcher

FadeImageSwitcher is to change background images with fade-in and fade-out for ViewPager

![](https://github.com/kimkevin/FadeImageSwitcher/blob/master/assets/demo.gif)

## Download

Gradle:

```bash
repositories {
  jcenter()
}

dependencies {
  compile 'com.github.kimkevin:fadeimageswitcher:1.0.0'
}
```

Maven:

```bash
<dependency>
  <groupId>com.github.kimkevin</groupId>
  <artifactId>fadeimageswitcher</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Usage
1. Make ImageView array for passing `FadeImageSwitcher` as parameter

```java
  yourImageViews = new ImageView[bgRes.length];
  
  ... set background image to each ImageView
  
  fadeImageSwitcher = new FadeImageSwitcher(this, yourImageViews);
```

2. Use `onPageScrolled` of `FadeImageSwitcher` method in your `onPageScrolled` of `ViewPager.OnPageChangeListener` 

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
