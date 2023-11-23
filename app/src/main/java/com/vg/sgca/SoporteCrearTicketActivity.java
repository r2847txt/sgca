package com.vg.sgca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SoporteCrearTicketActivity extends AppCompatActivity {
    EditText et_asuntoTicket, et_detalleTicket;
    Button bt_ingresarTicket;
    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte_crear_ticket);
        mfirestore = FirebaseFirestore.getInstance();
        et_asuntoTicket = findViewById(R.id.et_asuntoTicket);
        et_detalleTicket = findViewById(R.id.et_detalleTicket);
        bt_ingresarTicket = findViewById(R.id.bt_ingresarTicket);

        bt_ingresarTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String asuntoTicket = et_asuntoTicket.getText().toString().trim();
                String detalleTicket = et_detalleTicket.getText().toString().trim();

                if(asuntoTicket.isEmpty() && detalleTicket.isEmpty()) {
                    Toast.makeText(SoporteCrearTicketActivity.this, "Los campos estan vacios", Toast.LENGTH_SHORT).show();
                }else if(asuntoTicket.isEmpty() || detalleTicket.isEmpty()) {
                    Toast.makeText(SoporteCrearTicketActivity.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    postTicket(asuntoTicket, detalleTicket);
                }
            }

            private void postTicket(String asuntoTicket, String detalleTicket) {
                Map<String, Object> map = new HashMap<>();
                map.put("asunto_ticket", asuntoTicket);
                map.put("detalle_ticket", detalleTicket);
                map.put("estado", "PENDIENTE"); // Cambia "PENDIENTE" según tus necesidades
                mfirestore.collection("ticket").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(SoporteCrearTicketActivity.this, "Ticket ingresado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SoporteCrearTicketActivity.this, "No se pudo guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        //NAVEGACION
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ticket de soporte");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_soporte);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_inicio) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

    private void postTicket(String asuntoTicket, String detalleTicket) {
        Map<String, Object> map = new HashMap<>();
        map.put("asunto_ticket", asuntoTicket);
        map.put("detalle_ticket", detalleTicket);
        mfirestore.collection("ticket").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(SoporteCrearTicketActivity.this, "Ticket ingresado", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SoporteCrearTicketActivity.this, "No se pudo guardar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //NAVEGACION
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}