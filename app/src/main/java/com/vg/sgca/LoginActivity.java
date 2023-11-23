package com.vg.sgca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText et_correoLogin, et_claveLogin;
    Button bt_ingresarLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        et_correoLogin = findViewById(R.id.et_correoLogin);
        et_claveLogin = findViewById(R.id.et_claveLogin);
        bt_ingresarLogin = findViewById(R.id.bt_ingresarLogin);

        bt_ingresarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoLogin = et_correoLogin.getText().toString().trim();
                String claveLogin = et_claveLogin.getText().toString().trim();
                
                if (correoLogin.isEmpty() && claveLogin.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Debe ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                    loginUsuario(correoLogin, claveLogin);
                }
            }
        });

    }

    private void loginUsuario(String correoLogin, String claveLogin) {
        mAuth.signInWithEmailAndPassword(correoLogin, claveLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuario = mAuth.getCurrentUser();

        if (usuario != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }
}