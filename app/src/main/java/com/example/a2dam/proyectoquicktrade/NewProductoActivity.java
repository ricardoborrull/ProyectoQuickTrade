package com.example.a2dam.proyectoquicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_producto);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

    }
}
