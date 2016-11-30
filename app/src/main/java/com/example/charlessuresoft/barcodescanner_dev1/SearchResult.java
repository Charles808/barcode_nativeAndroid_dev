package com.example.charlessuresoft.barcodescanner_dev1;

/**
 * Created by Charles Suresoft on 30/11/2016.
 */

public class SearchResult {
    private String scanResult = "";
    private String formatResult = "";
    private String timeResult = "";

    public void setScanResult(String scanResult) {
        this.scanResult = scanResult;
    }

    public String getScanResult() {
        return scanResult;
    }

    public void setFormatResult(String formatResult) {
        this.formatResult = formatResult;
    }

    public String getFormatResult() {
        return formatResult;
    }
    public void setTimeResult(String timeResult) {
        this.timeResult = timeResult;
    }

    public String getTimeResult() {
        return timeResult;
    }
}
