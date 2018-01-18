package com.example.a2dam.proyectoquicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
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
    private EditText nombre, descripcion, precio;
    private RadioButton tecnologia, hogar, coche;
    DatabaseReference bbdd;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_productos);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        bbdd = (FirebaseDatabase.getInstance().getReference("usuario").child(firebaseUser.getUid()));

        modify = (ToggleButton) findViewById(R.id.modify);
        nombre = (EditText) findViewById(R.id.nombre);
        descripcion = (EditText) findViewById(R.id.desc);
        precio = (EditText) findViewById(R.id.precio);
        tecnologia = (RadioButton) findViewById(R.id.tecnologia);
        coche = (RadioButton) findViewById(R.id.coche);
        hogar = (RadioButton) findViewById(R.id.hogar);

        nombre.setEnabled(false);
        descripcion.setEnabled(false);
        precio.setEnabled(false);
        tecnologia.setEnabled(false);
        coche.setEnabled(false);
        hogar.setEnabled(false);


        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bindInfoProducto(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*modify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    nombre.setEnabled(true);

                } else {

                    nombre.setEnabled(false);

                    String sNombre = nombre.getText().toString();

                    bbdd.child(getString(R.string.campo_nombre)).setValue(sNombre);
                }
            }
        });*/
    }

    //Aquí se rellena la información del usuario
    private void bindInfoProducto(DataSnapshot dataSnapshot) {
        Producto p = dataSnapshot.getValue(Producto.class);
        nombre.setText(p.getNombre().toUpperCase());
        descripcion.setText(p.getDescripcion());
        precio.setText(p.getPrecio());
    }
}
