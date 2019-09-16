package com.example.thuli001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.mEmailId);
        password = (EditText) findViewById(R.id.mPassword);
        login = (Button) findViewById(R.id.mLOginSubmit);
        login.setOnClickListener(this);
    }

    private void submit(){
        if(email.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Email not entered!", Toast.LENGTH_LONG).show();
        }
        else if(password.getText().toString().isEmpty()){
            Toast.makeText(this, "Password not entered!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(Login.this,MainActivity.class );
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mLOginSubmit:
                submit();
                break;
        }
    }


}
