package com.matrimonial.marathimarriagebiodatamaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }




    public void DownloadBitMap()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            downloadData();
            Log.e("callPhone: ", "permission" );
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            Toast.makeText(this, "need permission", Toast.LENGTH_SHORT).show();
        }

    }

    private void downloadData() {

        ScrollView iv = (ScrollView) findViewById(R.id.scrollView);
        Bitmap bitmap = Bitmap.createBitmap(
                iv.getChildAt(0).getWidth()*2,
                iv.getChildAt(0).getHeight()*2,
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        c.scale(2.0f, 2.0f);
        c.drawColor(getResources().getColor(android.R.color.white));
        iv.getChildAt(0).draw(c);
        // Do whatever you want with your bitmap
        saveBitmap(bitmap);

    }


    public void saveBitmap(Bitmap bitmap) {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "SidduInvoices");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            // Do something on success
        } else {
            // Do something else on failure
        }

        File imagePath = new File(Environment.getExternalStorageDirectory() + "/SidduInvoices/Siddus.png");

        if(imagePath.exists())
        {
            imagePath=new File(Environment.getExternalStorageDirectory() + "/SidduInvoices/Siddus"+generateString().toString()+".png");

        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();



            final File finalImagePath = imagePath;




        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }



    }



    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return "uuid = " + uuid;
    }

    public void clickdownload(View view) {
        DownloadBitMap();
    }
}
