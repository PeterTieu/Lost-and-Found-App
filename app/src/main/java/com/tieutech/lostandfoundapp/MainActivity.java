package com.tieutech.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//ABOUT: Main activity - links to the user to create a new Advert or view the list of all adverts
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Listener for "Create A New Advert" Button
    public void createANewAdvertClick(View view) {
        Intent createANewAdvertIntent = new Intent(MainActivity.this, CreateANewAdvertActivity.class);
        startActivity(createANewAdvertIntent);
    }

    //Listener for "Show All Lost and Found Items" Button
    public void showAllLostAndFoundItemsClick(View view) {
        Intent lostAndFoundItemsIntent = new Intent(MainActivity.this, LostAndFoundItemsActivity.class);
        startActivity(lostAndFoundItemsIntent);
    }
}