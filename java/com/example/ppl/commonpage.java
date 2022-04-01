package com.example.ppl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class commonpage extends AppCompatActivity {

    MaterialButton button1 ,button2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commonpage);
        button1 = (MaterialButton) findViewById(R.id.auth);
        button2 = (MaterialButton) findViewById(R.id.people);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),authoritylogin.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),sendotp.class);
                startActivity(intent);
            }
        });

    }

}