package com.example.thuli001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener {

    public EditText mEmailField;
    public EditText mPasswordField;
    Button mLoginButton;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailField = (EditText) findViewById(R.id.email_field);
        mPasswordField = (EditText) findViewById(R.id.password_field);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(Login.this, "User logged in ", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(Login.this, MainActivity.class);
                    startActivity(I);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

        mLoginButton.setOnClickListener(this);

    }
       private void login() {
           String userEmail = mEmailField.getText().toString();
           String userPaswd = mPasswordField.getText().toString();
           if (userEmail.isEmpty()) {
               mEmailField.setError("Enter Email");
               mEmailField.requestFocus();
           } else if (userPaswd.isEmpty()) {
               mPasswordField.setError("Enter Password");
               mPasswordField.requestFocus();
           } else {
               mAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           Intent I = new Intent(Login.this, MainActivity.class);
                           startActivity(I);
                           finish();

                       } else {
                           Toast.makeText(Login.this, "Not successful", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }
       }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                login();
                break;

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


}
