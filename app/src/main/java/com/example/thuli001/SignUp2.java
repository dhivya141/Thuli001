package com.example.thuli001;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp2 extends AppCompatActivity implements View.OnClickListener {

    public EditText mNameField;
    public EditText mContactField;
    public EditText mAddressField;
    public Spinner mSpinner;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mPasswordconfirmField;
    private Button mSignupButton;
    private TextView mLoginLink;

    DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    String username, userphno, useraddress, userlocation, useremail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mNameField = (EditText) findViewById(R.id.name_field);
        mContactField = (EditText) findViewById(R.id.contact_field);
        mAddressField = (EditText) findViewById(R.id.address_field);

        mEmailField = (EditText) findViewById(R.id.email_field);
        mPasswordField = (EditText) findViewById(R.id.password_field);
        mPasswordconfirmField = (EditText) findViewById(R.id.passwordconfirm_field);
        mSignupButton = (Button) findViewById(R.id.signup_button);
        mLoginLink = (TextView) findViewById(R.id.signup_link);

        mLoginLink.setOnClickListener(this);
        mSignupButton.setOnClickListener(this);
    }
    private void Sign(){

        username=mNameField.getText().toString().trim();
        userphno=mContactField.getText().toString().trim();
        useraddress=mAddressField.getText().toString().trim();

        useremail = mEmailField.getText().toString().trim();
        String password = mPasswordField.getText().toString();
        String cpassword = mPasswordconfirmField.getText().toString();

        if(username.isEmpty())
        {   mNameField.setError("Enter Name");
            mNameField.requestFocus();
        } else if(userphno.isEmpty())
        {   mContactField.setError("Enter Contact");
            mContactField.requestFocus();
        } else if(useraddress.isEmpty()) {
            mAddressField.setError("Enter Address");
            mAddressField.requestFocus();
        }
        else if(useremail.isEmpty()) {
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
            mAuth.createUserWithEmailAndPassword(useremail, password).addOnCompleteListener(SignUp2.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        sendEmailVerification();
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
    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(SignUp2.this, "Successfully verified ", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        finish();
                        startActivity(new Intent(SignUp2.this, Login.class));

                    }
                }
            });
        }
        else{
            Toast.makeText(SignUp2.this, "Verify email", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef= firebaseDatabase.getReference(mAuth.getUid());
        UserProfile userprofile = new UserProfile(username, userphno, useraddress, useremail);
        myRef.setValue(userprofile);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_button:
                Sign();
                break;
            case R.id.signup_link:{
                Intent I = new Intent(SignUp2.this, Login.class);
                startActivity(I);
                finish();
            }
        }

    }
}

