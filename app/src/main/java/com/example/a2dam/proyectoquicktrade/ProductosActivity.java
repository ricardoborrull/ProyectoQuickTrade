package com.example.a2dam.proyectoquicktrade;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a2dam.proyectoquicktrade.model.ProductAdapter;
import com.example.a2dam.proyectoquicktrade.model.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity{

    private Button newP;
    DatabaseReference bbdd;
    private RecyclerView recycler;
    ProductAdapter adaptador;
    private Spinner spinner;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    ArrayAdapter<String> comboAdapter;
    private String seleccion;
    String filtros[] = {"Ver todo", "Mis productos", "Por Categoría: Tecnología","Por Categoría: Hogar","Por Categoría: Coche", "Por Usuario"};
    String categorias[] = {"Tecnología", "Hogar", "Coche"};
    private ArrayList<Producto> listado = new ArrayList<>();
    private ArrayList<Producto> listadofiltrado = new ArrayList<>();



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
                switch (position) {
                    case 0:
                        bbdd.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                listado.clear();
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
                        recycler.setLayoutManager(new LinearLayoutManager(ProductosActivity.this, LinearLayoutManager.VERTICAL, false));
                        break;
                    case 1:
                        mAuth = FirebaseAuth.getInstance();
                        Query uno = bbdd.orderByChild(getString(R.string.campo_user)).equalTo(mAuth.getCurrentUser().getUid());
                        uno.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                listado.clear();
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
                        recycler.setLayoutManager(new LinearLayoutManager(ProductosActivity.this, LinearLayoutManager.VERTICAL, false));
                        break;
                    case 2:
                        Query dos = bbdd.orderByChild(getString(R.string.campo_categoria)).equalTo("Tecnología");
                        dos.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                listado.clear();
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
                        recycler.setLayoutManager(new LinearLayoutManager(ProductosActivity.this, LinearLayoutManager.VERTICAL, false));
                        break;
                    case 3:
                        Query tres = bbdd.orderByChild(getString(R.string.campo_categoria)).equalTo("Hogar");
                        tres.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                listado.clear();
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
                        recycler.setLayoutManager(new LinearLayoutManager(ProductosActivity.this, LinearLayoutManager.VERTICAL, false));

                        break;
                    case 4:
                        Query cuatro = bbdd.orderByChild(getString(R.string.campo_categoria)).equalTo("Coche");
                        cuatro.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                listado.clear();
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
                        recycler.setLayoutManager(new LinearLayoutManager(ProductosActivity.this, LinearLayoutManager.VERTICAL, false));
                        break;
                    case 5:
                        BuscaID();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void BuscaID() {
        AlertDialog.Builder alert = new AlertDialog.Builder(ProductosActivity.this);
        final EditText edittext = new EditText(ProductosActivity.this);
        alert.setMessage("Introduce la ID del Usuario");
        alert.setTitle("Por Usuario:");

        alert.setView(edittext);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String text = edittext.getText().toString();
                Query cinco = bbdd.orderByChild(getString(R.string.campo_user)).equalTo(text);
                cinco.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        listado.clear();
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
                recycler.setLayoutManager(new LinearLayoutManager(ProductosActivity.this, LinearLayoutManager.VERTICAL, false));
            }
        });

        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();
    }

}
