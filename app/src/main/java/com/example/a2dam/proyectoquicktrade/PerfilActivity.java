package com.example.a2dam.proyectoquicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.example.a2dam.proyectoquicktrade.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilActivity extends AppCompatActivity {

    private ToggleButton modify;
    private EditText user, nombre, apellido, correo, direccion;
    DatabaseReference bbdd;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        bbdd = (FirebaseDatabase.getInstance().getReference("usuario").child(firebaseUser.getUid()));

        modify = (ToggleButton) findViewById(R.id.modify);
        user = (EditText) findViewById(R.id.nombre);
        nombre = (EditText) findViewById(R.id.name);
        apellido = (EditText) findViewById(R.id.apellido);
        correo = (EditText) findViewById(R.id.correo);
        direccion = (EditText) findViewById(R.id.direccion);


        user.setEnabled(false);
        correo.setEnabled(false);
        nombre.setEnabled(false);
        apellido.setEnabled(false);
        direccion.setEnabled(false);


        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bindInfoUsuario(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        modify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    nombre.setEnabled(true);
                    apellido.setEnabled(true);
                    direccion.setEnabled(true);

                } else {

                    nombre.setEnabled(false);
                    apellido.setEnabled(false);
                    direccion.setEnabled(false);

                    String sNombre = nombre.getText().toString();
                    String sApellidos = apellido.getText().toString();
                    String sDireccion = direccion.getText().toString();

                    bbdd.child(getString(R.string.campo_nombre)).setValue(sNombre);
                    bbdd.child(getString(R.string.campo_apellidos)).setValue(sApellidos);
                    bbdd.child(getString(R.string.campo_direccion)).setValue(sDireccion);
                }
            }
        });
    }

        //Aquí se rellena la información del usuario
        private void bindInfoUsuario(DataSnapshot dataSnapshot) {
            Usuario unUsuario = dataSnapshot.getValue(Usuario.class);
            user.setText(unUsuario.getUser().toUpperCase());
            nombre.setText(unUsuario.getNombre());
            apellido.setText(unUsuario.getApellidos());
            correo.setText(firebaseUser.getEmail());
            direccion.setText(unUsuario.getDireccion());
    }
}
