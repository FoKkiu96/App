package com.devst.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Toast;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class EventoActivity extends AppCompatActivity {

    // --- Receptor interno ---
    private BroadcastReceiver receptorInterno = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mensaje = intent.getStringExtra("dato");
            Toast.makeText(context, "Mensaje Recibido: " + mensaje, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        // Registrar el receptor cuando la Activity esté activa
        IntentFilter filtro = new IntentFilter("com.devst.app.MENSAJE_INTERNO");
        registerReceiver(receptorInterno, filtro, Context.RECEIVER_EXPORTED); // interno
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Desregistrar el receptor para evitar fugas de memoria
        unregisterReceiver(receptorInterno);
    }

    // --- Tu método para agregar el evento al calendario ---
    public void agregarEvento(View view) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);

        Calendar inicio = Calendar.getInstance();
        inicio.set(2025, Calendar.OCTOBER, 20, 10, 0);
        Calendar fin = Calendar.getInstance();
        fin.set(2025, Calendar.OCTOBER, 20, 11, 0);

        intent.putExtra(CalendarContract.Events.TITLE, "Charla motivacional");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Camarín");
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, inicio.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, fin.getTimeInMillis());
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Día de la gran final.");
        intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}