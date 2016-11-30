package com.example.charlessuresoft.barcodescanner_dev1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private static final String DTAG = "HistoryActivity";
    // Array list container declaration
    private static ArrayList<SearchResult> results = new ArrayList<SearchResult>();
    // Array list member object declaration
    private SearchResult sr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(DTAG, "Executed onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Get all array member in the list
        ArrayList<SearchResult> searchResults = GetSearchResults();
        // Direct list view to our list view in xml
        final ListView lv = (ListView) findViewById(R.id.historyList);
        // Direct adapter to custom adapter for multiple line in list view
        lv.setAdapter(new CustomBaseAdapter(this, searchResults));

        // Override function for action when one of the member being clicked
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv.getItemAtPosition(position);
                SearchResult fullObject = (SearchResult)o;
                Toast.makeText(HistoryActivity.this, "You have chosen: " + " " + fullObject.getScanResult(), Toast.LENGTH_SHORT).show();
            }
        });
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

    private void showClearDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);

        builder.setTitle("Clear History");
        builder.setMessage("Are you sure ?");
        // Add the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                results.clear();
                recreate();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        // Set other dialog properties
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void clearHistoryApp(View view) {
        if(results.size() != 0) {
            showClearDialog();
        }
    }

    // Function for adding array member to the List View
    private ArrayList<SearchResult> GetSearchResults(){
        // Return array list
        return results;
    }

    public void addToHistory(String scanTxt, String formatTxt, Long timeLong) {
        // Define new member object definition
        sr = new SearchResult();
        // Set member object values
        sr.setScanResult(scanTxt);
        sr.setFormatResult(formatTxt);
        sr.setTimeResult(timeLong.toString());
        // Add member object to array list container
        results.add(sr);
    }
}