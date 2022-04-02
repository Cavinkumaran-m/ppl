package com.example.ppl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView greivance ,complaintaroundu,status,brain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        greivance = (CardView) findViewById(R.id.greivance);
        complaintaroundu = (CardView) findViewById(R.id.complaintsaroundu);
        status = (CardView) findViewById(R.id.status);
        brain = (CardView) findViewById(R.id.brain);

        greivance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),grievance.class);
                startActivity(intent);

            }
        });
        complaintaroundu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),complaints_around_you.class);
                startActivity(intent);

            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        brain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}