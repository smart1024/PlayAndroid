package com.lilin.android.viewfinder.example;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lilin.android.viewfinder.ViewFinder;
import com.lilin.android.viewfinder_annotation.BindView;
import com.lilin.android.viewfinder_annotation.OnClick;

/**
 * 创建日期：2021/7/23 17:23
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.viewfinder
 * 类说明：
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv)
    TextView mTextView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //会调用代码生成类MainActivity$$Finder的inject完成事件的设置和view的绑定
        ViewFinder.inject(this);
    }

    @OnClick(R.id.btn)
    public void onButtonClick() {
        Toast.makeText(this, "onButtonClick", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv)
    public void onTextClick() {
        Toast.makeText(this, "onTextClick", Toast.LENGTH_SHORT).show();
    }
}
