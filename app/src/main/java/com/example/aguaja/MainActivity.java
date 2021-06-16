package com.example.aguaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity{
    private EditText email;
    private EditText senha;
    private TextView lblRest;
    private String url = DomainName.NAME + "/api/client";


    // Componentes do Volley
    private RequestQueue meuQue;
    private StringRequest minhaStringRequest;
    private JsonArrayRequest jsonArrayRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clica(View view) {
         email = (EditText)findViewById(R.id.edit_email);
         senha = (EditText)findViewById(R.id.edit_senha);
        lblRest = (TextView)findViewById(R.id.text);
        login();
    }

    public void login() {
        meuQue = Volley.newRequestQueue(this);
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            StringBuilder texto = new StringBuilder();
                            for(int i = 0; i<response.length(); i++){
                                JSONObject obj = response.getJSONObject(i);
                                String email = obj.getString("email");
                                String senha = obj.getString("password");
                                checaCredenciais(email, senha);
                            }

                        }catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
        });
        meuQue.add(jsonArrayRequest);
    }
    public void checaCredenciais(String email, String senha) {

        if (this.email.getText().toString().equals(email)  && this.senha.getText().toString().equals(senha)  ){
            chamaLista();
        }
    }

    private void chamaLista() {
        Toast toast = Toast.makeText(getApplicationContext(), "Bem Vindo!", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, ListaProdActivity.class);
        startActivity(intent);
    }
}