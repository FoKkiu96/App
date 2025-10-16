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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        Button btnTomarFoto = findViewById(R.id.btnTomarFoto);
        imagenPrevia = findViewById(R.id.imgPreview);

        btnTomarFoto.setOnClickListener(v -> {
            Intent intent = new Intent(CamaraActivity.this, CamaraXActivity.class);
            startActivityForResult(intent, 200); // requestCode = 200
        });
    }

    // Recibir la foto desde CamaraXActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200 && resultCode == RESULT_OK) {
            Uri imagenUri = data.getData();
            if (imagenUri != null) {
                imagenPrevia.setImageURI(imagenUri);
            }
        }
    }

}
