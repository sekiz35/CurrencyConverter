package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCurrencyScreen extends AppCompatActivity {

    DatabaseHelper db;
    TextView saved0, saved1, saved2, saved3, newCurrencyCode, newCurrencyName, newCountryName, newSymbol;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_currency_screen);

        db = new DatabaseHelper(this);

        btnAdd          = findViewById(R.id.btnAdd);
        saved0          = findViewById(R.id.txtSaved0);
        saved1          = findViewById(R.id.txtSaved1);
        saved2          = findViewById(R.id.txtSaved2);
        saved3          = findViewById(R.id.txtSaved3);
        newCurrencyCode = findViewById(R.id.txtCurrencyCode);
        newCurrencyName = findViewById(R.id.txtCurrencyName);
        newCountryName  = findViewById(R.id.txtCountryName);
        newSymbol       = findViewById(R.id.txtSymbol);

        updateList();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    public void add(){
        String newCurrencyCode_, newCurrencyName_, newCountryName_, newSymbol_;
        newCurrencyCode_ = newCurrencyCode.getText().toString();
        newCurrencyName_ = newCurrencyName.getText().toString();
        newCountryName_  = newCountryName.getText().toString();
        newSymbol_       = newSymbol.getText().toString();
        boolean bool     = db.insert(newCurrencyCode_.toUpperCase(), newCurrencyName_, newCountryName_, newSymbol_);
        if(newCurrencyCode_.length()!=3)
            Toast.makeText(this, "Currency Code must be 3 letters", Toast.LENGTH_SHORT).show();
        else if(!bool)
            Toast.makeText(this, "Currency already exists", Toast.LENGTH_SHORT).show();
        else{
            updateList();
            newCurrencyCode.setText("");
            newCurrencyName.setText("");
            newCountryName.setText("");
            newSymbol.setText("");
        }
    }

    public void updateList(){
        saved0.setText(db.showList("lineNumber"));
        saved1.setText(db.showList("currencyCode"));
        saved2.setText(db.showList("currencyName"));
        saved3.setText(db.showList("countryName"));
    }
}
