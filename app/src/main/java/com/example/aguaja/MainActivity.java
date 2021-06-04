package com.example.aguaja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnEmail = (Button) findViewById(R.id.btn_login);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_lista_prod);
            }
        });

    }
}