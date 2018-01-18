package com.example.a2dam.proyectoquicktrade;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2dam.proyectoquicktrade.model.Usuario;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private Button register;
    private EditText user, nombre, apellido, correo, direccion, password;
    DatabaseReference bbdd;
    private FirebaseAuth mAuth;
    String userId;
    String sUser,sNom ,sApe,sCorr,sDir,sPassw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        register = (Button) findViewById(R.id.email_sign_in_button);
        user = (EditText) findViewById(R.id.nombre);
        nombre = (EditText) findViewById(R.id.name);
        apellido = (EditText) findViewById(R.id.apellido);
        correo = (EditText) findViewById(R.id.correo);
        direccion = (EditText) findViewById(R.id.direccion);
        password = (EditText) findViewById(R.id.password);

        bbdd = (FirebaseDatabase.getInstance().getReference("usuario"));

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUser = user.getText().toString().toLowerCase();
                Query q = bbdd.orderByChild("user").equalTo(sUser);

                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            user.setError("Ese usuario ya existe");
                            user.requestFocus();
                        } else {
                            registrarUsuario();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void registrarUsuario() {
        sUser = user.getText().toString().toLowerCase();
        sNom = nombre.getText().toString();
        sApe = apellido.getText().toString();
        sCorr = correo.getText().toString();
        sDir = direccion.getText().toString();
        sPassw = password.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        if (!TextUtils.isEmpty(sUser)){
            if (!TextUtils.isEmpty(sNom)){
                if (!TextUtils.isEmpty(sApe)){
                    if (!TextUtils.isEmpty(sCorr)){
                        if (!TextUtils.isEmpty(sDir)){
                            if (!TextUtils.isEmpty(sPassw)){

                                mAuth.createUserWithEmailAndPassword(sCorr, sPassw)
                                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    // Sign in success, update UI with the signed-in user's information
                                                    FirebaseUser user = mAuth.getCurrentUser();
                                                    Toast.makeText(RegisterActivity.this, "Authentication successfull." + user.getUid(),
                                                            Toast.LENGTH_SHORT).show();
                                                    userId = user.getUid();
                                                    Usuario u = new Usuario(sUser, sNom, sApe, sCorr, sDir, sPassw);
                                                    Toast.makeText(RegisterActivity.this, userId , Toast.LENGTH_SHORT).show();
                                                    bbdd.child(userId).setValue(u);

                                                    mAuth.signOut();

                                                    Toast.makeText(RegisterActivity.this, "¡Registro completado!", Toast.LENGTH_SHORT).show();

                                                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                                    startActivity(i);
                                                } else {
                                                    FirebaseUser user = mAuth.getCurrentUser();
                                                    // If sign in fails, display a message to the user.
                                                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                                            Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
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
}
