package com.vg.sgca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class CitasCrearActivity extends AppCompatActivity {
    EditText et_asuntoCita, et_detalleCita;
    TextView tv_fechaSeleccionada;
    Button bt_seleccionarFecha;
    int dia, mes, ano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_crear);
        et_asuntoCita = findViewById(R.id.et_asuntoCita);
        et_detalleCita = findViewById(R.id.et_detalleCita);
        tv_fechaSeleccionada = findViewById(R.id.tv_fechaSeleccionada);
        bt_seleccionarFecha = findViewById(R.id.bt_seleccionarFecha);

        bt_seleccionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendario = Calendar.getInstance();

                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                ano = calendario.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CitasCrearActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int anoSeleccionado, int mesSeleccionado, int diaSeleccionado) {
                        String formatoDia, formatoMes;
                        int mes = mesSeleccionado + 1;

                        if (diaSeleccionado < 10) {
                            formatoDia = "0" + String.valueOf(diaSeleccionado);
                        } else {
                            formatoDia = String.valueOf(diaSeleccionado);
                        }

                        if (mes < 10) {
                            formatoMes = "0" + String.valueOf(mes);
                        }  else {
                            formatoMes = String.valueOf(mes);
                        }

                        tv_fechaSeleccionada.setText(formatoDia + "/" + formatoMes + "/" + anoSeleccionado);

                    }
                }, ano, mes,dia);
                datePickerDialog.show();
            }
        });


        //NAVEGACION
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detalle de cita");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_citas);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_inicio) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            } else if (itemId == R.id.bottom_citas) {
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}