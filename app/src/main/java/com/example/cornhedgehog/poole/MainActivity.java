package com.example.cornhedgehog.poole;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSearchButtonClicked(View view) throws Exception {
        final EditText urlField = (EditText) findViewById(R.id.urlText);
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        String urlFromEditText = urlField.getText().toString();
        if (!urlFromEditText.contains("www")) {
            String www = "www.";
            urlFromEditText = www.concat(urlFromEditText);
        }
        if (!urlFromEditText.contains("http://")) {
            String http = "http://";
            urlFromEditText = http.concat(urlFromEditText);
        }
        intent.putExtra("Url", urlFromEditText);
        startActivity(intent);
    }
    //TODO manage the exception

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handles item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                Log.i("MainActivity", "Menu item is not implemented.");
                return true;
        }
    }
}
