package com.example.userrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class UserDetailActivity extends AppCompatActivity {
    ImageView imgView;
    TextView idTv,nameTv,addressTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        idTv = findViewById(R.id.id_tv);
        nameTv = findViewById(R.id.name_tv);
        addressTv = findViewById(R.id.address_tv);
        imgView = findViewById(R.id.detail_img_view);

        Intent intent = getIntent();

        idTv.setText("ID : " + intent.getStringExtra("ID"));
        nameTv.setText("Name : " + intent.getStringExtra("NAME"));
        addressTv.setText("Address : " + intent.getStringExtra("ADDRESS"));
        Picasso.get().load(intent.getStringExtra("IMG")).into(imgView);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
