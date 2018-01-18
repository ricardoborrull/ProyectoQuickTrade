package com.example.a2dam.proyectoquicktrade;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a2dam.proyectoquicktrade.model.Producto;
import com.example.a2dam.proyectoquicktrade.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewProductoActivity extends AppCompatActivity {

    private Button subir;
    private EditText nombre, precio, descripcion;
    private RadioGroup categoria;
    private RadioButton tecnologia, coche, hogar;
    DatabaseReference bbdd;
    private FirebaseAuth mAuth;
    String userId, opcion;
    String sUser, sNom, sCat, sPre, sDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_producto);

        nombre = (EditText) findViewById(R.id.nombre);
        descripcion = (EditText) findViewById(R.id.desc);
        categoria = (RadioGroup) findViewById(R.id.categoria);
        tecnologia = (RadioButton) findViewById(R.id.tecnologia);
        coche = (RadioButton) findViewById(R.id.coche);
        hogar = (RadioButton) findViewById(R.id.hogar);
        precio = (EditText) findViewById(R.id.precio);
        subir = (Button) findViewById(R.id.subir);

        bbdd = (FirebaseDatabase.getInstance().getReference("producto"));

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        categoria.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.tecnologia){
                    opcion = tecnologia.getText().toString();
                    sCat = opcion;
                }else if (checkedId == R.id.coche){
                    opcion = coche.getText().toString();
                    sCat = opcion;
                }else if (checkedId == R.id.hogar){
                    opcion = hogar.getText().toString();
                    sCat = opcion;
                }

            }

        });

        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarProducto();
            }
        });

    }

    public void registrarProducto() {
        sNom = nombre.getText().toString();
        sPre = precio.getText().toString();
        sDesc = descripcion.getText().toString();
        sUser = userId;

        mAuth = FirebaseAuth.getInstance();


        if (!TextUtils.isEmpty(sNom)) {
            if (!TextUtils.isEmpty(sCat)) {
                if (!TextUtils.isEmpty(sPre)) {

                    FirebaseUser user = mAuth.getCurrentUser();
                    userId = user.getUid();
                    Producto p = new Producto(sNom, sDesc, sCat, sPre, sUser);
                    String clave = sNom+sUser;
                    bbdd.child(clave).setValue(p);


                    Toast.makeText(NewProductoActivity.this, "¡Producto subido!", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(NewProductoActivity.this, ProductosActivity.class);
                    startActivity(i);

                } else {
                    Toast.makeText(NewProductoActivity.this, "Debes introducir una contraseña", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(NewProductoActivity.this, "Debes introducir una dirección", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(NewProductoActivity.this, "Debes introducir un correo electrónico", Toast.LENGTH_SHORT).show();
        }
    }
}
