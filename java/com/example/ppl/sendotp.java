package com.example.ppl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class sendotp extends AppCompatActivity {

     MaterialButton send_otp  ;
     TextInputEditText mobile_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendotp);
        send_otp = (MaterialButton) findViewById(R.id.sendotp);
        mobile_no = (TextInputEditText) findViewById(R.id.mobileno);

        send_otp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mobile_no.getText().toString().trim().isEmpty()){
                Toast.makeText(sendotp.this, "ENTER MOBILE", Toast.LENGTH_SHORT).show();
                return;}
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" +mobile_no.getText().toString(),
                        30,

                        TimeUnit.SECONDS,
                        sendotp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(sendotp.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                Intent intent = new Intent(getApplicationContext(),verifyotp.class);
                                intent.putExtra("mobile",mobile_no.getText().toString());
                                intent.putExtra("verificationId",verificationId);

                                startActivity(intent);

                            }
                        }
                );





            }



        });
    }

}