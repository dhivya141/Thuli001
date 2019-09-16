package com.example.thuli001;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    TextView regn;
    TextView fn;
    EditText fname;

    TextView contact;
    EditText ctno;
    TextView addr;
    EditText address;
    TextView locn;
    Spinner spin;
    Button next;
    TextView already;

    String[] area = {"Kodambakkam", "Anna Nagar", "Adyar", "Gopalapuram", "Guindy"};
    ArrayAdapter aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regn = (TextView) findViewById(R.id.signup);
        fn = (TextView) findViewById(R.id.tname);
        fname = (EditText) findViewById(R.id.ename);

        contact = (TextView) findViewById(R.id.tcontact);
        ctno = (EditText) findViewById(R.id.econtact);
        addr = (TextView) findViewById(R.id.taddr);
        address = (EditText) findViewById(R.id.eaddr);
        locn = (TextView) findViewById(R.id.location);
        spin = (Spinner) findViewById(R.id.spinner);
        next = (Button) findViewById(R.id.buttonnext);
        already = (TextView) findViewById(R.id.log);

        aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, area);
        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        next.setOnClickListener(this);
        already.setOnClickListener(this);
    }
    private void submit(){
        if(fname.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Name not entered!", Toast.LENGTH_LONG).show();
        }

        else if(ctno.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Contact number not entered!", Toast.LENGTH_LONG).show();
        }
        else if(address.getText().toString().isEmpty()){
            Toast.makeText(this, "Address not entered!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Complete the Sign Up!", Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(SignUp.this,SignUp2.class );
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonnext:
                submit();
                break;
            case  R.id.log:
                Intent intent =new Intent(SignUp.this,Login.class );
                startActivity(intent);
                finish();
                break;

        }
    }
}

