package com.rai.umesh.cmrlvent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        Intent intent = getIntent();
        String userid = intent.getStringExtra("userid");
        String username = intent.getStringExtra("username");
        String organisation = intent.getStringExtra("organisation");
        String auth_key = intent.getStringExtra("auth_key");

        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        TextView tvUserid = (TextView) findViewById(R.id.tvUserid);
        TextView tvContractor = (TextView) findViewById(R.id.tvContractor);
        TextView tvAccesskey = (TextView) findViewById(R.id.tvAccesskey);

        tvUsername.setText("Username: "+ username);
        tvUserid.setText("User ID: "+ userid);
        tvContractor.setText("Contractor: "+ organisation);
        tvAccesskey.setText("Key: "+ auth_key);

    }
}
