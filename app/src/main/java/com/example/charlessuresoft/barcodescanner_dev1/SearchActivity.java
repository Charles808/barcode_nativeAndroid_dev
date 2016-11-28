package com.example.charlessuresoft.barcodescanner_dev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    private static final String DTAG = "SearchActivity";
    TextView searchView, formatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(DTAG, "Inside SearchActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = (TextView) findViewById(R.id.searchText);
        formatView = (TextView) findViewById(R.id.formatText);

        String searchStr = getIntent().getStringExtra("DATA_STRING");
        String formatStr = getIntent().getStringExtra("FORMAT_STRING");

        searchView.setText(searchStr);
        formatView.setText(formatStr);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
