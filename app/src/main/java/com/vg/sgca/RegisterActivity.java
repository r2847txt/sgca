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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText et_nombre, et_correo, et_contrasena;
    Button bt_registrar;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        et_nombre = findViewById(R.id.et_nombre);
        et_correo = findViewById(R.id.et_correo);
        et_contrasena = findViewById(R.id.et_contrasena);
        bt_registrar = findViewById(R.id.bt_registrar);

        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUsuario = et_nombre.getText().toString().trim();
                String correoUsuario = et_correo.getText().toString().trim();
                String contrasenaUsuario = et_contrasena.getText().toString().trim();

                if(nombreUsuario.isEmpty() && correoUsuario.isEmpty() && contrasenaUsuario.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Debe ingresar los datos solicitados", Toast.LENGTH_SHORT).show();
                }else{
                    registrarUsuario(nombreUsuario, correoUsuario, contrasenaUsuario);
                }

            }
        });
    }

    private void registrarUsuario(String nombreUsuario, String correoUsuario, String contrasenaUsuario) {
        mAuth.createUserWithEmailAndPassword(correoUsuario, contrasenaUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();

                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("nombre", nombreUsuario);
                map.put("correo", correoUsuario);
                map.put("contraseña", contrasenaUsuario);

                mFirestore.collection("usuario").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}