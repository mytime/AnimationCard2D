package com.jikexueyuan.card2d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * 2D翻转效果
 */
public class MainActivity extends AppCompatActivity {

    private ImageView imageA,imageB;

    //1,0 自身X轴，从有到无
    // 1,1 自身Y轴
    // Animation.RELATIVE_TO_PARENT,0.5f  父级空间X中心点
    // Animation.RELATIVE_TO_PARENT,0.5f  父级空间中心点
    private ScaleAnimation sa1 = new ScaleAnimation(1,0,1,1,
            Animation.RELATIVE_TO_PARENT,0.5f,
            Animation.RELATIVE_TO_PARENT,0.5f);

    //从无到有
    private ScaleAnimation sa2 = new ScaleAnimation(0,1,1,1,
            Animation.RELATIVE_TO_PARENT,0.5f,
            Animation.RELATIVE_TO_PARENT,0.5f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化图片显示方法
        initView();
        //监听事件: 布局页面被点击时
        findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageA.getVisibility() == View.VISIBLE){
                    imageA.startAnimation(sa1);
                }else{
                    imageB.startAnimation(sa1);
                }
            }
        });
    }

    //图片显示方法
    private void showImageA(){
        imageA.setVisibility(View.VISIBLE); //图片可见
        imageB.setVisibility(View.INVISIBLE); //图片隐藏
    }
    private void showImageB(){
        imageB.setVisibility(View.VISIBLE); //图片可见
        imageA.setVisibility(View.INVISIBLE); //图片隐藏
    }

    //图片初始化显示方法
    private void initView(){

        //查找图片
        imageA = (ImageView) findViewById(R.id.ivA);
        imageB = (ImageView) findViewById(R.id.ivB);
        // 初始化showImageA
        showImageA();

        //设定动画时间
        sa1.setDuration(500);
        sa2.setDuration(500);

        //sa1动画监听事件，
        sa1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //imageA 显示
                if(imageA.getVisibility() == View.VISIBLE){
                    imageA.setAnimation(null); //清除
                    showImageB(); //显示第二张图片
                    imageB.startAnimation(sa2);
                }else{
                    imageB.setAnimation(null);
                    showImageA();
                    imageA.startAnimation(sa2);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
