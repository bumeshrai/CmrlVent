package com.rai.umesh.cmrlvent;

/**
 * Created by umesh on 9/6/16.
 */

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UserLoginRequest  extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://cmrlvent.co.in/assetMaint/api/web/user/login";
    private Map<String, String> params;

    public UserLoginRequest(String username, String password,  String latitude, String longitude ,Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("latitude", latitude);
        params.put("longitude", longitude);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
