package com.example.speechtotext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import com.example.speechtotext.resultParse;
import com.example.speechtotext.Item_Entity;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.recyclerview);
    }

    // Initialize speech recognizer
    public void getSpeechInput(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Item db = Room.databaseBuilder(getApplicationContext(),
//                Item.class, "database-name").build();

        if (requestCode == 1){
            if (resultCode == RESULT_OK && data != null){
                String[] results = data.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS);
                // Show results on textview
                textResult.setText(results[0]);

                //Parse result and put it into database
                try {
                    String test = "put item1 in location2";
                    resultParse rp = new resultParse();
                    rp.parseResult(test);
                } catch(IOException e){
                    System.out.println("IOEXception");
                }



            }
        }
    }
}
