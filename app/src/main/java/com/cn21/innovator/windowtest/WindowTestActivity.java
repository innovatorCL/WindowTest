package com.cn21.innovator.windowtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class WindowTestActivity extends Activity {

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

  }

}
