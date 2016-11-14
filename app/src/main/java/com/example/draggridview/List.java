package com.example.draggridview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.os.Handler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class List extends Activity {

        ListView mListView;
        RecordAdapter mSimpleAdapter;
        String te[] = new String[100];
        java.util.List<HashMap<String, Object>> dataSourceList = new ArrayList<HashMap<String, Object>>();
        Button b01;
        URL url;
        Handler handler=new Handler();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list);

            mSimpleAdapter = new RecordAdapter(this, dataSourceList);

//		LayoutInflater inflater = getLayoutInflater();
//		View otherLayout = inflater.inflate(R.layout.grid_item, null);
//		TextView otherTv1 = (TextView)otherLayout.findViewById(R.id.words_home_function_1);

            mListView = (ListView) findViewById(R.id.list_view);
            mListView.setChoiceMode( ListView.CHOICE_MODE_MULTIPLE );



            b01 = (Button) findViewById(R.id.b01);


            b01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    geturl = ed01.getText().toString();
//                    ed01.setText(null); //重設內容

                    Thread threadc =new Thread(){

                        Thread threadB = new Thread(new Runnable() {
                            public void run(){
                                Element title[] = new Element[100];
                                try {

                                    Log.e("thb", "start");
                                    url=new URL("http://www.ntub.edu.tw/bin/home.php");
                                    Document doc =  Jsoup.parse(url, 3000);        //連結該網址
                                    doc.select("img").removeAttr("src");

                                    for (int x=1;x<100 ;x++) {  //設定一個for迴圈裡面放陣列動態去抓每一段的a

                                        title[x] = doc.select("a").get(x);//抓取為tr且有class屬性的所有Tag get動態抓第幾段li
                                        te[x] = title[x].toString();

                                        //Log.e("123123", Integer.toString(cloclk));
                                    }
                                    Log.e("thb","end");
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                            }
                        });

                        public void run(){
                            threadB.start();
                            try {

                                threadB.join();
                                Log.e("tha", "start");
                                for (int i = 1; i < 100; i++) {
                                    HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
                                    itemHashMap.put("words", te[i]);

                                    Log.e("123123", te[i]);
                                    dataSourceList.add(itemHashMap);
//                                    mListView.setAdapter(mSimpleAdapter);

                                    handler.post(runnableUi);
                                }

                                Log.e("tha", "end");
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    };
                    threadc.start();

                }
            });






        }

    Runnable runnableUi = new Runnable(){
        @Override
        public void run() {
            //更新界面
            mListView.setAdapter(mSimpleAdapter);


        }

    };

}
