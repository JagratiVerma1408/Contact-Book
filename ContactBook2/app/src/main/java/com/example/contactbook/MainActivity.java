package com.example.contactbook;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler1 helper=new DatabaseHandler1(this);
    EditText e,e1;
    Button b,b1;
    String mn ,cm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e=findViewById(R.id.editText);
        e1=findViewById(R.id.editText2);
        b=findViewById(R.id.button);
        b1=findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mn=e.getText().toString();
                cm=e1.getText().toString();
                String pass=helper.searchpass(mn);
                if (cm.equals(pass)){
                    Intent i=new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(i);

                }
                else{
                    Toast.makeText(MainActivity.this, "signup or incorrect password", Toast.LENGTH_SHORT).show();
                }

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity6.class);
                startActivity(i);

            }
        });


    }
}
