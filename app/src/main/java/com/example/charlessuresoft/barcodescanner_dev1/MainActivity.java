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
    private Button flashBtn;

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
            formatText = result.getBarcodeFormat().toString();
            barcodeView.setStatusText(result.getText());

            //Added preview of scanned barcode
            ImageView imageView = (ImageView) findViewById(R.id.barcodePreview);
            imageView.setImageBitmap(result.getBitmapWithResultPoints(Color.YELLOW));

            switchActivity(scanText, formatText);
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

        flashBtn = (Button)findViewById(R.id.flashSwitch);
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

    public void flashApp(View view) {
        //barcodeView.resume();
    }

    private void switchActivity(String dataString, String formatString) {

        Log.d(DTAG, "Switching Activity");
        Log.d(DTAG, "dataString : " + dataString);
        Log.d(DTAG, "formatString : " + formatString);

        Intent intent;

        int parseResult = parseData(dataString);

        switch (parseResult) {
            case 1:
                // URL
                Log.d(DTAG, "Switched to : UrlActivity");

                intent = new Intent(this, UrlActivity.class);
                intent.putExtra("DATA_STRING", dataString);

                startActivity(intent);
                break;

            case 2:
                // VCARD
                Log.d(DTAG, "Switched to : VcardActivity");

                intent = new Intent(this, VcardActivity.class);
                intent.putExtra("DATA_STRING", dataString);

                startActivity(intent);
                break;

            case 3:
                // later...
                break;

            default:
                // WEB SEARCH
                Log.d(DTAG, "Switched to : SearchActivity");

                intent = new Intent(this, SearchActivity.class);
                intent.putExtra("DATA_STRING", dataString);
                intent.putExtra("FORMAT_STRING", formatString);

                startActivity(intent);
                break;
        }
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
