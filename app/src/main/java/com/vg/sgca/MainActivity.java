package com.vg.sgca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 4 botones con las principales acciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inicio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    public void citas(View view) {
        try {
            Intent intent = new Intent(this, CitasActivity.class);
            startActivity(intent);
        }catch (Exception ex) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Excepcion " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void notificaciones(View view) {
        try {
            Intent intent = new Intent(this, NotificacionesActivity.class);
            startActivity(intent);
        }catch (Exception ex) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Excepcion " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void soporte(View view) {
        try {
            Intent intent = new Intent(this, SoporteActivity.class);
            startActivity(intent);
        }catch (Exception ex) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Excepcion " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void perfil(View view) {
        try {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        }catch (Exception ex) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Excepcion " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}