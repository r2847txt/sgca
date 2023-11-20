package com.vg.sgca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText et_nombre, et_apellido, et_rut, et_correo, et_pass1, et_pass2;
    Button bt_registrarse;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);
        et_rut = findViewById(R.id.et_rut);
        et_correo = findViewById(R.id.et_correo);
        et_pass1 = findViewById(R.id.et_pass1);
        et_pass2 = findViewById(R.id.et_pass2);
        bt_registrarse = findViewById(R.id.bt_registrarse);

        bt_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo, pass1;
                correo = String.valueOf(et_correo.getText());
                pass1 = String.valueOf(et_pass1.getText());

                if(TextUtils.isEmpty(correo)) {
                    Toast.makeText(RegisterActivity.this, "Ingrese email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(pass1)) {
                    Toast.makeText(RegisterActivity.this, "Ingrese clave", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(correo, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Cuenta creada.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "No se pudo crear la cuenta.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                });
            }
        });
    }
}