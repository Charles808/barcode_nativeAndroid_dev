package com.example.charlessuresoft.barcodescanner_dev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
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

    // Function for adding array member to the List View
    private ArrayList<SearchResult> GetSearchResults(){

        // Array list container declaration
        ArrayList<SearchResult> results = new ArrayList<SearchResult>();
        // Array list member object declaration
        SearchResult sr;

        // Define new member object definition
        sr = new SearchResult();
        // Set member object values
        sr.setScanResult("Justin Schultz");
        sr.setFormatResult("San Francisco, CA");
        sr.setTimeResult("415-555-1234");
        // Add member object to array list container
        results.add(sr);

        sr = new SearchResult();
        sr.setScanResult("Jane Doe");
        sr.setFormatResult("Las Vegas, NV");
        sr.setTimeResult("702-555-1234");
        results.add(sr);

        // Return array list
        return results;
    }
}