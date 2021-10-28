package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import Objetos.Insumos;

public class Home_act extends AppCompatActivity {

    private VideoView video;
    private Insumos in = new Insumos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        video = findViewById(R.id.vw);//llamo al video

        //Obtener la ruta
        String ruta = "android.resource://"+ getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(ruta); // parseo la rurta
        video.setVideoURI(uri);// le paso mi ruta al videoView

        video.start();

        //Controles para el video
        //MediaController media = new MediaController(this);
        //video.setMediaController(media);
    }

    public void Insumos(View view)
    {
        // Preparo los extras
        Intent i = new Intent(this, Insumos_act.class);
        Bundle bun = new Bundle();// necesario para enviar arreglos
        bun.putStringArray("insumos", in.getInsumos());// Relleno el Bundle.
        i.putExtras(bun);// le paso el Bundle al intent
        startActivity(i);
    }
}