package com.example.ehte6848.sampletodolist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by ehte6848 on 10-06-2017.
 */

public class NewActivity extends Activity{
    private TextView passedView = null;
    String passedVar = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newactivity);
        //get passed variable
        passedVar = getIntent().getStringExtra(MainActivity.ID_EXTRA);
        //set TextView
        passedView = (TextView)findViewById(R.id.textView);
        passedView.setText(passedVar);
    }
}
