package com.proyecto.kidfun2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Actividades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String select = intent.getStringExtra("DescAct");
        setContentView(R.layout.activity_actividades);
        if(select.equals("esp")){
           finish();
           Intent Actividad = new Intent(this, adivinaPalabra.class);
           startActivity(Actividad);
        }
        else{
            Toast.makeText(this, "Pendiente", Toast.LENGTH_SHORT).show();
            finish();
        }





    }
}