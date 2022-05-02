package com.tieutech.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.tieutech.lostandfoundapp.Model.Advert;
import com.tieutech.lostandfoundapp.adapter.AdvertRecyclerViewAdapter;
import com.tieutech.lostandfoundapp.data.AdvertDatabaseHelper;
import com.tieutech.lostandfoundapp.util.Util;

import java.util.ArrayList;
import java.util.List;

//ABOUT: Activity that displays the list of all Adverts
public class LostAndFoundItemsActivity extends AppCompatActivity implements AdvertRecyclerViewAdapter.OnAdvertListener{

    //Data variables
    String itemState;
    String name;
    String phone;
    String description;
    String date;
    String location;

    //RecyclerView variables
    RecyclerView advertRecyclerView;
    AdvertRecyclerViewAdapter advertRecyclerViewAdapter;
    List<Advert> advertsList = new ArrayList<>(); //List of all Adverts

    //Database variable
    AdvertDatabaseHelper advertDatabaseHelper = new AdvertDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found_items);

        advertRecyclerView = findViewById(R.id.advertRecyclerView); //Obtain RecyclerView

        //Obtain data from the AdvertDataHelper database (database of my Adverts) and add them to advertsList
        try {
            Cursor cursor = advertDatabaseHelper.fetchAdvertList(); //Obtain the cursor for the database

            //Cursor to the FIRST entry in the Adverts database
            if (cursor != null) {
                cursor.moveToFirst();

                //Obtain data from the first row of the database
                itemState = cursor.getString(1);
                name = cursor.getString(2);
                phone = cursor.getString(3);
                description = cursor.getString(4);
                date = cursor.getString(5);
                location = cursor.getString(6);

                //Add all the obtained data from the FIRST advert in the database to the advertssList
                advertsList.add(new Advert(itemState, name, phone, description, date, location));
            }

            //Cursor to the rest of the entries int he Adverts database
            while (cursor.moveToNext()) {

                //Obtain data from the first row of the database
                itemState = cursor.getString(1);
                name = cursor.getString(2);
                phone = cursor.getString(3);
                description = cursor.getString(4);
                date = cursor.getString(5);
                location = cursor.getString(6);

                //Add all the obtained data from the FIRST advert in the database to the advertsList
                advertsList.add(new Advert(itemState, name, phone, description, date, location));
            }
        }
        catch (Exception e) {
            Log.i("Error", "An error has occurred");
        }

        //RecyclerViewAdapter to link the RecyclerView for Adverts to the data
        advertRecyclerViewAdapter = new AdvertRecyclerViewAdapter(advertsList, this, this); //Instantiate the Recyclerview Adapter
        advertRecyclerView.setAdapter(advertRecyclerViewAdapter); //Set the Adapter to the RecyclerView

        //LinearLayoutManager to set the layout of the RecyclerView (and make it vertical)
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        advertRecyclerView.setLayoutManager(layoutManager); //Link the LayoutManager to the RecyclerView
    }

    //Listener for the selection of an Advert item
    @Override
    public void onAdvertClick(int position) {

        //Begin intent to open AdvertDetailsActivity
        Intent itemDetailsIntent = new Intent(LostAndFoundItemsActivity.this, ItemDetailsActivity.class);

        //Send data to the AdvertDetailsActivity
        itemDetailsIntent.putExtra(Util.DATA_ITEM_STATE, advertsList.get(position).isItemFound());
        itemDetailsIntent.putExtra(Util.ADVERT_DATABASE_NAME, advertsList.get(position).getName());
        itemDetailsIntent.putExtra(Util.ADVERT_PHONE, advertsList.get(position).getPhone());
        itemDetailsIntent.putExtra(Util.DATA_DESCRIPTION, advertsList.get(position).getDescription());
        itemDetailsIntent.putExtra(Util.DATA_DATE, advertsList.get(position).getDate());
        itemDetailsIntent.putExtra(Util.DATA_LOCATION, advertsList.get(position).getLocation());

        //Open AdvertDetailsActivity
        startActivity(itemDetailsIntent);
    }
}