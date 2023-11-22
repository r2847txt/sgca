package com.vg.sgca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_inicio);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_inicio) {
                return true;
            } else if (itemId == R.id.bottom_citas) {
                startActivity(new Intent(getApplicationContext(), CitasActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                //finish();
                return true;
            } else if (itemId == R.id.bottom_notificaciones) {
                startActivity(new Intent(getApplicationContext(), NotificacionesActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                //finish();
                return true;
            } else if (itemId == R.id.bottom_soporte) {
                startActivity(new Intent(getApplicationContext(), SoporteActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                //finish();
                return true;
            } else if (itemId == R.id.bottom_perfil) {
                startActivity(new Intent(getApplicationContext(), PerfilActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                //finish();
                return true;
            }
            return false;
        });
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