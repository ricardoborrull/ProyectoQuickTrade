package com.example.a2dam.proyectoquicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2dam.proyectoquicktrade.model.Usuario;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private Button register;
    private EditText user, nombre, apellido, correo, direccion, password;
    DatabaseReference bbdd;
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = (Button) findViewById(R.id.email_sign_in_button);
        user = (EditText) findViewById(R.id.user);
        nombre = (EditText) findViewById(R.id.name);
        apellido = (EditText) findViewById(R.id.apellido);
        correo = (EditText) findViewById(R.id.correo);
        direccion = (EditText) findViewById(R.id.direccion);
        password = (EditText) findViewById(R.id.password);

        bbdd = (FirebaseDatabase.getInstance().getReference("usuario"));


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUser = user.getText().toString();
                String sNom = nombre.getText().toString();
                String sApe = apellido.getText().toString();
                String sCorr = correo.getText().toString();
                String sDir = direccion.getText().toString();
                String sPassw = password.getText().toString();

                if (!TextUtils.isEmpty(sUser)){
                    if (!TextUtils.isEmpty(sNom)){
                        if (!TextUtils.isEmpty(sApe)){
                            if (!TextUtils.isEmpty(sCorr)){
                                if (!TextUtils.isEmpty(sDir)){
                                    if (!TextUtils.isEmpty(sPassw)){


                                        String clave = sUser;
                                        Usuario u = new Usuario(sUser, sNom, sApe, sCorr, sDir, sPassw);

                                        bbdd.child(clave).setValue(u);

                                        Toast.makeText(RegisterActivity.this, "¡Registro completado!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Debes introducir una contraseña", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Debes introducir una dirección", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "Debes introducir un correo electrónico", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Debes introducir un apellido", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Debes introducir un nombre", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Debes introducir un nombre de usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
