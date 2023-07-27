package com.iseries.notes;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ((TextView)findViewById(R.id.create_account_text_view_btn)).setOnClickListener((v)->startActivity(new Intent(ForgotPasswordActivity.this,CreateAccountActivity.class)) );
        ((TextView)findViewById(R.id.login_btn)).setOnClickListener((v)->startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class)) );
        ((Button)findViewById(R.id.forgot_password_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotpasswordInFirebase();
            }
        });
    }
    void forgotpasswordInFirebase() {
        EditText emile = (EditText) findViewById(R.id.email_edit_text);
        if (emile.getText().length() > 5) {
            Toast.makeText(this, "Sending..", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().sendPasswordResetEmail(emile.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPasswordActivity.this, "Send Successful", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "Email sent.");
                                startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
                                finish();
                            }
                        }
                    });
        }else {
            Toast.makeText(this, "Enter email id ?", Toast.LENGTH_SHORT).show();
        }
    }
}