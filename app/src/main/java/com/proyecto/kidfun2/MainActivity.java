package com.proyecto.kidfun2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ListElement> elementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        elementList = new ArrayList<>();
        elementList.add(new ListElement("#775447", "Español","Español", "esp"));
        elementList.add(new ListElement("#34eb64", "Ingles","Ingles", "ing"));

        ListAdapter listAdapter = new ListAdapter(elementList, this);
        RecyclerView recyclerView = findViewById(R.id.listActView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }
}