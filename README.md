# Chapter-3

Exercise1：
使用函数setProgress即可：

animationView.setProgress(progress / 100f);<br>

/**
 * Sets the progress to the specified frame.
 * If the composition isn't set yet, the progress will be set to the frame when
 * it is.
 */
public void setFrame(int frame) {<br>
  lottieDrawable.setFrame(frame);<br>
}<br>

/**
 * Get the currently rendered frame.
 */
public int getFrame() {<br>
  return lottieDrawable.getFrame();<br>
}<br>

public void setProgress(@FloatRange(from = 0f, to = 1f) float progress) {<br>
  lottieDrawable.setProgress(progress);<br>
}<br>


Exercise2：<br>
首先在activity_ch3ex2.xml中，将visibility赋值为“visible“<br>

<!-- TODO X: A fun rainbow view, try to set it to visible --><br>
<!--android:visibility="invisible"--><br>
<com.example.chapter3.homework.RainbowTextView<br>
    android:id="@+id/rainbow"<br>
    android:layout_width="wrap_content"<br>
    android:layout_height="wrap_content"<br>
    android:layout_marginTop="32dp"<br>
    android:textSize="22sp"<br>
    android:visibility="visible"<br>
    android:layout_gravity="center_horizontal"<br>
    android:text="@string/rainbow_text" /><br>

填充Ch3Ex2Activity.java，实现对target控件的大小缩放和透明度的修改，分别用到“scaleX“，”scaleY“，”alpha“三个属性。<br>

// TODO ex2-1：在这里实现另一个 ObjectAnimator，对 target 控件的大小进行缩放，从 1 到 2 循环<br>

ObjectAnimator animatorX = ObjectAnimator.ofFloat(target,<br>
        "scaleX", 1f, 2f);<br>
animatorX.setRepeatCount(ValueAnimator.INFINITE);<br>
animatorX.setInterpolator(new LinearInterpolator());<br>
animatorX.setDuration(Integer.parseInt(durationSelector.getText().toString()));<br>
animatorX.setRepeatMode(ValueAnimator.REVERSE);<br>

ObjectAnimator animatorY = ObjectAnimator.ofFloat(target,<br>
        "scaleY", 1f, 2f);<br>
animatorY.setRepeatCount(ValueAnimator.INFINITE);<br>
animatorY.setInterpolator(new LinearInterpolator());<br>
animatorY.setDuration(Integer.parseInt(durationSelector.getText().toString()));<br>
animatorY.setRepeatMode(ValueAnimator.REVERSE);<br>
// TODO ex2-2：在这里实现另一个 ObjectAnimator，对 target 控件的透明度进行修改，从 1 到 0.5f 循环<br>
ObjectAnimator animator3 = ObjectAnimator.ofFloat(target,<br>
        "alpha", 1f, 0f);<br>
animator3.setRepeatMode(ValueAnimator.REVERSE);<br>
animator3.setRepeatCount(ValueAnimator.INFINITE);<br>
animator3.setDuration(Integer.parseInt(durationSelector.getText().toString()));<br>
// TODO ex2-3: 将上面创建的其他 ObjectAnimator 都添加到 AnimatorSet 中<br>
animatorSet = new AnimatorSet();<br>
animatorSet.playTogether(animator1, animatorX, animatorY, animator3);<br>
animatorSet.start();<br>
	
**可以思考下，这里为什么要使用 ofArgb，而不是 ofInt 呢？

ofAgrb是专门用来对颜色属性进行动画的函数，查看源代码，注释中有提到：Constructs and returns an ObjectAnimator that animates between Object values
 
	ofInt：
	/**
     * Constructs and returns an ObjectAnimator that animates coordinates along a <code>Path</code>
     * using two properties.  A <code>Path</code></> animation moves in two dimensions, animating
     * coordinates <code>(x, y)</code> together to follow the line. In this variation, the
     * coordinates are integers that are set to separate properties, <code>xProperty</code> and
     * <code>yProperty</code>.
*/
此外还有ofObject用来对对象进行动画的，ofMultiInt,ofMultiFloat这种多参数的等等。


Exercise3:
// TODO: ex3-1. 添加 ViewPager 和 Fragment 做可滑动界面<br>
ViewPager pager = findViewById(R.id.view_pager);<br>
pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {<br>
    @Override<br>
    public Fragment getItem(int i) {<br>
        return new PlaceholderFragment();<br>
    }<br>

    @Override<br>
    public int getCount() {<br>
        return PAGE_COUNT;<br>
    }<br>

    @Override<br>
    public CharSequence getPageTitle(int position) {<br>
        if(position == 0)<br>
            return "好友列表";<br>
        else<br>
            return "我的好友";<br>
    }<br>
});<br>

// TODO: ex3-2, 添加 TabLayout 支持 Tab<br>
TabLayout tabLayout = findViewById(R.id.tab_layout);<br>
tabLayout.setupWithViewPager(pager);<br>


// TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件<br>

View view = inflater.inflate(R.layout.fragment_placeholder, container, false);<br>
animationView = view.findViewById(R.id.animation_view);<br>
listView = view.findViewById(R.id.lv_1);<br>
listView.setAdapter(new ListAdapter());<br>
return view;<br>


// TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入<br>

采用Exercise2中一样的属性动画的方法即可：<br>

ObjectAnimator animator = ObjectAnimator.ofFloat(animationView,<br>
        "alpha", 1f, 0f);<br>
ObjectAnimator animator1 = ObjectAnimator.ofFloat(listView,<br>
        "alpha", 0f, 1f);<br>
animatorSet = new AnimatorSet();<br>
animatorSet.playTogether(animator, animator1);<br>
animatorSet.start();<br>


