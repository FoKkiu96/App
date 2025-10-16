package com.devst.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class CamaraActivity extends AppCompatActivity {

    private ImageView imagenPrevia;
    private Uri urlImagen;

    // Launcher para permisos de cámara (puede quedarse por si quieres usar cámara más adelante)
    private final ActivityResultLauncher<String> permisoCamaraLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                if (granted) tomarFoto();
                else Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            });

    // Launcher para tomar foto (puede quedarse por si quieres usar cámara más adelante)
    private final ActivityResultLauncher<Uri> takePictureLauncher =
            registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> {
                if (result && urlImagen != null) {
                    imagenPrevia.setImageURI(urlImagen);
                    Toast.makeText(this, "Foto guardada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Captura cancelada", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        Button btnTomarFoto = findViewById(R.id.btnTomarFoto);
        imagenPrevia = findViewById(R.id.imgPreview);

        // Nuevo comportamiento: abrir CamaraXActivity al presionar el botón
        btnTomarFoto.setOnClickListener(v -> {
            Intent intent = new Intent(CamaraActivity.this, CamaraXActivity.class);
            startActivity(intent);
        });

        // Aquí podrías agregar el botón de seleccionar imagen si quieres mantenerlo funcional
    }

    // Métodos anteriores de cámara (pueden mantenerse si luego quieres usarlos)
    private void checkPermisoYTomar() {
        boolean granted = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
        if (granted) {
            tomarFoto();
        } else {
            permisoCamaraLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void tomarFoto() {
        // Método para tomar foto (queda por si quieres activarlo en otra parte)
        // No se usará con la nueva lógica actual
    }
}
