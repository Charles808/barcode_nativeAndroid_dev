package com.example.charlessuresoft.barcodescanner_dev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UrlActivity extends AppCompatActivity {

    TextView urlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        urlView = (TextView) findViewById(R.id.urlText);
    }
}
