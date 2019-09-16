package com.example.thuli001;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp2 extends AppCompatActivity implements View.OnClickListener {

    EditText mEmailField;
    EditText mPasswordField;
    EditText mPasswordconfirmField;
    Button mSignupButton;
    TextView mLoginLink;

    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mEmailField = (EditText) findViewById(R.id.email_field);
        mPasswordField = (EditText) findViewById(R.id.password_field);
        mPasswordconfirmField = (EditText) findViewById(R.id.passwordconfirm_field);
        mSignupButton = (Button) findViewById(R.id.signup_button);

        mLoginLink = (TextView) findViewById(R.id.login_link);
        mLoginLink.setOnClickListener(this);
        mSignupButton.setOnClickListener(this);
    }
    private void Sign(){
        String email = mEmailField.getText().toString().trim();
        String password = mPasswordField.getText().toString();
        String cpassword = mPasswordconfirmField.getText().toString();
        if(email.isEmpty()) {
            mEmailField.setError("Enter Email");
            mEmailField.requestFocus();
        }
        else if(password.isEmpty()) {
            mPasswordField.setError("Enter Password");
            mPasswordField.requestFocus();
        }
        else if(cpassword.isEmpty()){
            mPasswordField.setError("Enter Password Confirmation");
            mPasswordField.requestFocus();
        }
        else if(password.equals(cpassword)){
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp2.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp2.this, "Successful", Toast.LENGTH_SHORT).show();
                        Intent I = new Intent(SignUp2.this, SignUp.class);
                        startActivity(I);
                        finish();

                    } else {
                        Toast.makeText(SignUp2.this, "Not successful", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        else {
            Toast.makeText(this, "Password not matching!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_button:
                Sign();
                break;
            case R.id.login_link:{
                Intent I = new Intent(SignUp2.this, Login.class);
                startActivity(I);
                finish();
            }
        }

    }
}
