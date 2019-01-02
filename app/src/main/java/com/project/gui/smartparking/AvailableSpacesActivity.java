package com.project.gui.smartparking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AvailableSpacesActivity extends Activity {


    ImageButton carImgButton1;
    ImageButton carImgButton2;
    ImageButton carImgButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_spaces);


        carImgButton1 = findViewById(R.id.carImgButton1);
        carImgButton2 = findViewById(R.id.carImgButton2);
        carImgButton3 = findViewById(R.id.carImgButton3);

        carImgButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AvailableSpacesActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });






    }
}
