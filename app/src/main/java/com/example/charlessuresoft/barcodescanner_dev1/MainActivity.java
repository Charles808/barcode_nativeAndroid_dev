package com.example.charlessuresoft.barcodescanner_dev1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.BarcodeView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button scanBtn;
    private TextView formatTxt, contentTxt;
   // private static final int PERMISSIONS_REQUEST_CAMERA = 88;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);

        scanBtn.setOnClickListener(this);

        //getPermissionCamera();
    }

    public void onClick(View v){
        // Scan button action
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            //scanIntegrator.setOrientationLocked(false);
            scanIntegrator.setBarcodeImageEnabled(true);
            scanIntegrator.initiateScan();
        }
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // Retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
        // A result received
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            String scanImagePath = intent.getStringExtra(Intents.Scan.RESULT_BARCODE_IMAGE_PATH);
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    /* Using new library of ZXing 3.4.0 this won't be needed anymore....
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // permission was granted, Make SCAN flag to true.
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Permission Granted", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    // permission denied. Disable the functionality that depends on this permission.
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Permission Denied", Toast.LENGTH_SHORT);
                    toast.show();
                    ActivityCompat.finishAffinity(this);
                }
                return;
            }
            // other 'case' lines to check for other future permissions this app might request
        }
    }

    private void getPermissionCamera(){
        // check if permission already granted or not
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSIONS_REQUEST_CAMERA);

            // PERMISSIONS_REQUEST_CAMERA is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }
    */
    // Experimental Function !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /*
    private void checkFlag() {
        if(flag) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "True", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "False", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    */
    // Experimental Function !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
}
