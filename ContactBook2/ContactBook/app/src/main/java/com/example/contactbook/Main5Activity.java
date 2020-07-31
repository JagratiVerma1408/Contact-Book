package com.example.contactbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class Main5Activity extends AppCompatActivity implements  TextToSpeech.OnInitListener  {
    private TextToSpeech tts;
    EditText e,e1,e2,e3;
    Button b;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Bundle data=getIntent().getExtras();
        final String to=data.getString("email");
        final String t=data.getString("name");
        tts = new TextToSpeech(this, this);
        e=findViewById(R.id.editText);
        e1=findViewById(R.id.editText2);
        e2=findViewById(R.id.editText3);
        b=findViewById(R.id.button);
        e.setText(to);
        text=t;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sub=e1.getText().toString();
                String msg=e2.getText().toString();
                Intent i=new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
                i.putExtra(Intent.EXTRA_SUBJECT,sub);
                i.putExtra(Intent.EXTRA_TEXT,msg);
                i.setType("message/rfc822");
                startActivity(i);
                tts.speak("Sending email to "+text, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(Main5Activity.this, "Sending email to "+text, Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                b.setEnabled(true);
            }

        } else { Log.e("TTS", "Initilization Failed!");}
    }

}
