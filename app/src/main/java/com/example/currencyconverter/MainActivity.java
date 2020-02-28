package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    private Button btnViaCamera, btnManually, btnNewCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        updateInfo();

        btnViaCamera = (Button) findViewById(R.id.btnViaCamera);
        btnManually = (Button) findViewById(R.id.btnManually);
        btnNewCurrency = (Button) findViewById(R.id.btnNewCurrency);

        btnViaCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCameraScreen();
            }
        });
        btnManually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManualCreen();
            }
        });
        btnNewCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCurrencyScreen();
            }
        });
    }

    public void openCameraScreen(){
        Intent intent = new Intent(this, CameraScreen.class);
        startActivity(intent);
    }

    public void openManualCreen(){
        Intent intent = new Intent(this, ManualScreen.class);
        startActivity(intent);
    }

    public void openAddCurrencyScreen(){
        Intent intent = new Intent(this, AddCurrencyScreen.class);
        startActivity(intent);
    }

    public void updateInfo(){
        db.insert("USD", "US Dollar",       "US",               "$", "1");
        db.insert("JPY", "Japanese Yen",    "Japan",            "¥", "2");
        db.insert("GBP", "Pound Sterling",  "United Kingdom",   "£", "3");
        db.insert("TRY", "Turkish Lira",    "Turkey",           "TL", "4");
    }
}
