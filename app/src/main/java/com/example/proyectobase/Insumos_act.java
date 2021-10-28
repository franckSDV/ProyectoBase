package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import Objetos.Insumos;

public class Insumos_act extends AppCompatActivity {

    private Spinner insumos;
    private TextView result;
    private RatingBar califica;
    private Insumos in = new Insumos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos);

        insumos = findViewById(R.id.spnInsumos);
        result = findViewById(R.id.tvResult);
        califica = findViewById(R.id.rbcalificar);

        result.setVisibility(View.INVISIBLE);

        // Recibo mis extras
        Bundle bun = getIntent().getExtras();// Recibo el intent con los valores del Bundle
        String[] listado = bun.getStringArray("insumos");//recibo el listado por su referencia

        ArrayAdapter adaptInsumos = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listado);
        insumos.setAdapter(adaptInsumos);
    }

    // Metodo para calcular insumos
    public void Calcular(View view)
    {
        String opcion = insumos.getSelectedItem().toString(); // obtengo la seleccion en una variable
        int resultado = 0;

        for(int i = 0; i < opcion.length(); i++)
        {
            if(opcion.equals(in.getInsumos()[i]))
            {
                //resultado = in.getPrecios()[i];
                resultado = in.anadirAdicional(in.getPrecios()[i], 350);
                califica.setRating(i);
                break;
            }
        }
        result.setVisibility(View.VISIBLE);
        result.setText("La opcion es: "+ opcion + "\nSu precio es: "+ resultado);
    }
}