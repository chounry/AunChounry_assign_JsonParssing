package com.example.userrecyclerview;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.userrecyclerview.models.UserAdapter;
import com.example.userrecyclerview.models.UserModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private ArrayList<UserModel> users;
    private RecyclerView recyclerViewHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewHome = findViewById(R.id.recycler_view);
        users = new ArrayList<>();

        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager((this)));

        String url = "https://gorest.co.in/public-api/users?_format=json&access-token=E_oBeFfH0SEnL3Rd8plX9XyOqycPZonCWk82";
        new JsonRequestData().execute(url);



    }


    class JsonRequestData extends AsyncTask<String , Void, String>{

        String result = "";

        @Override
        protected String doInBackground(String... strings) {
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection con =(HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                if(con.getResponseCode() == 200){
                    InputStream is = con.getInputStream();
                    while(true){
                        int data = is.read();
                        if(data == -1)
                            break;
                        else
                            result += (char)data;
                    }
                }
                con.disconnect();
            }catch (Exception ex){
                Log.e("Connection Fail : ",ex.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject json = new JSONObject(s);
                JSONArray newJson = json.getJSONArray("result");
                for(int i = 0; i <newJson.length(); i++){
                    JSONObject each = newJson.getJSONObject(i);
                    String avatar = each.getJSONObject("_links").getJSONObject("avatar").get("href").toString();
//                    Log.e("Jere",each.getString("name"));
                    UserModel user = new UserModel(each.getString("id"),each.getString("name"),each.getString("dob"),each.getString("email"),each.getString("phone"),each.getString("address"));

                    user.setImg(avatar);
                    users.add(user);
                }
                adapter = new UserAdapter(users, MainActivity.this,recyclerViewHome);
                recyclerViewHome.setAdapter(adapter);
                recyclerViewHome.notify();
            }catch (Exception e){
                Log.e("Json error : ",e.toString());
            }
//            Log.e("Something",s);
        }
    }


}
