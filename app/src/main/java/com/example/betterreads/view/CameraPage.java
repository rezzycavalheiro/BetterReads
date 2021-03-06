package com.example.betterreads.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.betterreads.R;
import com.example.betterreads.model.PhotoModel;
import com.example.betterreads.model.Pictures;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraPage extends AppCompatActivity {

    static final int CAMERA_PERMISSION_CODE = 2001;
    static final int CAMERA_INTENT_CODE = 3001;
    static final int GALLERY_SELECT_IMAGE = 4001;
    ImageView imageViewCamera;
    String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_page);
        imageViewCamera = findViewById(R.id.imageViewCamera);
    }

    public void cameraButton(View view){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestCameraPermission();
        }
        else {
            sendCameraIntent();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void requestCameraPermission(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
               requestPermissions(new String[]{
                       Manifest.permission.CAMERA
               }, CAMERA_PERMISSION_CODE);
            }
            else {
                sendCameraIntent();
            }
        }
        else {
            Toast.makeText(CameraPage.this, "N??o h?? c??meras dispon??ves.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendCameraIntent();
            }
            else {
                Toast.makeText(CameraPage.this, "Permiss??o de uso da c??mera negada.", Toast.LENGTH_LONG).show();
            }
        }
    }

    // ABRE A C??MERA E PERMITE TIRAR FOTO
    void sendCameraIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
        if(intent.resolveActivity(getPackageManager()) != null){
            // new Date() retorna a hora atual
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date());
            String picName = "pic_" +  timeStamp;
            File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File pictureFile = null;
            try {
                pictureFile = File.createTempFile(picName, ".jpg",dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(pictureFile != null){
                picturePath = pictureFile.getAbsolutePath();
                Uri photouri = FileProvider.getUriForFile(
                        CameraPage.this,
                        "com.example.betterreads.fileprovider",
                        pictureFile
                );
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photouri);
                startActivityForResult(intent, CAMERA_INTENT_CODE);
            }
        }
    }

    // ADICIONA A FOTO NA GALERIA DO CELULAR
    public void galleryAddPic(String file) {
        File f = new File(file);
        Uri contentUri = Uri.fromFile(f);
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri);
        sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // USA A FOTO TIRADA PELO APLICATIVO
        if(requestCode == CAMERA_INTENT_CODE){
            if(resultCode == RESULT_OK){
                File file = new File(picturePath);
                if(file.exists()){
                    imageViewCamera.setImageURI(Uri.fromFile(file));
                    galleryAddPic(picturePath);
                }
            }
            else {
                Toast.makeText(CameraPage.this, "Problema ao pegar a imagem da c??mera.",
                        Toast.LENGTH_LONG).show();
            }
        }
        // USA FOTO SELECIONADA PELO USU??RIO NA GALERIA
        else if(requestCode == GALLERY_SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] path = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, path, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(path[0]);
                picturePath = cursor.getString(columnIndex);

                imageViewCamera.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                cursor.close();
            }
        }
    }

    // VOLTA PARA A P??GINA DE PEDIDOS -> menu inicial
    public void orderPageButton(View view){
        Intent orderPage = new Intent(this, StartMenu.class);
        PhotoModel.getInstance().picturesArrayList.add(new Pictures(picturePath));
        startActivity(orderPage);
    }

    // ABRE A GALERIA DO CELULAR
    public void openGallery(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, GALLERY_SELECT_IMAGE);
    }
}