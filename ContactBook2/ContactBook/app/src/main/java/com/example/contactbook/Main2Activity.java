package com.example.contactbook;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main2Activity extends Activity {
    Button btnAdd,b;
    EditText Name,Email,Phno;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        btnAdd = findViewById(R.id.button2);
        Name = findViewById(R.id.editText3);
        Email = findViewById(R.id.editText4);
        Phno = findViewById(R.id.editText5);
        b=findViewById(R.id.button3);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0)
            {
                String label = Name.getText().toString( );
                String label1 = Email.getText().toString( );
                String label2 = Phno.getText().toString( );
                if (label.trim().length() > 0 & label1.trim().length() > 0 & label2.trim().length() > 0)
                {
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    db.insertLabel(label,label1,label2);
                    Name.setText("");
                    Email.setText("");
                    Phno.setText("");
                    Toast.makeText(Main2Activity.this, "data added", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getApplicationContext(), "Please enter label name",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(i);
            }
        });
}


    }



