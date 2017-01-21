package com.example.cornhedgehog.poole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final Button button = (Button) findViewById(R.id.searchBtn);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onSearchBtnClicked(View v) {
//                // Perform action on click
//            }
//        });
    }

    public void onSearchButtonClicked(View view) {
        final Button button = (Button) findViewById(R.id.searchBtn);
        final EditText urlField = (EditText) findViewById(R.id.urlText);
        //button.setText("done");
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra("Url", urlField.getText().toString());
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
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
