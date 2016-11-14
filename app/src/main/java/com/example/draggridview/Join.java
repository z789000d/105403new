package com.example.draggridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Join extends Activity {
    Button button,button2;
    TextView textView,textView2,textView3,textView4,textView5;
    EditText edittext1,edittext2,edittext3,edittext4,edittext5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

//        textView = (TextView) findViewById(R.id.textView);
//        textView2 = (TextView) findViewById(R.id.textView2);
//        textView3 = (TextView) findViewById(R.id.textView3);
//        textView4 = (TextView) findViewById(R.id.textView4);
//        textView5 = (TextView) findViewById(R.id.textView5);
//
//        edittext1 = (EditText) findViewById(R.id.editext1);
//        edittext2 = (EditText) findViewById(R.id.editext2);
//        edittext3 = (EditText) findViewById(R.id.editext3);
//        edittext4 = (EditText) findViewById(R.id.editext4);
//        edittext5 = (EditText) findViewById(R.id.editext5);
//
//
//        button = (Button) findViewById(R.id.button);
        button = (Button) findViewById(R.id.register_button);
//        button2 = (Button) findViewById(R.id.button2);
//
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(Join.this, MainActivity.class);
                //startActivity(intent);
                Join.this.finish();
                //返回MAIN

            }
        });
//
//        button2.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent();
//                intent.setClass(Join.this, JoinNext.class);
//                startActivity(intent);
//                Join.this.finish();
//                //
//
//            }
//        });



    }
}
