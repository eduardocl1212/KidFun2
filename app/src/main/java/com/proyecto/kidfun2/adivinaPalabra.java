package com.proyecto.kidfun2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class adivinaPalabra extends AppCompatActivity {
    TextView txtPalabraAdivinar, txtLetrasIntentadas, txtIntentosRestantes;
    String Palabra, comparadorString, LetrasIntentadas= " ", IntentosRestantes = " X X X X";
    char[] comparador;
    ArrayList<String> diccionario;
    EditText Letras;
    Button btnReset;
    private FirebaseDatabase db;
    final String PalabrasIntentadas = "Letras Intentadas";
    final String Ganado = "Has Ganado";
    final String Perdido = "Has Perdido";
    boolean tengoTodo = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivina_palabra);

        diccionario = new ArrayList<String>();
        obtenerBase();
        txtPalabraAdivinar = findViewById(R.id.txtToGuess);
        Letras = findViewById(R.id.txtOneLetter);
        txtIntentosRestantes = findViewById(R.id.txtIntentosRestantes);
        txtIntentosRestantes.setText(IntentosRestantes);
        txtLetrasIntentadas = findViewById(R.id.txtLetrasIntentadas);
        btnReset = findViewById(R.id.btnReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerBase();
                Letras.setText(" ");
                IntentosRestantes = " X X X X";
            }
        });

       Letras.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    existelaletra(s.charAt(0));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void existelaletra(char letter) {
        if (Palabra.indexOf(letter) >= 0){
            if(comparadorString.indexOf(letter) < 0){
                revelarLetraEnPalabra(letter);
                mostrarPalabra();

                if(!comparadorString.contains("_")){
                    txtIntentosRestantes.setText("Has ganado");
                    obtenerBase();
                }
            }
        }
        else {
            decrementarIntentos();

            if(IntentosRestantes.isEmpty()){
                txtIntentosRestantes.setText("Has perdido :(");
                txtPalabraAdivinar.setText(Palabra);
            }
        }

        if (LetrasIntentadas.indexOf(letter) < 0) {
            LetrasIntentadas += letter + ", ";
            String mensajeA = "Has intentado: " + LetrasIntentadas;
            txtLetrasIntentadas.setText(mensajeA);
        }
    }

    private void decrementarIntentos() {
        if(!IntentosRestantes.isEmpty()){
            IntentosRestantes = IntentosRestantes.substring(0, IntentosRestantes.length() -2);
            txtIntentosRestantes.setText(IntentosRestantes);
        }
    }

    private void obtenerBase() {

        db = FirebaseDatabase.getInstance();

        DatabaseReference reference = db.getReference().child("Palabras");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                diccionario.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    diccionario.add(snapshot1.getValue().toString());

                }
                iniciarJuego();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    void iniciarJuego() {
        Collections.shuffle(diccionario);
        Palabra = diccionario.get(0);
        diccionario.remove(0);
        comparador = Palabra.toCharArray();
        Log.e("Palabra", "La palabra seleccionada es: " + Palabra);

        for (int i = 2; i < comparador.length -1; i++){
            Log.e("Palabras", "Camino " + i + " " +comparador[i]);
            comparador[i] = '_';
        }

        revelarLetraEnPalabra(comparador[0]);
        revelarLetraEnPalabra(comparador[1]);
        revelarLetraEnPalabra(comparador[comparador.length -1]);

        comparadorString = String.valueOf(comparador);

        mostrarPalabra();

        Letras.setText("");

        LetrasIntentadas = " ";

        txtIntentosRestantes.setText("Intentos Restantes: ");

    }

    private void mostrarPalabra() {
        String formattedString = "";
        for(char character : comparador){
            formattedString += character + " ";
        }
        txtPalabraAdivinar.setText(formattedString);
    }

    private void revelarLetraEnPalabra(char letra) {
        int indexLetra = Palabra.indexOf(letra);

        while(indexLetra >= 0){
            comparador[indexLetra] = Palabra.charAt(indexLetra);
            indexLetra = Palabra.indexOf(letra, indexLetra + 1);

        }

        comparadorString = String.valueOf(comparador);
    }


}