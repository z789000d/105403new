package com.example.draggridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewUrlNext extends Activity {
    EditText EditUrl;
    Button Button1,Button2;
    String Array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_url);
        Bundle bundle = this.getIntent().getExtras();
        Array = bundle.getString("edit");
        Log.d("Array1",Array);

        EditUrl = (EditText) findViewById(R.id.EditUrl);
        Button1 = (Button) findViewById(R.id.Button1);
        Button2 = (Button) findViewById(R.id.b02);

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Url = EditUrl.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("edit",Array);
                intent.putExtra("Url",Url);
                intent.setClass(NewUrlNext.this,ListViewCheckboxesActivityNext.class);
                startActivity(intent);
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditUrl.setText("");
            }
        });
    }
}
