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
        elementList.add(new ListElement("#775447", "Español","Español", "esp", "https://cdn.pixabay.com/photo/2019/03/12/01/41/books-4049868_960_720.png"));
        elementList.add(new ListElement("#34eb64", "Inglés","Inglés", "ing", "https://file.gtosecurity.club/f.php?h=0U9993Kk&p=1"));

        ListAdapter listAdapter = new ListAdapter(elementList, this);
        RecyclerView recyclerView = findViewById(R.id.listActView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }
}