package com.example.neteastmusic;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        LinearLayout toLocalMusic = findViewById(R.id.toLocalMusic);
        toLocalMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When clicked, first check if the program has permission to external storage
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    //If permission is granted, go to local music activity
                    Intent toLocalMusic = new Intent(MainActivity.this, LocalListActivity.class);
                    startActivity(toLocalMusic);

                } else {
                    //If permission is not granted, request for the permission
                    requestStoragePermission();

                    //After request, check again if the permission is granted. If so, go to local music activity. Otherwise, do nothing.
                    if(ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                        Intent toLocalMusic = new Intent(MainActivity.this, LocalListActivity.class);
                        startActivity(toLocalMusic);
                    }
                }
            }
        });

        //Set onClickListener on FavoriteListActivity
        LinearLayout toFavoriteMusic = findViewById(R.id.toFavoriteMusic);
        toFavoriteMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toFavoriteMusic = new Intent(MainActivity.this, MusicPlayingActivity.class);
                startActivity(toFavoriteMusic);
            }
        });

        //Set onClickListener on SystemListActivity
        LinearLayout toSystemMusic = findViewById(R.id.toSystemMusic);
        toSystemMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSystemMusic = new Intent(MainActivity.this, SystemListActivity.class);
                startActivity(toSystemMusic);
            }
        });
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to access local files")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

}
