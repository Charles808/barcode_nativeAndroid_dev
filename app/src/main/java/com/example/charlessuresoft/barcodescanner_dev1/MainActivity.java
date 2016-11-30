package com.example.charlessuresoft.barcodescanner_dev1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        DecoratedBarcodeView.TorchListener {

    private static final String
            DTAG = "MainActivity",
            URL_ACT = "UrlAct",
            VCARD_ACT = "VcardAct",
            SEARCH_ACT = "SearchAct",
            HISTORY_ACT = "HistoryAct";

    private static final int CAMERA_PERMISSION = 88;

    private DecoratedBarcodeView barcodeView;
    private HistoryActivity ha;
    private Intent intent;

    private String toast = null;
    private String
            scanText = null,
            formatText = null,
            timeText = null;

    private long timeRaw = 0;

    private boolean
            hasFlash = false,
            flashOn = false;

    private Button flashBtn;

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {

            Log.d(DTAG, "Barcode Detected");

            /*
            if(result.getText() == null || result.getText().equals(scanText)) {
                // Prevent duplicate scans
                return;
            }
            */

            // To avoid duplicate entry to array list
            if(!(result.getText() == null || result.getText().equals(scanText))) {
                Log.d(DTAG, "Add new data to history");
                ha.addToHistory(result.getText(), result.getBarcodeFormat().toString(), result.getTimestamp());
            }

            scanText = result.getText();
            formatText = result.getBarcodeFormat().toString();
            timeRaw = result.getTimestamp();
            barcodeView.setStatusText(result.getText());

            //Added preview of scanned barcode
            ImageView imageView = (ImageView) findViewById(R.id.barcodePreview);
            imageView.setImageBitmap(result.getBitmapWithResultPoints(Color.YELLOW));

            switchActivity();
            //barcodeView.pause();
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        barcodeView = (DecoratedBarcodeView) findViewById(R.id.barcode_scanner);
        barcodeView.decodeContinuous(callback);

        barcodeView.setTorchListener(this);
        flashBtn = (Button)findViewById(R.id.flashSwitch);

        hasFlash = checkFlash();

        ha = new HistoryActivity();
    }
    @Override
    protected void onResume() {
        super.onResume();

        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        barcodeView.pause();
    }

    private void checkPermission() {
        Log.d(DTAG, "User Device API Level : " + android.os.Build.VERSION.SDK_INT);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // Do something for lollipop and above versions
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted.
                    toast = "Permission Granted";
                    displayToast();
                }
                else {
                    // permission denied.
                    toast = "You CAN'T used the app without PERMISSION";
                    displayToast();
                }
                return;
            }
        // other 'case' lines to check for other permissions this app might request
        }
    }

    private void displayToast() {
        if(getApplicationContext() != null && toast != null) {
            Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
            toast = null;
        }
    }

    private boolean checkFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public void flashApp(View view) {
        //barcodeView.resume();
        if(hasFlash) {
            if (!flashOn) {
                barcodeView.setTorchOn();
                flashOn = true;
            } else {
                barcodeView.setTorchOff();
                flashOn = false;
            }
        }
        else {
            toast = "Flash Unavailable";
            displayToast();
        }
    }

    public void historyApp(View view) {
        // go to HistoryActivity
        gotoActivity(intent, HISTORY_ACT);
    }

    @Override
    public void onTorchOn() {
        flashBtn.setText("Flash On");
    }

    @Override
    public void onTorchOff() {
        flashBtn.setText("Flash Off");
    }

    private void switchActivity() {

        Log.d(DTAG, "Switching Activity");
        Log.d(DTAG, "dataString : " + scanText);
        Log.d(DTAG, "formatString : " + formatText);
        Log.d(DTAG, "timestamp : " + timeRaw);

        int parseResult = parseData(scanText);

        switch (parseResult) {
            case 1:
                // URL
                gotoActivity(intent, URL_ACT);
                break;

            case 2:
                // VCARD
                gotoActivity(intent, VCARD_ACT);
                break;

            case 3:
                // later...
                break;

            default:
                // WEB SEARCH
                gotoActivity(intent, SEARCH_ACT);
                break;
        }
    }

    private void gotoActivity(Intent intent, String activity) {

        Log.d(DTAG, "Switched to : " + activity);

        if(activity.contentEquals(URL_ACT)) {
            intent = new Intent(this, UrlActivity.class);
        }

        else if (activity.contentEquals(VCARD_ACT)) {
            intent = new Intent(this, VcardActivity.class);
        }

        else if (activity.contentEquals(HISTORY_ACT)) {
            intent = new Intent(this, HistoryActivity.class);
        }

        else
        {
            intent = new Intent(this, SearchActivity.class);
        }

        intent.putExtra("DATA_STRING", scanText);
        intent.putExtra("FORMAT_STRING", formatText);
        intent.putExtra("TIME_STRING", timeText);

        startActivity(intent);
    }

    private int parseData(String rawString) {

        Log.d(DTAG, rawString);

        if(rawString.startsWith("https://",0) || rawString.startsWith("http://",0))
            return 1;
        else if(rawString.startsWith("BEGIN:VCARD", 0) && rawString.contains("END:VCARD"))
            return 2;
        else
            return 0;
    }
}
