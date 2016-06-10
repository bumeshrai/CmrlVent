package com.rai.umesh.cmrlvent;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvAssetcode = (TextView) findViewById(R.id.tvAssetcode);
        final Button btLogin = (Button) findViewById(R.id.btLogin);

        GPSTracker gps = new GPSTracker(this);
        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Log.i("value",  "getLatitude: " + latitude+"\n" + "getLongitude: " + longitude);
        } else {
            Log.i("value",  "get location failed: ");
        }

        tvAssetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent assetIntent = new Intent(UserActivity.this, AssetDecipherActivity.class);
                UserActivity.this.startActivity(assetIntent);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            Log.i("value", jsonResponse.toString());
                            boolean success = jsonResponse.getJSONObject("data").getBoolean("success");
                            String userid = jsonResponse.getJSONObject("data").getString("id");
                            String organisation = jsonResponse.getJSONObject("data").getString("organisation");
                            String auth_key = jsonResponse.getJSONObject("data").getString("auth_key");
                            //Log.i("value",  "success: " + String.valueOf(success) + ", auth_key: "+ auth_key);

                            if (success) {
                                Intent intent = new Intent(UserActivity.this, UserDataActivity.class);
                                intent.putExtra("userid", userid);
                                intent.putExtra("username", username);
                                intent.putExtra("organisation", organisation);
                                intent.putExtra("auth_key", auth_key);
                                UserActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UserLoginRequest loginRequest = new UserLoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
