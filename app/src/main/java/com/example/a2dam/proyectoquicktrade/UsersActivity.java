package com.example.a2dam.proyectoquicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a2dam.proyectoquicktrade.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UsersActivity extends AppCompatActivity {

    DatabaseReference bbdd;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        lista = (ListView) findViewById(R.id.lista);

        bbdd = FirebaseDatabase.getInstance().getReference("usuario");

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayAdapter<String> adapter;
                ArrayList<String> listado = new ArrayList<String>();


                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                    Usuario u = datasnapshot.getValue(Usuario.class);

                    String user = u.getUser();
                    listado.add(user);

                }

                adapter = new ArrayAdapter<String>(UsersActivity.this, android.R.layout.simple_list_item_1, listado);
                lista.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
