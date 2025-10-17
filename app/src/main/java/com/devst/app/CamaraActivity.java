package com.devst.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class CamaraActivity extends AppCompatActivity {

    private ImageView imagenPrevia;

    // Lanzadores modernos (reemplazan a startActivityForResult)
    private ActivityResultLauncher<Intent> launcherCamara;
    private ActivityResultLauncher<String> launcherGaleria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        Button btnTomarFoto = findViewById(R.id.btnTomarFoto);
        Button btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        imagenPrevia = findViewById(R.id.imgPreview);

        // Inicializamos los ActivityResultLaunchers
        inicializarLaunchers();

        // Abrir cámara frontal (CamaraXActivity)
        btnTomarFoto.setOnClickListener(v -> {
            Intent intent = new Intent(CamaraActivity.this, CamaraXActivity.class);
            launcherCamara.launch(intent);
        });

        // Seleccionar imagen desde la galería
        btnSeleccionarImagen.setOnClickListener(v -> {
            launcherGaleria.launch("image/*"); // abre solo imágenes
        });
    }

    private void inicializarLaunchers() {
        // Recibe la imagen tomada desde CamaraXActivity
        launcherCamara = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imagenUri = result.getData().getData();
                        if (imagenUri != null) {
                            imagenPrevia.setImageURI(imagenUri);
                        }
                    }
                });

        // Recibe la imagen seleccionada desde la galería
        launcherGaleria = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        imagenPrevia.setImageURI(uri);
                    }
                });
    }
}

