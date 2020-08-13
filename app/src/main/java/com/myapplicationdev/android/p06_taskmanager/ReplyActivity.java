package com.myapplicationdev.android.p06_taskmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.RemoteInput;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class ReplyActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        CharSequence reply = null;

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        String name = i.getStringExtra("name");

        Bundle remoteInput = RemoteInput.getResultsFromIntent(i);
        if(remoteInput != null){
            reply = remoteInput.getCharSequence("status");
        }

        if(reply == "Completed"){
            DBHelper dbh = new DBHelper(ReplyActivity.this);
            dbh.delete(id);
            dbh.close();
            Toast.makeText(ReplyActivity.this, "You have completed " + name, Toast.LENGTH_LONG).show();
        }
    }
}
