package com.rai.umesh.cmrlvent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by umesh on 9/6/16.
 */
public class AssetDecipherRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://cmrlvent.co.in/assetMaint/api/web/asset-code/decipher?assetCode=";

    public AssetDecipherRequest (String assetCode, Response.Listener<String> listener){
        super(Request.Method.GET, LOGIN_REQUEST_URL + assetCode, listener, null);

    }

}
