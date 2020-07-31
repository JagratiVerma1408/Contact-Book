package com.example.contactbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class Main4Activity extends AppCompatActivity  implements  TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    EditText e,e1;
    Button b;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Bundle data=getIntent().getExtras();
        final String mob=data.getString("mob");
        final String t=data.getString("name");
        tts = new TextToSpeech(this, this);
        e=findViewById(R.id.editText);
        b=findViewById(R.id.button);
        e1=findViewById(R.id.editText2);
        e1.setText(mob);
        text= t;

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=e.getText().toString();
                Intent i=new Intent();
                SmsManager sms = SmsManager.getDefault();
                PendingIntent p=PendingIntent.getActivity(getApplicationContext(),0,i,0);
                i.setData(Uri.parse("tel:"+mob));
                sms.sendTextMessage(mob,null,msg,p,null);
                tts.speak("Sending message to "+text, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(getApplicationContext(), "Message Send to"+" "+text, Toast.LENGTH_SHORT).show();
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

