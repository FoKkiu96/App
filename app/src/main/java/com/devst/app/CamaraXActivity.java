package com.devst.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CamaraXActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSIONS = 101;
    private final String[] REQUIRED_PERMISSIONS = new String[]{Manifest.permission.CAMERA};

    private PreviewView previewView;
    private ImageCapture imageCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camarax);

        previewView = findViewById(R.id.previewView);
        Button btnCapturarFoto = findViewById(R.id.btnCapturarFoto);

        if (allPermissionsGranted()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }

        btnCapturarFoto.setOnClickListener(v -> capturarFoto());
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                imageCapture = new ImageCapture.Builder().build();

                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                        .build();

                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);

            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(this, "Error iniciando la cámara", Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void capturarFoto() {
        if (imageCapture == null) return;

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File fotoArchivo = new File(dir, "IMG_" + timeStamp + ".jpg");

        ImageCapture.OutputFileOptions outputOptions =
                new ImageCapture.OutputFileOptions.Builder(fotoArchivo).build();

        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        Uri savedUri = Uri.fromFile(fotoArchivo);

                        // Devuelve la URI al Activity anterior
                        Intent resultIntent = new Intent();
                        resultIntent.setData(savedUri);
                        setResult(RESULT_OK, resultIntent);
                        finish(); // cierra CamaraXActivity
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Toast.makeText(CamaraXActivity.this, "Error al capturar foto", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
