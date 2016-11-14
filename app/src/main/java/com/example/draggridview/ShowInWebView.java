package com.example.draggridview;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.example.draggridview.DragGridView.OnChanageListener;

public class ShowInWebView extends Activity {

	List<HashMap<String, Object>> dataSourceList = new ArrayList<HashMap<String, Object>>();
	URL url;

	String  geturl;
	DragGridView mDragGridView;
	RecordAdapter mSimpleAdapter;
	String te[] = new String[100];
	Handler handler=new Handler();
	String ArrayTextSplit[] ;
	private SQLiteDatabase db;
	String Array;
	String edit1;
	String Url;
	Button bt01;


	@Override
	protected void onCreate(Bundle savedInstanceState) {



		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showinwebview);
		bt01 = (Button)findViewById(R.id.button3);

		Bundle bundle = this.getIntent().getExtras();
		Array = bundle.getString("Array");
		Url = bundle.getString("Url");
		ArrayTextSplit = Array.split("----");
		MyDataDB dbHelper = new MyDataDB(this);
		db = dbHelper.getWritableDatabase();

		mSimpleAdapter = new RecordAdapter(this,dataSourceList);
//		LayoutInflater inflater = getLayoutInflater();
//		View otherLayout = inflater.inflate(R.layout.grid_item, null);
//		TextView otherTv1 = (TextView)otherLayout.findViewById(R.id.words_home_function_1);


		mDragGridView = (DragGridView) findViewById(R.id.dragGridView);

				Thread threadc =new Thread(){

					Thread threadB = new Thread(new Runnable() {
						public void run(){
							try {




								for (int x=1; x<ArrayTextSplit.length ;x++) {  //設定一個for迴圈裡面放陣列動態去抓每一段的a

									//以下去除div標籤
									ArrayTextSplit[x] = ArrayTextSplit[x].replace("<div>", "");
									ArrayTextSplit[x] = ArrayTextSplit[x].replace("</div>","");
									Log.e("22",ArrayTextSplit[x]);
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
							for (int x=1; x<ArrayTextSplit.length ;x++) {
								HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
								itemHashMap.put("words", ArrayTextSplit[x]);
								dataSourceList.add(itemHashMap);
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
				bt01.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						LayoutInflater inflater = LayoutInflater.from(ShowInWebView.this);
						final View v = inflater.inflate(R.layout.alertdialog_use, null);
						final AlertDialog.Builder dialog = new AlertDialog.Builder(ShowInWebView.this);

						dialog.setTitle("輸入名稱");
						dialog.setView(v);
						dialog.setMessage("輸入網頁名稱");
						dialog.setPositiveButton("確定",new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								MyDataDB db = new MyDataDB(ShowInWebView.this);
								EditText editText = (EditText) (v.findViewById(R.id.editText1));

								edit1 =  editText.getText().toString();
								for (int x=0; x<ArrayTextSplit.length-1 ;x++) {

									db.insert(mSimpleAdapter.getItem(x).toString().replace("{words=","").replace("}","").replace("..",Url),edit1.toString());


								}



								Intent intent = new Intent();
								intent.putExtra("edit",edit1);
								intent.setClass(ShowInWebView.this,WebList.class);
								startActivity(intent);
							}

						});
						dialog.setNeutralButton("取消",new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub

							}

						});
						dialog.show();
					}
				});

			}







	Runnable runnableUi = new Runnable(){
		@Override
		public void run() {
			//更新界面
			mDragGridView.setAdapter(mSimpleAdapter);
			mDragGridView.setOnChangeListener(new OnChanageListener() {

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

	public void add()

	{



		//db.select();
//		for (int x=1; x<100; x++ ) {
//			db.delete(x);
//		}



	}

}
