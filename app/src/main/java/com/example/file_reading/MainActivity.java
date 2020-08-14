package com.example.file_reading;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);

//        final ImageButton record_Btn = (ImageButton) findViewById(R.id.play_btn);
//        record_Btn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                record_Btn.setSelected(true);
//            }
//        });



    }

    public void clickBtn(View view) {

        //json 파일 읽어와서 분석하기

        //assets폴더의 파일을 가져오기 위해
        //창고관리자(AssetManager) 얻어오기
        AssetManager assetManager= getAssets();

        //assets/ test.json 파일 읽기 위한 InputStream
        try {
            InputStream is= assetManager.open("jsons/test1.json");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader reader= new BufferedReader(isr);

            StringBuffer buffer= new StringBuffer();
            String line= reader.readLine();
            while (line!=null){
                buffer.append(line+"\n");
                line=reader.readLine();
            }

            String jsonData= buffer.toString();




            //json 데이터가 []로 시작하는 배열
            JSONArray jsonArray= new JSONArray(jsonData);

            String s="";

            for(int i=0; i<jsonArray.length();i++){
                JSONObject jo=jsonArray.getJSONObject(i);

                String index= jo.getString("index");
                String speaking= jo.getString("speaking");
                //JSONObject flag=jo.getJSONObject("flag");
                String st= jo.getString("startTime");
                String et= jo.getString("endTime");

                s +="speaking index"+"  "+index+"                                              "+st+"~"+et+"\n"+"\n"+"   "+speaking+"\n"+"\n"+"\n";
            }
            tv.setText(s);

        } catch (IOException e) {e.printStackTrace();} catch (JSONException e) {e.printStackTrace(); }

    }

}



