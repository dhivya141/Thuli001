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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    public EditText mNameField;
    public EditText mContactField;
    public EditText mAddressField;
    public Spinner mSpinner;
    Button mNextButton;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mNameField = (EditText) findViewById(R.id.name_field);
        mContactField = (EditText) findViewById(R.id.contact_field);
        mAddressField = (EditText) findViewById(R.id.address_field);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(this);

    }
    private void Next(){
        String name=mNameField.getText().toString().trim();
        String phno=mContactField.getText().toString().trim();
        String address=mAddressField.getText().toString().trim();
        String location = mSpinner.getSelectedItem().toString();
        if(name.isEmpty())
        {   mNameField.setError("Enter Name");
            mNameField.requestFocus();
        } else if(phno.isEmpty())
        {   mContactField.setError("Enter Contact");
            mContactField.requestFocus();
        } else if(address.isEmpty()) {
            mAddressField.setError("Enter Address");
            mAddressField.requestFocus();
        } else {
            HashMap<String, String> dataMap=new HashMap<String, String>();
            dataMap.put("Name", name);
            dataMap.put("Phone", phno);
            dataMap.put("Address", address);
            dataMap.put("Location", location);
            mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Intent I = new Intent(SignUp.this, MainActivity.class);
                        startActivity(I);
                        finish();
                    }
                    else{
                        Toast.makeText(SignUp.this,"Error", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_button: {
                Next();
                break;
            }

        }
    }
}

