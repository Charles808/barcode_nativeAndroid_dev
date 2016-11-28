package com.example.charlessuresoft.barcodescanner_dev1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String DTAG = "MainActivity";
    private DecoratedBarcodeView barcodeView;
    private String scanText, formatText;

    /*private enum ParseResult {
        URL,
        VCARD,
        EMAIL,
        ETC
    }*/


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

            scanText = result.getText();
            //formatText = result.get
            barcodeView.setStatusText(result.getText());

            //Added preview of scanned barcode
            ImageView imageView = (ImageView) findViewById(R.id.barcodePreview);
            imageView.setImageBitmap(result.getBitmapWithResultPoints(Color.YELLOW));

            switchActivity(scanText);
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

        barcodeView = (DecoratedBarcodeView) findViewById(R.id.barcode_scanner);
        barcodeView.decodeContinuous(callback);
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

    public void scanApp(View view) {
        //barcodeView.resume();
    }

    public void exitApp(View view) {
        //barcodeView.resume();
    }

    private void switchActivity(String dataString) {

        Log.d(DTAG, "Switching Activity");
        int parseResult = parseData(dataString);

        switch (parseResult) {
            case 1:
                // URL
                break;
            case 2:
                // VCARD
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                // WEB SEARCH
                Log.d(DTAG, "Switched to : SearchActivity");
                Log.d(DTAG, "dataString : " + dataString);

                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra("DATA_STRING", dataString);
                startActivity(intent);
                break;
        }
    }

    private int parseData(String rawString) {
        return 0;
    }
}
