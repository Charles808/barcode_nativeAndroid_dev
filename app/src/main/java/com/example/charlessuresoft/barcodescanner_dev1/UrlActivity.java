package com.example.charlessuresoft.barcodescanner_dev1;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UrlActivity extends AppCompatActivity {

    private TextView urlView;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        urlView = (TextView) findViewById(R.id.urlText);
        String urlStr = getIntent().getStringExtra("DATA_STRING");
        urlView.setText(urlStr);
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

    public void gotoUrlApp (View view) {
        Uri uriUrl = Uri.parse(urlView.getText().toString());
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public void copyUrlApp (View view) {
        myClip = ClipData.newPlainText("text", urlView.getText().toString());
        myClipboard.setPrimaryClip(myClip);
    }
}
