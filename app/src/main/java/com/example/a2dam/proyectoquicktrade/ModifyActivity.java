package com.example.a2dam.proyectoquicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2dam.proyectoquicktrade.R;
import com.example.a2dam.proyectoquicktrade.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ModifyActivity extends AppCompatActivity {

    private Button modify;
    private EditText user, nombre, apellido, correo, direccion, password;
    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        modify = (Button) findViewById(R.id.modify);
        user = (EditText) findViewById(R.id.user);
        nombre = (EditText) findViewById(R.id.name);
        apellido = (EditText) findViewById(R.id.apellido);
        correo = (EditText) findViewById(R.id.correo);
        direccion = (EditText) findViewById(R.id.direccion);
        password = (EditText) findViewById(R.id.password);

        bbdd = (FirebaseDatabase.getInstance().getReference("usuario"));

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String usuario = user.getText().toString();

                if (!TextUtils.isEmpty(usuario)){
                    //Hacemos una query que compare si los campos son iguales
                    Query q = bbdd.orderByChild(getString(R.string.campo_user)).equalTo(usuario);
                    Log.e("mitag", q.toString());

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                //Encontramos la "key" en la bbdd "Ej: L0jnF6jj9lhbfSf48Hl" para coger sus datos
                                String clave = datasnapshot.getKey();
                                bbdd.child(clave).child(getString(R.string.campo_nombre)).setValue(nombre.getText().toString());

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    }); Toast.makeText(ModifyActivity.this, "Se ha modificado con éxito", Toast.LENGTH_SHORT).show();
                } Toast.makeText(ModifyActivity.this, "Debes introducir un usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
