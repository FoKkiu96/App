package com.devst.app;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.view.View;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class EventoActivity  extends AppCompatActivity {


    public void agregarEvento(View view) {
        // Creamos un intent para insertar evento
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);

        // Configuramos la fecha/hora del evento
        Calendar inicio = Calendar.getInstance();
        inicio.set(2025, Calendar.OCTOBER, 20, 10, 0); // Año, mes, día, hora, minuto
        Calendar fin = Calendar.getInstance();
        fin.set(2025, Calendar.OCTOBER, 20, 11, 0);

        // Agregamos los datos del evento
        intent.putExtra(CalendarContract.Events.TITLE, "Charla motivacional");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Camarin");
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, inicio.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, fin.getTimeInMillis());
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Dia de La gran Final .");
        intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

        // Verificamos si hay app de calendario disponible
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
