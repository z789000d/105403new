package com.example.draggridview;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ListViewCheckboxesActivityNext extends Activity
{
    String te[] = new String[100];
    MyCustomAdapter dataAdapter = null;
    EditText txtsearch;
    ArrayList<States> stateList = new ArrayList<States>();
    ListView listView ;
    URL  url;
    int x; //迴圈
    States _states = new States("x",te[x],false);
    String [] status = new String[100];
    String [] number = new String[100];
    ActionBar actionBar;
    Handler handler=new Handler();
    String Url;
    String Array;
    Button bt01;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        actionBar = getActionBar();
        bt01 =(Button)findViewById(R.id.button3);
        listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView

        Bundle bundle = this.getIntent().getExtras();
        Array = bundle.getString("edit");
        Log.d("Array2",Array);
           Url = bundle.getString("Url");
            try {
             url = new URL(Url);
                }
            catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }

        txtsearch = (EditText) findViewById(R.id.txtsearch);


        txtsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    search();


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        bt01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer responseText = new StringBuffer();
                // responseText.append("The following were selected...\n");

                ArrayList<States> stateList = dataAdapter.stateList;


                for(int i=0;i<stateList.size();i++)
                {
                    States state = stateList.get(i);

                    if(state.isSelected())
                    {
                        responseText.append("\n"+"----" + state.getName());
                    }
                }
                String ArrayText =  responseText.toString();


//                Toast.makeText(getApplicationContext(),
//                        responseText, Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.putExtra("Array",ArrayText);
                intent.putExtra("edit",Array);
                intent.putExtra("Url",Url);
                intent.setClass(ListViewCheckboxesActivityNext.this,ShowInWebViewNext1.class);
                startActivity(intent);
            }
        });





        //Generate list View from ArrayList


//        checkButtonClick();
        Thread threadc =new Thread() {

            Thread threadB = new Thread(new Runnable() {
                public void run() {
                    try {

                        Html();

                        Log.e("thb", "end");
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            });

            public void run() {
                threadB.start();
                try {

                    threadB.join();
                    Log.e("tha", "start");
                    Thread.sleep(2000);
                    Thread a = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            displayListView();

                            handler.post(runnableUi);
                        }
                    });
                    a.start();



                    //handler.post(runnableUi);

                    Log.e("tha", "end");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        threadc.start();


    }



    public  void Html()

    {

        new Thread(new Runnable() {
            public void run(){
                Element title[] = new Element[100];
                try {

                    Document doc =  Jsoup.parse(url, 3000);        //連結該網址
                    doc.select("img").removeAttr("src");
                    doc.select("div").removeAttr("class");
                    doc.select("span").remove();			//去除不要的屬性

                    for ( x=0;x<100 ;x++) {  //設定一個for迴圈裡面放陣列動態去抓每一段的a

                        title[x] = doc.select("a").get(x);//抓取為tr且有class屬性的所有Tag get動態抓第幾段li
                        te[x] = title[x].toString();

                        //Log.e("123123", Integer.toString(cloclk));
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.e("123", "error");
                }

            }
        }).start();

    }

    public void search()
    {
        for(int i=0 ; i<stateList.size(); i++)
            {
                if(stateList.get(i).isSelected()==true)

                {
                    for(x=0; x<100; x++)
                        {
                            if(stateList.get(i).getName().equals(te[x]))

                                {
                                    status[x]="a"+te[x]+"true";
                                    Log.e("333",String.valueOf(x));

                                }
                        }


                }

            }//搜尋判斷選擇是true跟改陣列內容



        dataAdapter.clear();
        for( x=0 ; x<100; x++){



            if(te[x].contains(txtsearch.getText().toString())){

                if(status[x].equals("a"+te[x]+"true"))
                    {
                        _states = new States("a", te[x], true);
                        stateList.add(_states);
                    }
                else {
                    // Log.e(x+"",te[x]);
                    _states = new States("a", te[x], false);
                    //Log.e("333",_states.getName());
                    stateList.add(_states);
                }//搜尋判斷選擇是true還是false再分別加入
            }

        }
        dataAdapter = new MyCustomAdapter(this,R.layout.state_info, stateList);
        listView.setAdapter(dataAdapter);
    }

    public void displayListView()
    {

        //Array list of countries

        for ( x=0;x<100 ;x++) {  //設定一個for迴圈裡面放陣列動態去抓每一段的a
            status[x] ="a"+te[x]+"false";
            _states = new States("a",te[x],false);

            stateList.add(_states);

        }
        dataAdapter = new MyCustomAdapter(this,R.layout.state_info, stateList);





        //create an ArrayAdaptar from the String Array


//        listView.setOnItemClickListener(new OnItemClickListener()
//        {
//
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            {
//                // When clicked, show a toast with the TextView text
//                States state = (States) parent.getItemAtPosition(position);
//
//            }
//        });


    }
    Runnable runnableUi = new Runnable(){
        @Override
        public void run() {
            //更新界面
            listView.setAdapter(dataAdapter);

        }

    };

    public class MyCustomAdapter extends ArrayAdapter<States>
    {

        private ArrayList<States> stateList;

        public MyCustomAdapter(Context context, int textViewResourceId,

                               ArrayList<States> stateList)
        {
            super(context, textViewResourceId, stateList);
            this.stateList = new ArrayList<States>();
            this.stateList.addAll(stateList);
        }

        private class ViewHolder
        {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {

            ViewHolder holder = null;

            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null)
            {

                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = vi.inflate(R.layout.state_info, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);

                convertView.setTag(holder);

                holder.name.setOnClickListener( new OnClickListener()
                {
                    public void onClick(View v)
                    {
                        CheckBox cb = (CheckBox) v;
                        States _state = (States) cb.getTag();

//                        Toast.makeText(getApplicationContext(), "Clicked on Checkbox: " + cb.getText() + " is " + cb.isChecked(),
//                                Toast.LENGTH_LONG).show();

                        _state.setSelected(cb.isChecked());
                    }
                });

            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            try {
                States state = stateList.get(position);

                holder.code.setText(" (" + state.getCode() + ")");
                holder.name.setText(Html.fromHtml(state.getName()));
                holder.name.setChecked(state.isSelected());

                holder.name.setTag(state);
            }catch (java.lang.NullPointerException e)
            {
                Log.e("333","陣列太少");
            }

            return convertView;
        }

    }

//    private void checkButtonClick()
//    {
//
//        Button myButton = (Button) findViewById(R.id.findSelected);
//
//        myButton.setOnClickListener(new OnClickListener()
//        {
//
//            @Override
//            public void onClick(View v)
//            {
//
//
//            }
//
//
//        });
//    }


}
