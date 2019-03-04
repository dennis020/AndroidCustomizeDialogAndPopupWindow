package com.hbw020.androidcustomizedialogandpopupwindow.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hbw020.androidcustomizedialogandpopupwindow.demo.activity.CommonDialogActivity;
import com.hbw020.androidcustomizedialogandpopupwindow.demo.activity.PopupWindowActivity;
import com.hbw020.androidcustomizedialogandpopupwindow.demo.activity.WindowManagerActivity;
import com.hbw020.androidcustomizedialogandpopupwindow.demo.utils.NavitateUtil;

public class MainActivity extends AppCompatActivity {
    private TextView tv_popup, tv_floating_window, tv_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar, "Home", true);
        initView();
    }

    private void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp) {
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
    }

    private void initView() {
        tv_popup = (TextView) findViewById(R.id.tv_popup);
        tv_floating_window = (TextView) findViewById(R.id.tv_floating_window);
        tv_dialog = findViewById(R.id.tv_dialog);
        tv_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavitateUtil.startActivity(MainActivity.this, PopupWindowActivity.class);
            }
        });
        tv_floating_window.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavitateUtil.startActivity(MainActivity.this, WindowManagerActivity.class);
            }
        });
        tv_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavitateUtil.startActivity(MainActivity.this, CommonDialogActivity.class);
            }
        });
    }
}
