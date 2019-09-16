package com.example.thuli001;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp2 extends AppCompatActivity implements View.OnClickListener {
    TextView sign;
    TextView email;
    EditText emailid;

    TextView pass;
    EditText passw;

    TextView cpass;
    EditText cpassw;
    Button Signup2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        sign = (TextView) findViewById(R.id.sign);
        email = (TextView) findViewById(R.id.temail);
        emailid = (EditText) findViewById(R.id.eemail);

        pass= (TextView) findViewById(R.id.tpass);
        passw = (EditText) findViewById(R.id.epass);
        cpass = (TextView) findViewById(R.id.tcpass);
        cpassw = (EditText) findViewById(R.id.ecpass);
        Signup2 = (Button) findViewById(R.id.signup2);
        Signup2.setOnClickListener(this);
    }
    private void submit(){
        String password = passw.getText().toString();
        String cpassword = cpassw.getText().toString();
        if(emailid.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Email not entered!", Toast.LENGTH_LONG).show();
        }

        else if(passw.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Password not entered!", Toast.LENGTH_LONG).show();
        }
        else if(cpassw.getText().toString().isEmpty()){
            Toast.makeText(this, "Password not confirmed!", Toast.LENGTH_LONG).show();
        }

        else if(password.equals(cpassword)){
            Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(SignUp2.this,MainActivity.class );
            startActivity(intent);
            finish();


        }
        else {
            Toast.makeText(this, "Password not matching!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup2:
                submit();
                break;
        }
    }
}
