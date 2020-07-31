package com.example.contactbook;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity  {
    ListView l;
    String nm1, em1, cont1;
    String nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toast.makeText(this, "SINGLE CLICK TO STORE DATA AND LONG PRESS FOR CALL SMS AND EMAIL", Toast.LENGTH_LONG).show();
        l = findViewById(R.id.listview);
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        Contact c1 = new Contact();
        List<String> labels = db.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, labels);
        l.setAdapter(dataAdapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
                String nm = parent.getItemAtPosition(p).toString();
                Contact c1= new Contact();
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                List<Contact> l = db.getAllContacts();
                for (Contact c : l) {
                    nm1 = c.getName();
                    if (nm.equals(nm1)) {
                        em1 = c.getEmail();
                        cont1 = c.getPhno();
                        Toast.makeText(MainActivity3.this, "email id:"+em1+"\n "+"contact number:"+cont1, Toast.LENGTH_SHORT).show();
                        break;

                    }

                }
            }


        });
        registerForContextMenu(l);}
    public void onCreateContextMenu(ContextMenu m, View v, ContextMenu.ContextMenuInfo i){
        m.setHeaderTitle("select any one");
        m.add(0,v.getId(),0,"call");
        m.add(0,v.getId(),0,"sms");
        m.add(0,v.getId(),0,"email");

    }
    public boolean onContextItemSelected(MenuItem m){
        if(m.getTitle().equals("call")){
            Intent i=new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:"+cont1));
            startActivity(i);
            Toast.makeText(this, "Calling  to "+nm1, Toast.LENGTH_SHORT).show();


        }
        if(m.getTitle().equals("sms")){

            Intent i=new Intent(getApplicationContext(),MainActivity4.class);
            i.putExtra("mob",cont1);
            i.putExtra("name",nm1);
            startActivity(i);
        }
        if(m.getTitle().equals("email")){

            Intent i=new Intent(getApplicationContext(),MainActivity5.class);
            i.putExtra("email",em1);
            i.putExtra("name",nm1);
            startActivity(i);
        }
        return true;
    }


}

