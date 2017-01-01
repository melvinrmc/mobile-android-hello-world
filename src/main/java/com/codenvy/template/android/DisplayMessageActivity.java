package com.codenvy.template.android;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;

import com.lacasitaapp.admin.User;


public class DisplayMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(HelloAndroidActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(24);
        textView.setText(HelloAndroidActivity.usuario.getNombre());

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView, 0);


    }

    @Override
    public void onBackPressed() {

        return;
    }

}
