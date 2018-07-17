package com.androidsrc.client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidsrc.client.server_api.AppController;


public class LoginActivity extends Activity{
    EditText editName,editIP;
    Button btnGo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editName = (EditText)findViewById(R.id.editName);
        editIP = (EditText)findViewById(R.id.editIP);
        btnGo  = (Button)findViewById(R.id.btnGo);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=editIP.getText().toString();
                String address="";
                String ServerURL = "http://192.168.1.201:8000/response.php?server_id="+id;
                StringRequest stringRequest=new StringRequest(Request.Method.POST, ServerURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        SharedPreferences.Editor editor =Common.getPreference(LoginActivity.this).edit();
                        editor.putString(Common.name, editName.getText().toString());
                        editor.putString(Common.ip, response);
                        editor.commit();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                       // Toast.makeText(getApplicationContext(),"result is:  "+response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                AppController.getInstance().addToRequestQueue(stringRequest);


            }
        });

    }
}
