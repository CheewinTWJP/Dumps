package edu.qc.seclass.storesupplyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        // Find the inventory buttons
        Button inventoryButton1 = findViewById(R.id.inventoryButton1);
        Button inventoryButton2 = findViewById(R.id.inventoryButton2);
        Button inventoryButton3 = findViewById(R.id.inventoryButton3);
        Button inventoryButton4 = findViewById(R.id.inventoryButton4);

        // Set an OnClickListener for each inventory button
        inventoryButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        inventoryButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        inventoryButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        inventoryButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
