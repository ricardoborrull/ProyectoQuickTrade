package com.example.a2dam.proyectoquicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.a2dam.proyectoquicktrade.model.UserAdapter;
import com.example.a2dam.proyectoquicktrade.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UsersActivity extends AppCompatActivity {

    DatabaseReference bbdd;
    private RecyclerView recycler;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager lManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        recycler = (RecyclerView) findViewById(R.id.recycler);
        bbdd = FirebaseDatabase.getInstance().getReference("usuario");

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserAdapter adaptador;
                ArrayList<Usuario> listado = new ArrayList<>();

                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                    Usuario u = datasnapshot.getValue(Usuario.class);
                    listado.add(u);
                }
                adaptador = new UserAdapter(listado);
                recycler.setAdapter(adaptador);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
