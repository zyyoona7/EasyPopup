package com.yoona.popupwindowutil;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.yoona.popwindow.PopupWindowUtil;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setView();

    }

    public void setView() {
        Button btn = (Button) findViewById(R.id.btn_click);
        btn.setOnClickListener(v -> {
            PopupWindowUtil popupWindow = new PopupWindowUtil(btn);
            popupWindow.setContentView(R.layout.popup_window);
            popupWindow.setOutsideTouchable(false);
            popupWindow.showBottomWithAlpha();
            popupWindow.setOnDismissListener(() -> popupWindow.dismiss());
        });

    }

}
