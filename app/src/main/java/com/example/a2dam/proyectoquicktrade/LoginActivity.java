package com.example.a2dam.proyectoquicktrade;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button login;
    private EditText email, password;
    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sEmail = email.getText().toString();
                String sPassw = password.getText().toString();

                registrar(sEmail, sPassw);

            }
        });
    }

        private void registrar(String SEmail, String SPassw) {

            mAuth = FirebaseAuth.getInstance();

            mAuth.createUserWithEmailAndPassword(SEmail, SPassw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Authentication successfull."+user.getUid(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                FirebaseUser user = mAuth.getCurrentUser();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Authentication failed."+task.getException(),
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }

}


