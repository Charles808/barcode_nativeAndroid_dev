package com.example.charlessuresoft.barcodescanner_dev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class VcardActivity extends AppCompatActivity {

    TextView vcardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcard);

        vcardView = (TextView) findViewById(R.id.vcardText);
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

    public void addToContactApp(View view) {
        Toast.makeText(getApplicationContext(), "Not Implemented yet...", Toast.LENGTH_SHORT).show();
    }
}
