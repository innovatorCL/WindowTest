package com.cn21.innovator.windowtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

public class WindowTestActivity extends Activity implements OnTouchListener{

  private static final String TAG = "TestActivity";

  private Button mCreateWindowButton;

  private Button mFloatingButton;
  private WindowManager.LayoutParams mLayoutParams;
  private WindowManager mWindowManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_window_test);
    initView();
  }

  private void initView() {
    mCreateWindowButton = findViewById(R.id.button1);
    mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
  }

  public void onButtonClick(View v){
    if(v == mCreateWindowButton){
      mFloatingButton = new Button(this);
      mFloatingButton.setText("click me");
      mLayoutParams = new WindowManager.LayoutParams(
              WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 1, 0,
              PixelFormat.TRANSPARENT);
      mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL //当前Window区域以外的单击事件传递给底层Window
                                                                            //以内的交给自己处理
              | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  //不需要获取焦点，不接收各种输入事件
              | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;  // 显示在锁屏界面上

      mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
      
      mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
      mLayoutParams.x = 100;
      mLayoutParams.y = 300;
      mFloatingButton.setOnTouchListener(this);
      //添加 View
      mWindowManager.addView(mFloatingButton, mLayoutParams);
    }
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    int rawX = (int) event.getRawX();
    int rawY = (int) event.getRawY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN: {
        break;
      }
      case MotionEvent.ACTION_MOVE: {
        int x = (int) event.getX();
        int y = (int) event.getY();
        mLayoutParams.x = rawX;
        mLayoutParams.y = rawY;

        //更新 View
        mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
        break;
      }
      case MotionEvent.ACTION_UP: {
        break;
      }
      default:
        break;
    }

    return false;
  }

  @Override
  protected void onDestroy() {
    try {

      // 移除 View
      mWindowManager.removeView(mFloatingButton);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    super.onDestroy();
  }
}
