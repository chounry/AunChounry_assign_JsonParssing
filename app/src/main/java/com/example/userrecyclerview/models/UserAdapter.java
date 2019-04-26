package com.example.userrecyclerview.models;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.userrecyclerview.R;
import com.example.userrecyclerview.UserDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private ArrayList<UserModel> users;
    private Context context;
    private RecyclerView recyclerViewHome;
    private View.OnClickListener mOnClickListener;


    public UserAdapter(ArrayList<UserModel> users, Context context, RecyclerView recyclerView) {
        this.users = users;
        this.context = context;
        this.recyclerViewHome = recyclerView;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        mOnClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int itemPos = recyclerViewHome.getChildLayoutPosition(v);
                UserModel user = users.get(itemPos);
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("ID",user.getId());
                intent.putExtra("NAME",user.getName());
                intent.putExtra("ADDRESS",user.getAddress());
                intent.putExtra("IMG",user.getImg());
                context.startActivity(intent);
            }
        };
        View myView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.each_card_view,viewGroup,false);
        myView.setOnClickListener(mOnClickListener);
        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.id_tv.setText("ID : " + users.get(i).getId());
        myViewHolder.name_tv.setText("Name : " + users.get(i).getName());
        myViewHolder.email_tv.setText("Email : " + users.get(i).getEmail());
        myViewHolder.phone_tv.setText("Phone : " + users.get(i).getPhone());
        myViewHolder.dob_tv.setText("DOB : " + users.get(i).getDob());
        Picasso.get().load(users.get(i).getImg()).into(myViewHolder.img_view);

    }


    @Override
    public int getItemCount() {
        return this.users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView id_tv,name_tv,dob_tv,phone_tv,email_tv;
        private ImageView img_view;
        private LinearLayout each_linear_layout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_tv = itemView.findViewById(R.id.id_tv);
            name_tv = itemView.findViewById(R.id.name_tv);
            dob_tv = itemView.findViewById(R.id.dob_tv);
            phone_tv = itemView.findViewById(R.id.phone_tv);
            email_tv = itemView.findViewById(R.id.email_tv);
            img_view = itemView.findViewById(R.id.imgView);
            each_linear_layout = itemView.findViewById(R.id.each_linear_view);
        }
    }
}
