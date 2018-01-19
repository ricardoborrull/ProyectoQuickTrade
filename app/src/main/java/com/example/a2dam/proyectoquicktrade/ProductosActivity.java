package com.example.a2dam.proyectoquicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a2dam.proyectoquicktrade.model.ProductAdapter;
import com.example.a2dam.proyectoquicktrade.model.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity{

    private Button newP;
    DatabaseReference bbdd;
    private RecyclerView recycler;
    ProductAdapter adaptador;
    private Spinner spinner;
    ArrayAdapter<String> comboAdapter;
    String filtros[] = {"Ver todo", "Mis productos", "Por Categoría", "Por Usuario"};
    private ArrayList<Producto> listado = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        newP = (Button) findViewById(R.id.newP);
        spinner = (Spinner) findViewById(R.id.spinner);

        newP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductosActivity.this, NewProductoActivity.class);
                startActivity(i);
            }
        });

        recycler = (RecyclerView) findViewById(R.id.recycler);
        bbdd = FirebaseDatabase.getInstance().getReference().child("producto");

        comboAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, filtros);
        //Cargo el spinner con los datos
        spinner.setAdapter(comboAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                    Producto p = datasnapshot.getValue(Producto.class);
                    listado.add(p);
                }
                adaptador = new ProductAdapter(listado);
                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(getApplicationContext(), InfoProductosActivity.class);
                        //Cogemos la posición, elegimos la key de esta y la mandamos a info activity
                        i.putExtra("key",listado.get(recycler.getChildAdapterPosition(v)).getKey().toString());
                        i.putExtra("id",listado.get(recycler.getChildAdapterPosition(v)).getUser()).toString();
                        startActivity(i);

                    }
                });
                recycler.setAdapter(adaptador);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

}
