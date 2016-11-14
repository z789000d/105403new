package com.example.draggridview;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RecordAdapter extends BaseAdapter {
    private Context context;
    private List<HashMap<String, Object>> listItem;

    public RecordAdapter(Context context,
                         List<HashMap<String, Object>> listitem) {
        super();
        this.context = context;
        this.listItem = listitem;
    }

    public int getCount() {
        return listItem.size();
    }

    public Object getItem(int arg0) {
        return listItem.get(arg0);
    }

    public long getItemId(int arg0) {
        return arg0;
    }



    public View getView(int position , View convertView, ViewGroup parent) {

        // 取出数据
        String wordsStr = listItem.get(position).get("words").toString();
        View tem;
        Log.d("test",wordsStr);
        if (convertView == null) {

            RelativeLayout temRl = (RelativeLayout) View.inflate(context,
                    R.layout.grid_item, null);



            TextViewFixTouchConsume words =(TextViewFixTouchConsume) temRl.findViewById(R.id.words_home_function_1);
//            words.setText(Html.fromHtml("<a href=\"http://pres.ntub.edu.tw/\" >\n 校長室\n</a>"));
            //words.setTextViewHTML(wordsStr);
//            words.setText("<a href=\"http://www.ntub.edu.tw/\" title=\"行政單位\"><div class=\"menu-item\">行政單位</div></a>");
            words.setMovementMethod(TextViewFixTouchConsume.LocalLinkMovementMethod.getInstance());
            words.setText(Html.fromHtml(wordsStr));
            words.setBackgroundColor(Color.RED);
            words.setLinkTextColor(Color.BLACK);
            words.setMinHeight(300);
            words.setGravity(Gravity.CENTER);




//            WebView words =(WebView) temRl.findViewById(R.id.words_home_function_1);
//            words.getSettings().setDefaultTextEncodingName("utf-8");
//            words.loadDataWithBaseURL("",wordsStr, "text/html", "utf-8","");
            tem = temRl;
        } else {
            tem = convertView;


            TextViewFixTouchConsume words =(TextViewFixTouchConsume) tem.findViewById(R.id.words_home_function_1);
//            words.setText(Html.fromHtml("<a href=\"http://pres.ntub.edu.tw/\" >\n 校長室\n</a>"));
//            words.setTextViewHTML(wordsStr);
//            words.setText("<a href=\"http://www.ntub.edu.tw/\" title=\"行政單位\"><div class=\"menu-item\">行政單位</div></a>");
            words.setMovementMethod(TextViewFixTouchConsume.LocalLinkMovementMethod.getInstance());
            words.setText(Html.fromHtml(wordsStr));
            words.setBackgroundColor(Color.RED);
            words.setLinkTextColor(Color.BLACK);
            words.setMinHeight(300);
            words.setGravity(Gravity.CENTER);



//            WebView words =(WebView) tem.findViewById(R.id.words_home_function_1);
//            words.getSettings().setDefaultTextEncodingName("UTF-8");
//            words.loadDataWithBaseURL("",wordsStr, "text/html", "utf-8","");
        }
        return tem;

    }


}
