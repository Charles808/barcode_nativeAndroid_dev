package com.example.charlessuresoft.barcodescanner_dev1;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    private static final String DTAG = "SearchActivity";
    private TextView searchView, formatView;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(DTAG, "Inside SearchActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = (TextView) findViewById(R.id.searchText);
        formatView = (TextView) findViewById(R.id.formatText);

        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        String searchStr = getIntent().getStringExtra("DATA_STRING");
        String formatStr = getIntent().getStringExtra("FORMAT_STRING");

        searchView.setText(searchStr);
        formatView.setText("Format : " + formatStr);
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

    public void searchApp(View view) {
        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
        search.putExtra(SearchManager.QUERY, searchView.getText());
        startActivity(search);
    }

    public void copyContentApp (View view) {
        myClip = ClipData.newPlainText("text", searchView.getText().toString());
        myClipboard.setPrimaryClip(myClip);
    }
}
