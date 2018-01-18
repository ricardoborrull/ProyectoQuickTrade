package com.example.a2dam.proyectoquicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.a2dam.proyectoquicktrade.model.ProductAdapter;
import com.example.a2dam.proyectoquicktrade.model.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity {

    private Button newP;
    DatabaseReference bbdd;
    private RecyclerView recycler;
    private RecyclerView.Adapter adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        newP = (Button) findViewById(R.id.newP);

        newP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductosActivity.this, NewProductoActivity.class);
                startActivity(i);
            }
        });


        recycler = (RecyclerView) findViewById(R.id.recycler);
        bbdd = FirebaseDatabase.getInstance().getReference("producto");

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProductAdapter adaptador;
                ArrayList<Producto> listado = new ArrayList<>();

                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                    Producto p = datasnapshot.getValue(Producto.class);
                    listado.add(p);
                }
                adaptador = new ProductAdapter(listado);
                recycler.setAdapter(adaptador);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}
