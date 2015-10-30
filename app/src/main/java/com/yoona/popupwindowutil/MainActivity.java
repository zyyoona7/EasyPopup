package com.yoona.popupwindowutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.yoona.popwindow.PopupWindowUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        setViews();
    }

    public void setViews() {
        TextView textView = (TextView) findViewById(R.id.text);
        final Button btn1 = (Button) findViewById(R.id.btn_bottom);
        btn1.setOnClickListener(v -> {
            PopupWindowUtil popupWindow = new PopupWindowUtil(btn1);
            popupWindow.setContentView(R.layout.popup_window);
            Button button1 = (Button) popupWindow.findId(R.id.btn_onClick0);
            button1.setOnClickListener(v1 -> popupWindow.dismiss());
            popupWindow.showBottom();
        });
        Button btn2 = (Button) findViewById(R.id.btn_top);
        btn2.setOnClickListener(v1 -> {
            PopupWindowUtil popupWindow = new PopupWindowUtil(btn2);
            popupWindow.setContentView(R.layout.popup_window);
            popupWindow.showTop();
        });
        Button btn3 = (Button) findViewById(R.id.btn_bottom_alpha);
        btn3.setOnClickListener(v2 -> {
            startActivity(new Intent(this, Main2Activity.class));
        });
        Button btn10 = (Button) findViewById(R.id.btn_top_alpha);
        btn10.setOnClickListener(v10 -> {
            PopupWindowUtil popupWindow = new PopupWindowUtil(btn2);
            popupWindow.setContentView(R.layout.popup_window);
            popupWindow.showTopWithAlpha();
        });
        Button btn4 = (Button) findViewById(R.id.btn_center);
        btn4.setOnClickListener(v3 -> {
            PopupWindowUtil popupWindow = new PopupWindowUtil(btn4);
            popupWindow.setContentView(R.layout.popup_window);
            popupWindow.showCenter();
        });
        Button btn5 = (Button) findViewById(R.id.btn_center_alpha);
        btn5.setOnClickListener(v4 -> {
            PopupWindowUtil popupWindow = new PopupWindowUtil(btn5);
            popupWindow.setContentView(R.layout.popup_window);
            popupWindow.showCenterWithAlpha();
            popupWindow.setOnDismissListener(() -> popupWindow.dismiss());
        });
        Button btn6 = (Button) findViewById(R.id.btn_1);
        btn6.setOnClickListener(v5 -> {
            PopupWindowUtil popupWindow = new PopupWindowUtil(btn1);
            popupWindow.setContentView(R.layout.popup_window);
            popupWindow.showLikePopDownLeftMenu();
        });
        Button btn7 = (Button) findViewById(R.id.btn_4);
        btn7.setOnClickListener(v6 -> {
            PopupWindowUtil popupWindow = new PopupWindowUtil(textView);
            popupWindow.setContentView(R.layout.popup_window);
            popupWindow.showLikePopDownRightMenu();
        });
        Button btn8 = (Button) findViewById(R.id.btn_2);
        btn8.setOnClickListener(v7 -> {
            PopupWindowUtil popupWindow = new PopupWindowUtil(btn3);
            popupWindow.setContentView(R.layout.popup_window);
            popupWindow.showLikeQuickAction();
        });
        Button btn9 = (Button) findViewById(R.id.btn_3);
        btn9.setOnClickListener(v8 -> {
            PopupWindowUtil popupWindow = new PopupWindowUtil(btn9);
            popupWindow.setContentView(R.layout.popup_window);
            popupWindow.showLikeQuickAction(50, 100);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
