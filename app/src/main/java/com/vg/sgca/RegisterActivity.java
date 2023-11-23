package com.vg.sgca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText et_nombre, et_apellido, et_rut, et_correo, et_pass1, et_pass2;
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
        et_apellido = findViewById(R.id.et_apellido);
        et_rut = findViewById(R.id.et_rut);
        et_correo = findViewById(R.id.et_correo);
        et_pass1 = findViewById(R.id.et_pass1);
        et_pass2 = findViewById(R.id.et_pass2);
        bt_registrar = findViewById(R.id.bt_registrar);

        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUsuario = et_nombre.getText().toString().trim();
                String apellidoUsuario = et_apellido.getText().toString().trim();
                String rutUsuario = et_rut.getText().toString().trim();
                String correoUsuario = et_correo.getText().toString().trim();
                String pass1Usuario = et_pass1.getText().toString().trim();
                String pass2Usuario = et_pass2.getText().toString().trim();

                if (nombreUsuario.isEmpty() && apellidoUsuario.isEmpty() && rutUsuario.isEmpty() && correoUsuario.isEmpty() && pass1Usuario.isEmpty() && pass2Usuario.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Los campos estan vacios", Toast.LENGTH_SHORT).show();
                } else if(nombreUsuario.isEmpty() || apellidoUsuario.isEmpty() || rutUsuario.isEmpty() || correoUsuario.isEmpty() || pass1Usuario.isEmpty() || pass2Usuario.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if(!pass1Usuario.equals(pass2Usuario)) {
                        Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }else{
                        String claveUsuario = pass1Usuario;
                        registrarUsuario(nombreUsuario, apellidoUsuario, rutUsuario, correoUsuario, claveUsuario);
                    }
                }
            }
        });




        //NAVEGACION
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void registrarUsuario(String nombreUsuario, String apellidoUsuario, String rutUsuario, String correoUsuario, String claveUsuario) {
        mAuth.createUserWithEmailAndPassword(correoUsuario, claveUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String idUsuario = mAuth.getCurrentUser().getUid();

                Map<String, Object> map = new HashMap<>();
                map.put("id_u", idUsuario);
                map.put("nombre_u", nombreUsuario);
                map.put("apellido_u", apellidoUsuario);
                map.put("rut_u", rutUsuario);
                map.put("correo_u", correoUsuario);
                map.put("clave_u", claveUsuario);

                mFirestore.collection("usuario").document(idUsuario).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(RegisterActivity.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Registrado con exito", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}