package com.example.draggridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainView extends Activity {
    Button b01, b02 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        b01 = (Button) findViewById(R.id.NewLayout);
        b02 = (Button) findViewById(R.id.MyLayout);

        b01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainView.this,NewUrl.class);
                startActivity(intent);
            }
        });

        b02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainView.this,WebList.class);
                startActivity(intent);

            }
        });
    }
}
