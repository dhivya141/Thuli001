package com.example.thuli001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class choice extends AppCompatActivity implements View.OnClickListener {

    TextView iama;
    Button ok;
    RadioButton r1;
    RadioButton r2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        iama = (TextView) findViewById(R.id.iama);
        r1 = (RadioButton) findViewById(R.id.radio_user);
        r2 = (RadioButton) findViewById(R.id.radio_contr);
        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){
        switch (view.getId()){

            case R.id.radio_user:
                Intent intent = new Intent(choice.this, Login.class);
                startActivity(intent);
            case R.id.radio_contr:
                Intent intent1 = new Intent(choice.this,login_contr.class);
                startActivity(intent1);

        }
    }
}
