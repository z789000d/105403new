package com.example.draggridview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;
import java.util.*;

public class ShowInWebViewNext extends Activity {

    java.util.List<HashMap<String, Object>> dataSourceList = new ArrayList<HashMap<String, Object>>();
    URL url;


    DragGridView mDragGridView;
    RecordAdapter mSimpleAdapter;
    String te[] = new String[100];
    private SQLiteDatabase db;
    String ArrayTextSplit[] ;
    Handler handler=new Handler();
    MyDataDB dbHelper;
    String Array;
    Button bt01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_in_web_view_next);
        Bundle bundle = this.getIntent().getExtras();
        Array = bundle.getString("edit");
        Log.d("Array",Array);

        dbHelper = new MyDataDB(this);

        mSimpleAdapter = new RecordAdapter(this,dataSourceList);
//		LayoutInflater inflater = getLayoutInflater();
//		View otherLayout = inflater.inflate(R.layout.grid_item, null);
//		TextView otherTv1 = (TextView)otherLayout.findViewById(R.id.words_home_function_1);


        bt01 = (Button) findViewById(R.id.button3);
        mDragGridView = (DragGridView) findViewById(R.id.dragGridView);


        Cursor cursor = dbHelper.select();	//取得SQLite類別的回傳值:Cursor物件
        cursor.moveToFirst();
        do {
            if(cursor.getString(2).equals(Array))

            {
                HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
                itemHashMap.put("words", cursor.getString(1));
                dataSourceList.add(itemHashMap);
                handler.post(runnableUi);
            }



        }while (cursor.moveToNext());
        // for (int x=1; x<ArrayTextSplit.length ;x++) {

        bt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("edit",Array);
                intent.setClass(ShowInWebViewNext.this,NewUrlNext.class);
                startActivity(intent);
            }
        });




    }

    Runnable runnableUi = new Runnable(){
        @Override
        public void run() {
            //更新界面
            mDragGridView.setAdapter(mSimpleAdapter);
            mDragGridView.setOnChangeListener(new DragGridView.OnChanageListener() {

                @Override
                public void onChange(int from, int to) {
                    HashMap<String, Object> temp = dataSourceList.get(from);
                    //直接交互item
//									dataSourceList.set(from, dataSourceList.get(to));
                    //				dataSourceList.set(to, temp);


                    //这里的处理需要注意下
                    if (from < to) {
                        for (int i = from; i < to; i++) {
                            Collections.swap(dataSourceList, i, i + 1);
                        }
                    } else if (from > to) {
                        for (int i = from; i > to; i--) {
                            Collections.swap(dataSourceList, i, i - 1);
                        }
                    }


                    dataSourceList.set(to, temp);

                    mSimpleAdapter.notifyDataSetChanged();


                }
            });
        }

    };


}
