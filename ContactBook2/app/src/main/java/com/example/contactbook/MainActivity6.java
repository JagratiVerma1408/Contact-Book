package com.example.contactbook;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {
    DatabaseHandler1 helper=new DatabaseHandler1(this);
    Button d;
    EditText e,e1,e2;
    String  nm,cm,us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        e = findViewById(R.id.editTextTextPersonName12);
        e1 = findViewById(R.id.editTextTextPassword12);
        e2 = findViewById(R.id.editTextTextPassword13);
        d = findViewById(R.id.button13);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nm = e1.getText().toString();
                cm = e2.getText().toString();
                us = e.getText().toString();
                if (nm.equals(cm)) {
                    Contact1 c = new Contact1();
                    c.setUsername(us);
                    c.setPassword(nm);
                    helper.insertcontacts(c);
                    Toast.makeText(MainActivity6.this, "REGISTERED", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(MainActivity6.this, "password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }}

