package com.example.a2dam.proyectoquicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class ProductosActivity extends AppCompatActivity {

    private Button newP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        //newP = (Button) findViewById(R.id.newP);
    }
}
