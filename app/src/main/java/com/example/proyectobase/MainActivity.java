package com.example.proyectobase;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import Objetos.Administrador;
import Objetos.Insumos;

public class MainActivity extends AppCompatActivity {

    private EditText user, pass;
    private TextView msje;
    private Button btn;
    private ProgressBar carga;
    private Administrador adm = new Administrador(); //instancia del obj administrador

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.etUser);
        pass = findViewById(R.id.etPass);
        msje = findViewById(R.id.tvMensaje);
        btn = findViewById(R.id.btnSesion);
        carga = findViewById(R.id.pbCarga);

        msje.setVisibility(View.INVISIBLE);
        carga.setVisibility(View.INVISIBLE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aqui voy a correr mi tarea
                new Task().execute();
            }
        });
    }

    // Tarea asincrona.
    class Task extends AsyncTask<String, Void, String>
    {
        //Puedo dar la configuracion inicial a mi tarea
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            carga.setVisibility(View.VISIBLE);
        }

        //encargado de procesar en segundo plano nuestra tarea pesada
        @Override
        protected String doInBackground(String... strings) {

            try {
                for(int i = 0; i <= 10; i++)
                {
                    Thread.sleep(500);//Duerme un proceso
                }
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        //Finaliza el proceso (Tarea asincrona).
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            carga.setVisibility(View.INVISIBLE);

            String usuario = user.getText().toString().trim();
            String contrasena = pass.getText().toString().trim();

            String userObj = adm.getUser().trim();
            String passObj = adm.getPass().trim();

            switch (usuario)
            {
                case "Francisco":
                    if (usuario.equals(userObj) && contrasena.equals(passObj))
                    {
                        msje.setVisibility(View.INVISIBLE);
                        Intent i = new Intent(getBaseContext(), Home_act.class);//Explicito

                        startActivity(i);
                    }
                    break;
                case "":
                    if (usuario.equals("") && contrasena.equals(""))
                    {
                        msje.setVisibility(View.VISIBLE);
                        msje.setText("Campos vacios porfavor intete nuevamente");
                    }
                    break;
                default:
                    if (!usuario.equals(userObj) && !contrasena.equals(passObj))
                    {
                        msje.setVisibility(View.VISIBLE);
                        msje.setText("Campos incorrectos porfavor intente nuevamente");
                    }
                    break;
            }

        }
    }

    public void Facebook(View view)
    {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.facebook.com/"));
        startActivity(i);
    }
    public void Twitter(View view)
    {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.twitter.com/"));
        startActivity(i);
    }
    public void Youtube(View view)
    {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.youtube.com/"));
        startActivity(i);
    }

    public void Info(View view)
    {
        Intent i = new Intent(this, Info_act.class);
        startActivity(i);
    }
}