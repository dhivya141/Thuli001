package com.example.thuli001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private TextView mSignupLink;
    private TextView mForgotPassword;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailField = (EditText) findViewById(R.id.email_field);
        mPasswordField = (EditText) findViewById(R.id.password_field);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mSignupLink = (TextView) findViewById(R.id.signup_link);
        mForgotPassword=(TextView) findViewById(R.id.forgot_password);

        mSignupLink.setOnClickListener(this);
        mForgotPassword.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    finish();
                    Toast.makeText(Login.this, "User connected ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    Toast.makeText(Login.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };
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
                           checkEmailVerification();

                       } else {
                           Toast.makeText(Login.this, "Not successful", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }
       }
       private void checkEmailVerification()
       {
           FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
           Boolean emailflag=firebaseUser.isEmailVerified();
           if(emailflag==true){
               finish();
               startActivity(new Intent(Login.this, MainActivity.class));
           }
           else{
               Toast.makeText(Login.this, "Verify email", Toast.LENGTH_SHORT).show();
               mAuth.signOut();
           }
       }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                login();
                break;

            case R.id.signup_link:
                finish();
                startActivity(new Intent(Login.this, SignUp2.class));
                break;
            case R.id.forgot_password:
                finish();
                startActivity(new Intent(Login.this, PasswordReset.class));
                break;

        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


}
