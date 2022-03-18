package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
public class MainActivity extends AppCompatActivity {
    private RecyclerView numbersList;
    private NumbersAdapter numbersAdapter;
    @0verride
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R. layout.activity main);
        numbersList = findViewById (R. id.rv_numbers);
        LinearLayoutManager layoutManager = new LinearLayoutManager( context: this);
        numbersList.setLayoutManager(layoutManager);
        numbersList.setHasFixedSize(true);
        numbersAdapter = new NumbersAdapter( numberOfitems: 100);
        numbersList.setAdapter(numbersAdapter);