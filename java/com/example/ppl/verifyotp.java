package com.example.ppl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class verifyotp extends AppCompatActivity {
     private EditText code1,code2,code3,code4,code5,code6;
     TextInputEditText mobile_no;
     MaterialButton verifyotp;
     private String verificationId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);

        code1 = (EditText) findViewById(R.id.inputcode1);
        code2 = (EditText) findViewById(R.id.inputcode2);
        code3 = (EditText) findViewById(R.id.inputcode3);
        code4 = (EditText) findViewById(R.id.inputcode4);
        code5 = (EditText) findViewById(R.id.inputcode5);
        code6 = (EditText) findViewById(R.id.inputcode6);
        mobile_no=(TextInputEditText) findViewById(R.id.mobileno);
        verifyotp=(MaterialButton)findViewById(R.id.verifyotp);
        setupotpinputs();
        verificationId=getIntent().getStringExtra("verificationId");
        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(code1.getText().toString().trim().isEmpty()
                        ||code2.getText().toString().trim().isEmpty()
                        || code3.getText().toString().trim().isEmpty()
                        ||code4.getText().toString().trim().isEmpty()
                        ||code5.getText().toString().trim().isEmpty()
                        ||code6.getText().toString().trim().isEmpty()){
                    Toast.makeText(verifyotp.this, "Enter valid CODE", Toast.LENGTH_SHORT).show();
                    return;

                }
                String code=
                        code1.getText().toString() +
                                code2.getText().toString() +
                                code3.getText().toString() +
                                code4.getText().toString() +
                                code5.getText().toString() +
                                code6.getText().toString() ;
                if(verificationId!=null){
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId,
                            code

                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }else {
                                        Toast.makeText(verifyotp.this, "Invalid", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });
    }
    private  void setupotpinputs(){
        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before , int count) {
                if(!s.toString().trim().isEmpty()){
                   code2.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before , int count) {
                if(!s.toString().trim().isEmpty()){
                    code3.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before , int count) {
                if(!s.toString().trim().isEmpty()){
                    code4.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before , int count) {
                if(!s.toString().trim().isEmpty()){
                    code5.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before , int count) {
                if(!s.toString().trim().isEmpty()){
                    code6.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

}