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

    ArrayList<SearchResult> searchResults = GetSearchResults();

    final ListView lv = (ListView) findViewById(R.id.historyList);
    lv.setAdapter(new CustomBaseAdapter(this, searchResults));


    lv.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            Object o = lv.getItemAtPosition(position);
            SearchResult fullObject = (SearchResult)o;
            Toast.makeText(HistoryActivity.this, "You have chosen: " + " " + fullObject.getScanResult(), Toast.LENGTH_SHORT).show();
        }
    });
    }

    private ArrayList<SearchResult> GetSearchResults(){ArrayList<SearchResult> results = new ArrayList<SearchResult>();

    SearchResult sr;

    sr = new SearchResult();
    sr.setScanResult("Justin Schultz");
    sr.setFormatResult("San Francisco, CA");
    sr.setTimeResult("415-555-1234");
    results.add(sr);

    sr = new SearchResult();
    sr.setScanResult("Jane Doe");
    sr.setFormatResult("Las Vegas, NV");
    sr.setTimeResult("702-555-1234");
    results.add(sr);

    sr = new SearchResult();
    sr.setScanResult("Lauren Sherman");
    sr.setFormatResult("San Francisco, CA");
    sr.setTimeResult("415-555-1234");
    results.add(sr);

    sr = new SearchResult();
    sr.setScanResult("Fred Jones");
    sr.setFormatResult("Minneapolis, MN");
    sr.setTimeResult("612-555-8214");
    results.add(sr);

    sr = new SearchResult();
    sr.setScanResult("Bill Withers");
    sr.setFormatResult("Los Angeles, CA");
    sr.setTimeResult("424-555-8214");
    results.add(sr);

    sr = new SearchResult();
    sr.setScanResult("Justin Schultz");
    sr.setFormatResult("San Francisco, CA");
    sr.setTimeResult("415-555-1234");
    results.add(sr);

    sr = new SearchResult();
    sr.setScanResult("Jane Doe");
    sr.setFormatResult("Las Vegas, NV");
    sr.setTimeResult("702-555-1234");
    results.add(sr);

    sr = new SearchResult();
    sr.setScanResult("Lauren Sherman");
    sr.setFormatResult("San Francisco, CA");
    sr.setTimeResult("415-555-1234");
    results.add(sr);

    sr = new SearchResult();
    sr.setScanResult("Fred Jones");
    sr.setFormatResult("Minneapolis, MN");
    sr.setTimeResult("612-555-8214");
    results.add(sr);

    sr = new SearchResult();
    sr.setScanResult("Bill Withers");
    sr.setFormatResult("Los Angeles, CA");
    sr.setTimeResult("424-555-8214");
    results.add(sr);
    /*
     sr = new SearchResults();
     sr.setName("Donald Fagen");
     sr.setCityState("Los Angeles, CA");
     sr.setPhone("424-555-1234");
     results.add(sr);
      
     sr = new SearchResults();
     sr.setName("Steve Rude");
     sr.setCityState("Oakland, CA");
     sr.setPhone("515-555-2222");
     results.add(sr);
      
     sr = new SearchResults();
     sr.setName("Roland Bloom");
     sr.setCityState("Chelmsford, MA");
     sr.setPhone("978-555-1111");
     results.add(sr);
 
     sr = new SearchResults();
     sr.setName("Sandy Baguskas");
     sr.setCityState("Chelmsford, MA");
     sr.setPhone("978-555-2222");
     results.add(sr);
      
     sr = new SearchResults();
     sr.setName("Scott Taylor");
     sr.setCityState("Austin, TX");
     sr.setPhone("512-555-2222");
     results.add(sr);
    */
    return results;
    }
}