package com.vg.sgca;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SoporteActivity extends AppCompatActivity {
    Button bt_nuevoTicket;
    RecyclerView recyclerView;
    TicketAdapter ticketAdapter;
    List<Ticket> listaTickets;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte);

        mFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.rv_listarTickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        obtenerListaTickets();

        bt_nuevoTicket = findViewById(R.id.bt_nuevoTicket);
        bt_nuevoTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SoporteActivity.this, SoporteCrearTicketActivity.class));
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Soporte");
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
                return true;
            } else if (itemId == R.id.bottom_notificaciones) {
                startActivity(new Intent(getApplicationContext(), NotificacionesActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            } else if (itemId == R.id.bottom_soporte) {
                return true;
            } else if (itemId == R.id.bottom_perfil) {
                startActivity(new Intent(getApplicationContext(), PerfilActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            }
            return false;
        });
    }

    // aqui la chaya pa obtener lista del firebas
    private void obtenerListaTickets() {
        listaTickets = new ArrayList<>();

        // se supone que así se hace la consulta al firebase
        mFirestore.collection("ticket")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String asunto = document.getString("asunto_ticket");
                            String detalles = document.getString("detalle_ticket");
                            String estado = document.getString("estado");

                            // aqui se crea el ovejto ticket y se agrega a la lista
                            Ticket ticket = new Ticket(asunto, detalles, estado);
                            listaTickets.add(ticket);
                        }


                        ticketAdapter = new TicketAdapter(listaTickets);
                        recyclerView.setAdapter(ticketAdapter);
                    } else {
                        // Manejar el error, si lo hay
                        Log.e("SoporteActivity", "Error al obtener los tickets", task.getException());
                    }
                });
    }

    //NAVEGACION
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void citasCrearTicket(View view) {
        try {
            Intent intent = new Intent(this, SoporteCrearTicketActivity.class);
            startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(this, "Excepcion " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
