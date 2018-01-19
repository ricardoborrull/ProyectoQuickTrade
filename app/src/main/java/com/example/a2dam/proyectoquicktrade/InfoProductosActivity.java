package com.example.a2dam.proyectoquicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.a2dam.proyectoquicktrade.model.Producto;
import com.example.a2dam.proyectoquicktrade.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoProductosActivity extends AppCompatActivity {

    private ToggleButton modify;
    private TextView cat;
    private Button borrar;
    private EditText nombre, descripcion, precio;
    private RadioGroup categoria;
    private RadioButton tecnologia, hogar, coche;
    DatabaseReference bbdd;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private String id, key, opcion, sNombre, sCat, sDesc, sPre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_productos);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        key = getIntent().getStringExtra("key");
        id = getIntent().getStringExtra("id");

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        bbdd = (FirebaseDatabase.getInstance().getReference("producto").child(key));

        modify = (ToggleButton) findViewById(R.id.modify);
        cat = (TextView) findViewById(R.id.cat);
        borrar = (Button) findViewById(R.id.delete);
        nombre = (EditText) findViewById(R.id.nombre);
        descripcion = (EditText) findViewById(R.id.desc);
        precio = (EditText) findViewById(R.id.precio);
        tecnologia = (RadioButton) findViewById(R.id.tecnologia);
        coche = (RadioButton) findViewById(R.id.coche);
        hogar = (RadioButton) findViewById(R.id.hogar);
        categoria = (RadioGroup) findViewById(R.id.categoria);

        nombre.setEnabled(false);
        descripcion.setEnabled(false);
        precio.setEnabled(false);
        tecnologia.setVisibility(View.INVISIBLE);
        coche.setVisibility(View.INVISIBLE);
        hogar.setVisibility(View.INVISIBLE);
        tecnologia.setEnabled(false);
        coche.setEnabled(false);
        hogar.setEnabled(false);

        if (id.equals(mAuth.getCurrentUser().getUid())) {
            borrar.setVisibility(View.VISIBLE);
            modify.setVisibility(View.VISIBLE);
        } else {
            borrar.setVisibility(View.INVISIBLE);
            modify.setVisibility(View.INVISIBLE);
        }

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InfoProductosActivity.this, "¡Producto borrado!", Toast.LENGTH_SHORT).show();
                bbdd.removeValue();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

            bbdd.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    bindInfoProducto(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



            categoria.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.tecnologia) {
                        opcion = tecnologia.getText().toString();
                        sCat = opcion;
                    } else if (checkedId == R.id.coche) {
                        opcion = coche.getText().toString();
                        sCat = opcion;
                    } else if (checkedId == R.id.hogar) {
                        opcion = hogar.getText().toString();
                        sCat = opcion;
                    }

                }

            });

            modify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {

                        nombre.setEnabled(true);
                        precio.setEnabled(true);
                        descripcion.setEnabled(true);
                        precio.setEnabled(true);
                        tecnologia.setEnabled(true);
                        coche.setEnabled(true);
                        hogar.setEnabled(true);
                        tecnologia.setChecked(true);
                        tecnologia.setVisibility(View.VISIBLE);
                        coche.setVisibility(View.VISIBLE);
                        hogar.setVisibility(View.VISIBLE);

                    } else {

                        Toast.makeText(InfoProductosActivity.this, "¡Cambios guardados!", Toast.LENGTH_SHORT).show();
                        nombre.setEnabled(false);
                        precio.setEnabled(false);
                        descripcion.setEnabled(false);
                        precio.setEnabled(false);
                        tecnologia.setVisibility(View.INVISIBLE);
                        coche.setVisibility(View.INVISIBLE);
                        hogar.setVisibility(View.INVISIBLE);

                        sNombre = nombre.getText().toString();
                        sPre = precio.getText().toString();
                        sDesc = descripcion.getText().toString();

                        bbdd.child(getString(R.string.campo_nombre)).setValue(sNombre);
                        bbdd.child(getString(R.string.campo_categoria)).setValue(sCat);
                        bbdd.child(getString(R.string.campo_precio)).setValue(sPre);
                        bbdd.child(getString(R.string.campo_descripcion)).setValue(sDesc);

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                }
            });
        }

    //Aquí se rellena la información del usuario
    private void bindInfoProducto(DataSnapshot dataSnapshot) {
        Producto p = dataSnapshot.getValue(Producto.class);
        nombre.setText(p.getNombre().toUpperCase());
        descripcion.setText(p.getDescripcion());
        precio.setText(p.getPrecio());
        cat.setText(p.getCategoria());
    }
}
