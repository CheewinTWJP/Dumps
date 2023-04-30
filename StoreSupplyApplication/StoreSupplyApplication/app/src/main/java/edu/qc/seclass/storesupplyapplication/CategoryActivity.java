package edu.qc.seclass.storesupplyapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        CardView woodCardView = findViewById(R.id.CardViewLaminate);
        woodCardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(CategoryActivity.this, SearchActivity.class));
            }
        });
        CardView TileCardView = findViewById(R.id.CardViewTile);
        TileCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryActivity.this, SearchActivity.class));
            }
        });
        CardView StoneCardView = findViewById(R.id.CardViewStone);
        StoneCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryActivity.this, SearchActivity.class));
            }});
        CardView VinylCardView = findViewById(R.id.CardViewVinyl);
        VinylCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryActivity.this, SearchActivity.class));
            }});
        CardView WoodCardView = findViewById(R.id.inventory_button);
        WoodCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryActivity.this, SearchActivity.class));
            }});

    }
}
